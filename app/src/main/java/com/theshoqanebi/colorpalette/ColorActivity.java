package com.theshoqanebi.colorpalette;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theshoqanebi.colorpalette.databinding.ActivityColorBinding;

import java.io.IOException;

public class ColorActivity extends AppCompatActivity {
    ActivityColorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Uri uri = getIntent().getData();

        if (uri != null) {
            try {
                Bitmap bitmap = getBitmapFromUri(uri);
                createPaletteAsync(bitmap);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Image can't be processed", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(palette -> {
            if (palette != null) {
                binding.getRoot().setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                binding.getRoot().setAdapter(new Adapter(palette.getSwatches(), getApplicationContext()));
            }
        });
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        }
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.getContentResolver(), uri));
    }
}
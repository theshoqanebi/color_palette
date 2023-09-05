package com.theshoqanebi.colorpalette;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.theshoqanebi.colorpalette.databinding.ColorBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    List<Palette.Swatch> swatches;
    Context context;

    public Adapter(@NonNull List<Palette.Swatch> swatches, @NonNull Context context) {
        this.swatches = swatches;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ColorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Palette.Swatch swatch = swatches.get(position);
        holder.binding.color.setBackgroundColor(swatch.getRgb());
        holder.binding.color.setText(rgbToHex(swatch.getRgb()));
        holder.binding.getRoot().setOnClickListener(this::onClick);
    }

    @Override
    public int getItemCount() {
        return swatches.size();
    }

    private String rgbToHex(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    private void onClick(View view) {
        String text = ((TextView) view).getText().toString();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Color copied", Toast.LENGTH_LONG).show();
    }
}

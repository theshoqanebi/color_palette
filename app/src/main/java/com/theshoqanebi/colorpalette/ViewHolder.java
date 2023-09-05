package com.theshoqanebi.colorpalette;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theshoqanebi.colorpalette.databinding.ColorBinding;

public class ViewHolder extends RecyclerView.ViewHolder {
    ColorBinding binding;

    public ViewHolder(@NonNull ColorBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

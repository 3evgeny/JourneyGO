package com.melself.journeygo.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melself.journeygo.databinding.ListPassportBinding;

public class PassportAdapter extends RecyclerView.Adapter<PassportAdapter.PassportViewHolder> {

    public static class PassportViewHolder extends RecyclerView.ViewHolder {
        ListPassportBinding binding;

        public PassportViewHolder(ListPassportBinding item) {
            super(item.getRoot());
            binding = item;
        }
    }

    @NonNull
    @Override
    public PassportAdapter.PassportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PassportViewHolder(ListPassportBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PassportAdapter.PassportViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

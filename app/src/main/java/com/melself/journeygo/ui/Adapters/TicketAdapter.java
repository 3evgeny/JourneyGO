package com.melself.journeygo.ui.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melself.journeygo.databinding.ListTicketBinding;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        ListTicketBinding binding;
        public TicketViewHolder(ListTicketBinding item) {
            super(item.getRoot());
            binding = item;
        }
    }

    @NonNull
    @Override
    public TicketAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.TicketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}

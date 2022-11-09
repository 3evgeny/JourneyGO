package com.melself.journeygo.ui.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.melself.journeygo.data.model.Ticket;
import com.melself.journeygo.databinding.ListTicketBinding;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> tickets = new ArrayList<>();

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        ListTicketBinding binding;
        public TicketViewHolder(ListTicketBinding item) {
            super(item.getRoot());
            binding = item;
        }

        public void bindView(Ticket ticket){
            binding.numberTicketList.setText(ticket.getNumber());
            binding.descriptionTicketList.setText(ticket.getCountry() + "\n" + ticket.getHotel());
            binding.dateTicketList.setText(ticket.getDate());
            binding.statusTicket.setText(ticket.getStatus());
        }
    }

    @NonNull
    @Override
    public TicketAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListTicketBinding binding = ListTicketBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TicketViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.bindView(ticket);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public void setTickets(List<Ticket> ticket){
        this.tickets = ticket;
        notifyDataSetChanged();
    }
}

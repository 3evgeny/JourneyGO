package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Ticket;
import com.melself.journeygo.data.repository.CountryRepository;
import com.melself.journeygo.data.repository.TicketRepository;

import java.util.List;

public class BuyViewModel extends AndroidViewModel {
    private final TicketRepository ticketRepository;
    private LiveData<List<Ticket>> getAllTickets;
    private LiveData<Ticket> getTicket;

    public BuyViewModel(@NonNull Application application) {
        super(application);
        ticketRepository = new TicketRepository(application);
        getAllTickets = ticketRepository.getAllTicketsLive();
    }

    public void insert(Ticket ticket){
        ticketRepository.insertTicket(ticket);
    }

    public void update(Ticket ticket){
        ticketRepository.updateTicket(ticket);
    }

    public void delete(Ticket ticket){
        ticketRepository.deleteTicket(ticket);
    }

    public LiveData<List<Ticket>> getAllTicketsFromView(){
        return getAllTickets;
    }

    public LiveData<List<Ticket>> getTicketFromView(long id){
        return ticketRepository.getTicketLive(id);
    }
}
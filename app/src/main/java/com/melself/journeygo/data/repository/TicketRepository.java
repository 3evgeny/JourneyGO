package com.melself.journeygo.data.repository;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.melself.journeygo.data.AppDatabase;
import com.melself.journeygo.data.DBTicket;
import com.melself.journeygo.data.TicketDAO;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepository {

    private TicketDAO ticketDAO;
    private LiveData<List<DBTicket>> getAllTicketsRepository;
    private LiveData<DBTicket> getTicket;

    public TicketRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ticketDAO = appDatabase.getTicketDAO();
        getAllTicketsRepository = ticketDAO.getAllTickets();
    }

    public void insertTicket(Ticket ticket){
        DBTicket dbTicket = DBTicket.convertToDBTicket(ticket);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ticketDAO.insertTicket(dbTicket);
        });
    }

    public void updateTicket(Ticket ticket){
        DBTicket dbTicket = DBTicket.convertToDBTicket(ticket);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ticketDAO.updateTicket(dbTicket);
        });
    }

    public void deleteTicket(Ticket ticket){
        DBTicket dbTicket = DBTicket.convertToDBTicket(ticket);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ticketDAO.deleteTicket(dbTicket);
        });
    }

    public LiveData<List<Ticket>> getAllTicketsLive(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Transformations.map(ticketDAO.getAllTickets(), dbTickets -> dbTickets.stream().map(dbTicket -> dbTicket.convertToTicket()).collect(Collectors.toList()));
        }
        return new MutableLiveData<List<Ticket>>(new ArrayList<>());
    }

    public LiveData<Ticket> getTicketLive(long id){
        return Transformations.map(ticketDAO.getTicket(id), dbTicket -> dbTicket.convertToTicket());
    }
}

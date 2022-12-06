package com.melself.journeygo.ui.views;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Ticket;
import com.melself.journeygo.databinding.FragmentTicketBinding;
import com.melself.journeygo.ui.Adapters.TicketAdapter;
import com.melself.journeygo.ui.viewmodels.TicketViewModel;

import java.util.ArrayList;
import java.util.List;

public class TicketFragment extends Fragment {

    private TicketViewModel mViewModel;
    FragmentTicketBinding binding;
    private TicketAdapter ticketAdapter;

    private List<Ticket> itemList = new ArrayList<>();

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerTicket.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketAdapter = new TicketAdapter();
        binding.recyclerTicket.setAdapter(ticketAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TicketViewModel.class);

//        mViewModel.getAllTicketsFromView().observe(getViewLifecycleOwner(), new Observer<List<Ticket>>() {
//            @Override
//            public void onChanged(List<Ticket> tickets) {
//                ticketAdapter.setTickets(tickets);
//            }
//        });

        mViewModel.getTicketPersonFromView(MainActivity.user_id).observe(getViewLifecycleOwner(), new Observer<List<Ticket>>() {
            @Override
            public void onChanged(List<Ticket> tickets) {
                ticketAdapter.setTickets(tickets);
            }
        });

//        mViewModel.getTicketFromView(1).observe(getViewLifecycleOwner(), new Observer<Ticket>() {
//            @Override
//            public void onChanged(Ticket ticket) {
//                System.out.println(ticket.getCountry());
//                itemList.add(ticket);
//                System.out.println(itemList.size());
//            }
//        });
    }

}
package com.melself.journeygo.ui.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.R;
import com.melself.journeygo.databinding.FragmentTicketBinding;
import com.melself.journeygo.ui.Adapters.TicketAdapter;
import com.melself.journeygo.ui.viewmodels.TicketViewModel;

public class TicketFragment extends Fragment {

    private TicketViewModel mViewModel;
    FragmentTicketBinding binding;
    TicketAdapter ticketAdapter;

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
        // TODO: Use the ViewModel
    }

}
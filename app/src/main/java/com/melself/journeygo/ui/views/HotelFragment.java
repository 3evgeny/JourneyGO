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
import com.melself.journeygo.data.model.Hotel;
import com.melself.journeygo.databinding.FragmentHotelBinding;
import com.melself.journeygo.ui.Adapters.HotelAdapter;
import com.melself.journeygo.ui.viewmodels.HotelViewModel;

import java.util.ArrayList;
import java.util.List;

public class HotelFragment extends Fragment {

    private HotelViewModel mViewModel;
    FragmentHotelBinding binding;
    HotelAdapter hotelAdapter;

    public static HotelFragment newInstance() {
        return new HotelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHotelBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerHotel.setLayoutManager(new LinearLayoutManager(getContext()));
        hotelAdapter = new HotelAdapter(getContext());
        binding.recyclerHotel.setAdapter(hotelAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HotelViewModel.class);
        // TODO: Use the ViewModel

        Hotel hotel = new Hotel();
        hotel.setId(0);
        hotel.setName("Azimut");
        hotel.setDescription("City: Moscow");
        hotel.setPrice("10000");

        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel);

        hotelAdapter.setHotels(hotels);
    }

}
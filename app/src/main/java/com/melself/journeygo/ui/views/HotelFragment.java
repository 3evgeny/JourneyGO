package com.melself.journeygo.ui.views;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Hotel;
import com.melself.journeygo.data.retrofit.HotelService;
import com.melself.journeygo.data.retrofit.Root;
import com.melself.journeygo.databinding.FragmentHotelBinding;
import com.melself.journeygo.ui.Adapters.HotelAdapter;
import com.melself.journeygo.ui.viewmodels.HotelViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Bundle bundle = this.getArguments();
        String location = bundle.getString("nameCountry");
        System.out.println(location);

        mViewModel.getHotels(location).observe(getViewLifecycleOwner(), new Observer<List<Hotel>>() {
            @Override
            public void onChanged(List<Hotel> hotels) {
                hotelAdapter.setHotels(hotels);
            }
        });
    }

}
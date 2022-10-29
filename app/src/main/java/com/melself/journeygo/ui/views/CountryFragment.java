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
import com.melself.journeygo.databinding.FragmentCountryBinding;
import com.melself.journeygo.ui.Adapters.CountryAdapter;
import com.melself.journeygo.ui.viewmodels.CountryViewModel;

public class CountryFragment extends Fragment {

    private CountryViewModel mViewModel;
    FragmentCountryBinding binding;
    CountryAdapter countryAdapter;

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCountryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerCountry.setLayoutManager(new LinearLayoutManager(getContext()));
        countryAdapter = new CountryAdapter();
        binding.recyclerCountry.setAdapter(countryAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        // TODO: Use the ViewModel
    }

}
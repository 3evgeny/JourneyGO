package com.melself.journeygo.ui.views;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.databinding.FragmentCountryBinding;
import com.melself.journeygo.ui.Adapters.CountryAdapter;
import com.melself.journeygo.ui.viewmodels.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class CountryFragment extends Fragment {

    private CountryViewModel mViewModel;
    FragmentCountryBinding binding;
    CountryAdapter countryAdapter;

    private List<Country> itemList = new ArrayList<>();

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
        countryAdapter = new CountryAdapter(getContext());
        binding.recyclerCountry.setAdapter(countryAdapter);

        binding.searchCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        if (mViewModel.getCredoId(MainActivity.user_id).getRole() == 1){
            binding.addNewCountry.setVisibility(View.VISIBLE);
            binding.newDescription.setVisibility(View.VISIBLE);
            binding.newCountry.setVisibility(View.VISIBLE);

            binding.addNewCountry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.insert(new Country(0,
                            binding.newCountry.getText().toString(),
                            binding.newDescription.getText().toString(),
                            "0"));
                }
            });
        }else {
            binding.addNewCountry.setVisibility(View.GONE);
            binding.newDescription.setVisibility(View.GONE);
            binding.newCountry.setVisibility(View.GONE);
        }

        mViewModel.getAllCountriesFromView().observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                countryAdapter.setCountry(countries);
                itemList.addAll(countries);
            }
        });
    }

    private void filter(String text) {
        List<Country> filterList = new ArrayList<>();
        for (Country item : itemList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        countryAdapter.filterList(filterList);
    }
}
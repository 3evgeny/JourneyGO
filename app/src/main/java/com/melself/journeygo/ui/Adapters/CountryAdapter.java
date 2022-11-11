package com.melself.journeygo.ui.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.databinding.ListCountryBinding;
import com.melself.journeygo.ui.views.HotelFragment;

import java.util.ArrayList;
import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> data = new ArrayList<>();
    private Context context;
    Bundle bundle;

    public CountryAdapter(Context context) {
        this.context = context;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        ListCountryBinding binding;

        public CountryViewHolder(ListCountryBinding item) {
            super(item.getRoot());
            binding = item;
        }

        public void bindView(Country country){
            binding.nameCountryList.setText(country.getName());
            binding.descriptionCountryList.setText(country.getDescription());
            binding.checkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle = new Bundle();
                    bundle.putString("nameCountry", country.getName());
                    replaceFragment(new HotelFragment());
                }
            });
        }
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListCountryBinding binding = ListCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        Country country = data.get(position);
        holder.bindView(country);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setCountry(List<Country> country){
        this.data = country;
        notifyDataSetChanged();
    }

    public void filterList(List<Country> filterList) {
        data = filterList;
        notifyDataSetChanged();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment.setArguments(bundle);
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}

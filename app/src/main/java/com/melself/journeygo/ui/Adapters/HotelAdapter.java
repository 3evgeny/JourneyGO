package com.melself.journeygo.ui.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Hotel;
import com.melself.journeygo.databinding.ListHotelBinding;
import com.melself.journeygo.ui.views.BuyFragment;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotels = new ArrayList<>();
    private Context context;
    Bundle bundleHotel;

    public HotelAdapter(Context context) {
        this.context = context;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {

        ListHotelBinding binding;

        public HotelViewHolder(ListHotelBinding item) {
            super(item.getRoot());
            binding = item;
        }

        public void bindView(Hotel hotel){
            binding.nameHotelList.setText(hotel.getName());
            binding.descriptionHotelList.setText(hotel.getDescription());
            binding.priceHotelList.setText(hotel.getPrice());
            binding.moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundleHotel = new Bundle();
                    bundleHotel.putString("11", hotel.getName());
                    bundleHotel.putString("12", hotel.getDescription());
                    bundleHotel.putString("13", hotel.getPrice());
                    replaceFragment(new BuyFragment());
                }
            });
        }
    }

    @NonNull
    @Override
    public HotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListHotelBinding binding = ListHotelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HotelViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.HotelViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.bindView(hotel);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setHotels(List<Hotel> hotels){
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment.setArguments(bundleHotel);
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}

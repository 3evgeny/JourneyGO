package com.melself.journeygo.ui.views;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Ticket;
import com.melself.journeygo.databinding.FragmentBuyBinding;
import com.melself.journeygo.ui.viewmodels.BuyViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BuyFragment extends Fragment {

    private BuyViewModel mViewModel;
    FragmentBuyBinding binding;

    private String nameHotel;
    private String descriptionHotel;
    private int priceHotel;

    long startMillis = 0;
    long endMillis = 0;

    String dateStart;
    String dateEnd;
    String dateDb;


    public static BuyFragment newInstance() {
        return new BuyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBuyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BuyViewModel.class);

        Bundle bundleHotel = this.getArguments();
        nameHotel = bundleHotel.getString("11");
        descriptionHotel = bundleHotel.getString("12");
        priceHotel = Integer.parseInt(bundleHotel.getString("13"));

        binding.hotelName.setText(nameHotel);
        binding.descriptionHotelBuy.setText(descriptionHotel);

        final Calendar calendar = Calendar.getInstance();
        final int year1 = calendar.get(Calendar.YEAR);
        final int month1 = calendar.get(Calendar.MONTH);
        final int day1 = calendar.get(Calendar.DAY_OF_MONTH);

        binding.checkInSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month1+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        dateStart = dayOfMonth+"/"+month+"/"+year;
                        binding.checkInSelect.setText(date);

                        Calendar beginTime = Calendar.getInstance();
                        beginTime.set(year, month-1, dayOfMonth, 0, 0);
                        startMillis = beginTime.getTimeInMillis();


                    }
                },year1, month1, day1);
                dialog.show();
            }
        });

        binding.checkOutSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month1+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        dateEnd= dayOfMonth+"/"+month+"/"+year;
                        binding.checkOutSelect.setText(date);

                        Calendar endTime = Calendar.getInstance();
                        endTime.set(year, month-1, dayOfMonth, 0, 0);
                        endMillis = endTime.getTimeInMillis();

                    }
                },year1, month1, day1);
                dialog.show();
            }
        });

        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContext().getContentResolver();
                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, startMillis);
                values.put(CalendarContract.Events.DTEND, endMillis);
                values.put(CalendarContract.Events.TITLE, "Отель");
                values.put(CalendarContract.Events.DESCRIPTION, "Дата заселения и выселения");
                values.put(CalendarContract.Events.CALENDAR_ID, 3);
                values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

                dateDb = dateStart + " - " + dateEnd;
                System.out.println(descriptionHotel);
                mViewModel.insert(new Ticket(0, "12345", descriptionHotel, nameHotel, dateDb, "Оплачено"));

            }
        });

    }


}
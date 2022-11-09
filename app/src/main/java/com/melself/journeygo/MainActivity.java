package com.melself.journeygo;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.melself.journeygo.databinding.ActivityMainBinding;
import com.melself.journeygo.ui.views.ChatFragment;
import com.melself.journeygo.ui.views.CountryFragment;
import com.melself.journeygo.ui.views.HotelFragment;
import com.melself.journeygo.ui.views.ProfileFragment;
import com.melself.journeygo.ui.views.TicketFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private Context context;

    final int callbackId = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        checkPermission(callbackId, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR);


        replaceFragment(new CountryFragment());
        binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.country:
                        replaceFragment(new CountryFragment());
                        break;
                    case R.id.chat:
                        replaceFragment(new ChatFragment());
                        break;
                    case R.id.ticket:
                        replaceFragment(new TicketFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }

    private void checkPermission(int callbackId, String... permissionsId) {
        boolean permissions = true;
        for (String p : permissionsId) {
            permissions = permissions && ContextCompat.checkSelfPermission(this, p) == PERMISSION_GRANTED;
        }

        if (!permissions)
            ActivityCompat.requestPermissions(this, permissionsId, callbackId);
    }
}
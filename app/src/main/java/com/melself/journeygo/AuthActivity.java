package com.melself.journeygo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.melself.journeygo.databinding.ActivityAuthBinding;
import com.melself.journeygo.ui.views.SignInFragment;

import java.util.List;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final Intent intent = getIntent();
        final String action = intent.getAction();
        final String data = intent.getDataString();

        replaceFragment(new SignInFragment());

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameAuth, fragment);
        ft.commit();
    }

}
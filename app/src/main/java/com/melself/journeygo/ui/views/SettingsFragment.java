package com.melself.journeygo.ui.views;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentProfileBinding;
import com.melself.journeygo.databinding.FragmentSettingsBinding;
import com.melself.journeygo.ui.viewmodels.SettingsViewModel;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    FragmentSettingsBinding binding;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        mViewModel.getProfileFromView(1).observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                binding.editName.setText(profile.getFirstName());
                binding.editLastName.setText(profile.getLastName());
                binding.editPatronymic.setText(profile.getPatronymic());
            }
        });

        binding.saveProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.getProfileFromView(1).observe(getViewLifecycleOwner(), new Observer<Profile>() {
                    @Override
                    public void onChanged(Profile profile) {
                        profile.setFirstName(binding.editName.getText().toString());
                        profile.setLastName(binding.editLastName.getText().toString());
                        profile.setPatronymic(binding.editPatronymic.getText().toString());
                        mViewModel.update(profile);
                    }
                });
            }
        });
    }

}
package com.melself.journeygo.ui.views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentProfileBinding;
import com.melself.journeygo.ui.viewmodels.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    FragmentProfileBinding binding;
    Bundle bundle;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.editMainPassport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("key", 1);
                replaceFragment(new AddPassportFragment());
            }
        });

        binding.editInterPassport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("key", 2);
                replaceFragment(new AddPassportFragment());
            }
        });

        binding.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SettingsFragment());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getProfileFromView(1).observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                binding.fioProfile.setText(profile.getFirstName() + " " + profile.getLastName() + " " + profile.getPatronymic());
                binding.ageProfile.setText(profile.getAge());
                binding.typeMainPassport.setText(profile.getMainPassport());
                binding.initialMainPassport.setText(profile.getMainInitialPassport());
                binding.numberMainPassport.setText(profile.getMainNumberPassport());
                binding.typeInterPassport.setText(profile.getInterPassport());
                binding.initialInterPassport.setText(profile.getInterInitialPassport());
                binding.numberInterPassport.setText(profile.getInterNumberPassport());
            }
        });


    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment.setArguments(bundle);
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}
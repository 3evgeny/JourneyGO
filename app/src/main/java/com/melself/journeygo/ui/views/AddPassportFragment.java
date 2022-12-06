package com.melself.journeygo.ui.views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentAddPassportBinding;
import com.melself.journeygo.databinding.FragmentProfileBinding;
import com.melself.journeygo.ui.viewmodels.AddPassportViewModel;

public class AddPassportFragment extends Fragment {

    private AddPassportViewModel mViewModel;
    FragmentAddPassportBinding binding;

    private int mode;

    public static AddPassportFragment newInstance() {
        return new AddPassportFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddPassportBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddPassportViewModel.class);
        // TODO: Use the ViewModel

        Bundle bundle = this.getArguments();
        if (bundle != null){
            mode = bundle.getInt("key", 0);
        }

        binding.savePassportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == 0){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                else if (mode == 1){
                    mViewModel.getProfileFromView(MainActivity.user_id).observe(getViewLifecycleOwner(), new Observer<Profile>() {
                        @Override
                        public void onChanged(Profile profile) {
                            profile.setMainPassport(binding.editNamePassport.getText().toString());
                            profile.setMainInitialPassport(binding.editInitialsPassport.getText().toString());
                            profile.setMainNumberPassport(binding.editNumberPassport.getText().toString());
                            mViewModel.update(profile);
                            replaceFragment(new ProfileFragment());
                        }
                    });
                }
                else if (mode == 2){
                    mViewModel.getProfileFromView(MainActivity.user_id).observe(getViewLifecycleOwner(), new Observer<Profile>() {
                        @Override
                        public void onChanged(Profile profile) {
                            profile.setInterPassport(binding.editNamePassport.getText().toString());
                            profile.setInterInitialPassport(binding.editInitialsPassport.getText().toString());
                            profile.setInterNumberPassport(binding.editNumberPassport.getText().toString());
                            mViewModel.update(profile);
                            replaceFragment(new ProfileFragment());
                        }
                    });
                }
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}
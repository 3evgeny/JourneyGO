package com.melself.journeygo.ui.views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.AuthActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.ConfigUser;
import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentVkSignUpBinding;
import com.melself.journeygo.ui.viewmodels.VkSignUpViewModel;

import java.util.List;

public class VkSignUpFragment extends Fragment {

    private VkSignUpViewModel mViewModel;
    FragmentVkSignUpBinding binding;

    public static VkSignUpFragment newInstance() {
        return new VkSignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVkSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VkSignUpViewModel.class);

        mViewModel.getAllProfileView().observe(getViewLifecycleOwner(), new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                if (profiles == null){
                    AuthActivity.maxID = 1;
                }else {
                    AuthActivity.maxID = profiles.size();
                }
            }
        });

        Profile data = new Profile();
        mViewModel.getInfoProfile(ConfigUser.getInstance().access_token, ConfigUser.getInstance().user_id).observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                binding.lastname.setText(profile.getLastName());
                data.setFirstName(profile.getFirstName());
                data.setLastName(profile.getLastName());
                data.setAge(profile.getAge());

            }
        });

        DBCredo credo = new DBCredo();
        mViewModel.getUsernameVk(ConfigUser.getInstance().access_token, ConfigUser.getInstance().user_id).observe(getViewLifecycleOwner(), new Observer<DBCredo>() {
            @Override
            public void onChanged(DBCredo dbCredo) {
                System.out.println(dbCredo.getUsername());
                credo.setUsername(dbCredo.getUsername());
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.insertProfile(new Profile(0, data.getFirstName(),data.getLastName(),"",data.getAge(),"","","","","",""));
                mViewModel.insert(new DBCredo(0, AuthActivity.maxID,
                        credo.getUsername(),
                        ConfigUser.getInstance().email,
                        "", 0));
                replaceFragment(new SignInFragment());
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameAuth, fragment);
        ft.commit();
    }
}
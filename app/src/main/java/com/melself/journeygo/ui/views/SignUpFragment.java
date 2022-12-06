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
import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentSignUpBinding;
import com.melself.journeygo.ui.viewmodels.SignUpViewModel;

import java.util.List;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    FragmentSignUpBinding binding;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignUpContactFragment());
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getAllProfileView().observe(getViewLifecycleOwner(), new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                if (profiles == null){
                    AuthActivity.maxID = profiles.size();
                }else {
                    AuthActivity.maxID = profiles.size() + 1;
                }
            }
        });


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewModel.insertProfileCredo(new Profile(0, "","","","","","","","","",""),
                        new DBCredo(0, AuthActivity.maxID,
                                binding.usernameSignUp.getText().toString(),
                                binding.emailSignUp.getText().toString(),
                                binding.passwordSignUp.getText().toString(), 0));

//                mViewModel.insertProfile(new Profile(0, "","","","","","","","","",""));
//
//                mViewModel.insert(new DBCredo(0, AuthActivity.maxID,
//                        binding.usernameSignUp.getText().toString(),
//                        binding.emailSignUp.getText().toString(),
//                        binding.passwordSignUp.getText().toString(), 0));


                replaceFragment(new SignUpContactFragment());
            }
        });

        binding.vkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new VKFragmentUp());
            }
        });

        binding.yesAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
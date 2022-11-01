package com.melself.journeygo.ui.views;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentSignUpContactBinding;
import com.melself.journeygo.ui.viewmodels.SignUpContactViewModel;

import java.util.List;

public class SignUpContactFragment extends Fragment {

    private SignUpContactViewModel mViewModel;
    FragmentSignUpContactBinding binding;

    public static SignUpContactFragment newInstance() {
        return new SignUpContactFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpContactBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpContactViewModel.class);
        // TODO: Use the ViewModel

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.insert(
                        new Profile(
                                0,
                                binding.firstNameSignUp.getText().toString(),
                                binding.lastNameSignUp.getText().toString(),
                                binding.patronymicSignUp.getText().toString(),
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                                )
                        )   ;
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
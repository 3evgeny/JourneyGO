package com.melself.journeygo.ui.views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentSignInBinding;
import com.melself.journeygo.ui.viewmodels.SignInViewModel;

public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    FragmentSignInBinding binding;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.noAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignUpFragment());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        // TODO: Use the ViewModel
        binding.vkSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new VKFragment());
            }
        });

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mViewModel.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<Profile>>() {
//                    @Override
//                    public void onChanged(List<Profile> profiles) {
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                            Profile user = profiles.stream().filter(u -> binding.usernameSignIn.getText().toString().equals(u.getFirstName())).findFirst().orElse(null);
//                            if (user.getFirstName().equals(binding.usernameSignIn.getText().toString())){
//                                long id = user.getId();
//                                Intent intent = new Intent(getContext(), MainActivity.class);
//                                intent.putExtra("user_id", id);
//                                startActivity(intent);
//                            }
//                        }
//                    }
//                });




                DBCredo credo = mViewModel.getUsr(binding.usernameSignIn.getText().toString());

                if (credo == null){
                    Toast.makeText(getContext(), "Такого юзера нет", Toast.LENGTH_SHORT).show();
                }else {
                    if (credo.getPassword().equals(binding.passwordSignIn.getText().toString())){
                        long id = credo.getId();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra("user_id", id);
                        startActivity(intent);
                    }
                }

//                Profile user = mViewModel.getFirstNameView(binding.usernameSignIn.getText().toString());
//                if (user == null){
//                    Toast.makeText(getContext(), "Такого юзера нет", Toast.LENGTH_SHORT).show();
//                }else {
//                    long id = user.getId();
//                    Intent intent = new Intent(getContext(), MainActivity.class);
//                    intent.putExtra("user_id", id);
//                    startActivity(intent);
//                }

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
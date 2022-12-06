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
import com.melself.journeygo.data.ConfigUser;
import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.databinding.FragmentVkSignInBinding;
import com.melself.journeygo.ui.viewmodels.VkSignInViewModel;

public class VkSignInFragment extends Fragment {

    private VkSignInViewModel mViewModel;
    FragmentVkSignInBinding binding;
    String url;

    DBCredo credo;

    public static VkSignInFragment newInstance() {
        return new VkSignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVkSignInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VkSignInViewModel.class);

        mViewModel.getInfoProfile(ConfigUser.getInstance().access_token, ConfigUser.getInstance().user_id).observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                binding.test.setText(profile.getLastName());
            }
        });

        mViewModel.getUsernameVk(ConfigUser.getInstance().access_token, ConfigUser.getInstance().user_id).observe(getViewLifecycleOwner(), new Observer<DBCredo>() {
            @Override
            public void onChanged(DBCredo dbCredo) {
                credo = mViewModel.getUsr(dbCredo.getUsername());

                if (credo == null){
                    Toast.makeText(getContext(), "Такого юзера нет", Toast.LENGTH_SHORT).show();
                    replaceFragment(new SignUpFragment());
                }else {
                    long id = credo.getId();
                    System.out.println(id);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("user_id", id);
                    startActivity(intent);
                }
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
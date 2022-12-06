package com.melself.journeygo.ui.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melself.journeygo.MainActivity;
import com.melself.journeygo.R;
import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.databinding.FragmentAdminBinding;
import com.melself.journeygo.databinding.FragmentCountryBinding;
import com.melself.journeygo.ui.Adapters.AdminAdapter;
import com.melself.journeygo.ui.Adapters.CountryAdapter;
import com.melself.journeygo.ui.viewmodels.AdminViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {

    private AdminViewModel mViewModel;
    List<DBCredo> users = new ArrayList<>();
    FragmentAdminBinding binding;
    AdminAdapter adminAdapter;

    int role;
    long user_id;
    String username, email, password;

    public static AdminFragment newInstance() {
        return new AdminFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.usersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adminAdapter = new AdminAdapter();
        binding.usersRecycler.setAdapter(adminAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        // TODO: Use the ViewModel
        users = mViewModel.getAllCredo();
        adminAdapter.setUsers(users);

        binding.saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = adminAdapter.getRole();
                user_id = adminAdapter.getUser_id();
                username = adminAdapter.getUsername();
                email = adminAdapter.getEmail();
                password = adminAdapter.getPassword();
                mViewModel.update(new DBCredo(user_id, user_id, username, email, password, role));
            }
        });

    }

}
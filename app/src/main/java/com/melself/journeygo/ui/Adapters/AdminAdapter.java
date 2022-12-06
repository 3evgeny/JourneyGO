package com.melself.journeygo.ui.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.databinding.ListUserBinding;
import com.melself.journeygo.ui.views.HotelFragment;

import java.util.ArrayList;
import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private List<DBCredo> data = new ArrayList<>();
    Bundle bundle;

    long user_id;
    int role;
    String username, email, password;

    @NonNull
    @Override
    public AdminAdapter.AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListUserBinding binding = ListUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdminViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.AdminViewHolder holder, int position) {
        DBCredo dbCredo = data.get(position);
        holder.bindView(dbCredo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setUsers(List<DBCredo> dbCredo){
        this.data = dbCredo;
        notifyDataSetChanged();
    }

    public int getRole(){
        return role;
    }

    public long getUser_id(){
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        ListUserBinding binding;

        public AdminViewHolder(ListUserBinding item) {
            super(item.getRoot());
            binding = item;
        }

        public void bindView(DBCredo dbCredo){
            binding.userNameList.setText(dbCredo.getUsername());
            binding.roleET.setText(String.valueOf(dbCredo.getRole()));

            bundle = new Bundle();
            binding.updateUserList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user_id = dbCredo.getId();
                    role = Integer.parseInt(binding.roleET.getText().toString());
                    username = dbCredo.getUsername();
                    email = dbCredo.getEmail();
                    password = dbCredo.getPassword();
                }
            });
        }
    }


}

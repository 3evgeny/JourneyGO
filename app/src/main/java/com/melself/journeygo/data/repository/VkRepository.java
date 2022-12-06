package com.melself.journeygo.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.data.vk.VkMainResponse;
import com.melself.journeygo.data.vk.VkService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VkRepository {
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.vk.com/method/")
            .build();
    private VkService vkService = retrofit.create(VkService.class);

    public LiveData<Profile> getInfoProfile(String token, String userId){

        MutableLiveData<Profile> result = new MutableLiveData<>();
        Profile profile = new Profile();

        vkService.getDataUser(token, userId).enqueue(new Callback<VkMainResponse>() {
            @Override
            public void onResponse(Call<VkMainResponse> call, Response<VkMainResponse> response) {
                if (response.isSuccessful()){

                    profile.setFirstName(response.body().response.first_name);
                    profile.setLastName(response.body().response.last_name);
                    profile.setAge(response.body().response.bdate);
                    result.setValue(profile);
                }
            }

            @Override
            public void onFailure(Call<VkMainResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result;
    }

    public LiveData<DBCredo> getUsername(String token, String userId){
        MutableLiveData<DBCredo> result = new MutableLiveData<>();
        DBCredo dbCredo = new DBCredo();

        vkService.getDataUser(token, userId).enqueue(new Callback<VkMainResponse>() {
            @Override
            public void onResponse(Call<VkMainResponse> call, Response<VkMainResponse> response) {
                if (response.isSuccessful()){
                    dbCredo.setUsername(response.body().response.screen_name);
                    result.setValue(dbCredo);
                }
            }

            @Override
            public void onFailure(Call<VkMainResponse> call, Throwable t) {

            }
        });
        return result;
    }

}

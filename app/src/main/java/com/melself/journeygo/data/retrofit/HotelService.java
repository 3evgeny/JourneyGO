package com.melself.journeygo.data.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HotelService {

    @GET("v2/cache.json?currency=rub&checkIn=2022-12-10&checkOut=2022-12-12&limit=10")
    Call<List<Root>> listHotelAndPrice(@Query("location") String location);
}

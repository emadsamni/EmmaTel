package com.example.emma_tel.api;





import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Category;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Notification;
import com.example.emma_tel.models.Offer;
import com.example.emma_tel.models.Page;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getMobiles")
    Call<ApiResponse<List<Mobile>>> getMobiles(@Query("key") String key);

    @GET("getMainSlider")
    Call<ApiResponse<List<MainSlider>>> getMainSlider(@Query("key") String key);

    @GET("getOffers")
    Call<ApiResponse<List<Offer>>> getOffers(@Query("key") String key);

    @GET("getNotifications")
    Call<ApiResponse<List<Notification>>> getNotifications(@Query("key") String key);

    @GET("getBranches")
    Call<ApiResponse<List<Branch>>> getBranches(@Query("key") String key);

    @GET("getPages")
    Call<ApiResponse<List<Page>>> getPages(@Query("key") String key);

    @GET("getCompanies")
    Call<ApiResponse<List<Company>>> getCompanies(@Query("key") String key);

    @GET("getCategories")
    Call<ApiResponse<List<Category>>> getCategories(@Query("key") String key);

    @GET("getAccessories")
    Call<ApiResponse<List<Accessory>>> getAccessories(@Query("key") String key);





}


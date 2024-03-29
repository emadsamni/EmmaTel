package com.example.emma_tel.api;





import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Category;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.Complaint;
import com.example.emma_tel.models.EMedia;
import com.example.emma_tel.models.Event;
import com.example.emma_tel.models.FacebookUser;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Notification;
import com.example.emma_tel.models.Offer;
import com.example.emma_tel.models.Page;
import com.example.emma_tel.models.Tablet;
import com.example.emma_tel.models.Tips;
import com.example.emma_tel.models.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getMobiles")
    Call<ApiResponse<List<Mobile>>> getMobiles(@Query("key") String key);


    @GET("getTips")
    Call<ApiResponse<List<Tips>>> getTips(@Query("key") String key , @Query("name") String name);

    @GET("getTablets")
    Call<ApiResponse<List<Tablet>>> getTablets (@Query("key") String key);
    @GET("getMainSlider")
    Call<ApiResponse<List<MainSlider>>> getMainSlider(@Query("key") String key);

    @GET("getOffers")
    Call<ApiResponse<List<Offer>>> getOffers(@Query("key") String key);

    @GET("getEvents")
    Call<ApiResponse<List<Event>>> getEvents(@Query("key") String key);

    @GET("getNotifications")
    Call<ApiResponse<List<Notification>>> getNotifications(@Query("key") String key);

    @GET("getBranches")
    Call<ApiResponse<List<Branch>>> getBranches(@Query("key") String key);

    @GET("getPages")
    Call<ApiResponse<List<Page>>> getPages(@Query("key") String key);


    @GET("getVideos")
    Call<ApiResponse<List<EMedia>>> getMedia(@Query("key") String key);

    @GET("getPage")
    Call<ApiResponse<Page>> getPage(@Query("key") String key ,@Query("name") String name) ;

    @GET("getCompanies")
    Call<ApiResponse<List<Company>>> getCompanies(@Query("key") String key);

    @GET("getCategories")
    Call<ApiResponse<List<Category>>> getCategories(@Query("key") String key);

    @GET("getAccessories")
    Call<ApiResponse<List<Accessory>>> getAccessories(@Query("key") String key);

    @POST("addUser")
    Call<ApiResponse<User>> addUser(@Query("key") String key ,@Query("phone") String phone ,@Query("password") String password  );

    @POST("verifyUser")
    Call<ApiResponse<User>> verifyUser(@Query("key") String key ,@Query("phone") String phone ,@Query("verification_code") String verification_code  );

    @POST("login")
    Call<ApiResponse<User>> login(@Query("key") String key ,@Query("phone") String phone ,@Query("password") String password );

    @POST("addFacebookUser")
    Call<ApiResponse<FacebookUser>> addFacebookUser(@Query("key") String key ,@Query("token") String token, @Query("facebook_id") String facebook_id , @Query("name") String name ,  @Query("email") String email);

    @POST("updateUser")
    Call<ApiResponse<User>> updateUser(@Query("key") String key ,@Query("token") String token , @Query("full_name") String name);


    @POST("addComplaint")
    Call<ApiResponse<Complaint>> send(@Query("key") String key , @Query("token") String token , @Query("content") String message);




}



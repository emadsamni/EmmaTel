package com.example.emma_tel.repositories;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.activites.RegisterActivity;
import com.example.emma_tel.api.ApiClient;
import com.example.emma_tel.api.ApiInterface;
import com.example.emma_tel.api.ApiResponse;
import com.example.emma_tel.api.CallbackWithRetry;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.models.Complaint;
import com.example.emma_tel.models.FacebookUser;
import com.example.emma_tel.models.User;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.ProgressDialog;
import com.example.emma_tel.utils.Utils;

import java.util.Date;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Response;

public class LoginRepository {
    private Application application;
    private CustomerUtils customerUtils;

    private LoginRepository(Application application){
        this.application = application;
        this.customerUtils = CustomerUtils.getInstance(application);
    }

    public static LoginRepository getInstance(Application application){
        return new LoginRepository(application);
    }

    public void registerUser(Context context , String phone , String password){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<User>> call=apiService.addUser(Constants.API_KEY ,phone,password);
        call.enqueue(new CallbackWithRetry<ApiResponse<User>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                if (! response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toasty.custom(application,R.string.user_exsist,null,
                            application.getResources().getColor(R.color.colorAccent),Constants.TOAST_TIME,false,true).show();
                    return;
                }
                 ProgressDialog.getInstance().cancel();
                ((RegisterActivity) context).setPhone(phone);
                ((RegisterActivity) context).next();
                Date date = new Date(System.currentTimeMillis());
                CustomerUtils.getInstance(context).addLong(Constants.PREF_TIM,date.getTime());
            }
            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                Utils.getInstance(application).problemWithConnection(application);
            }
        });


    }

    public void verifyUser(Context context , String phone , String verification_code){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<User>> call=apiService.verifyUser(Constants.API_KEY ,phone,verification_code);
        call.enqueue(new CallbackWithRetry<ApiResponse<User>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                if (! response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toasty.custom(application,R.string.code_error,null,
                            application.getResources().getColor(R.color.colorAccent),Constants.TOAST_TIME,false,true).show();
                    return;
                }

                 ProgressDialog.getInstance().cancel();
                 String token = response.body().getData().getToken();
                 customerUtils.addString(Constants.PREF_TOKEN, token);
                if ( response.body().getData().getFull_name()!= null )
                    customerUtils.addString(Constants.PREF_Phone, response.body().getData().getPhone());
                else
                    customerUtils.addString(Constants.PREF_Phone,"");
                if ( response.body().getData().getFull_name()!= null ) {
                    customerUtils.addString(Constants.PREF_Name, response.body().getData().getFull_name());
                }
                else
                {
                    customerUtils.addString(Constants.PREF_Name, "");
                }
                customerUtils.addString(Constants.PREF_Phone, response.body().getData().getPhone());
                ((RegisterActivity) context).next();

            }
            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                Utils.getInstance(application).problemWithConnection(application);
            }
        });


    }

    public void login(Context context , String phone , String password){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<User>> call=apiService.login(Constants.API_KEY ,phone,password);
        call.enqueue(new CallbackWithRetry<ApiResponse<User>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                if (! response.isSuccessful()){

                    ProgressDialog.getInstance().cancel();
                    String message =ApiClient.getErrorMessage(response,context);
                    if (message.equals("invalid data") || message.equals("invaild password"))
                         Toasty.custom(application,R.string.login_failed,null,
                                 application.getResources().getColor(R.color.colorPrimary),Constants.TOAST_TIME,false,true).show();
                    return;
                }
                ProgressDialog.getInstance().cancel();
                String token = response.body().getData().getToken();
                customerUtils.addString(Constants.PREF_TOKEN, token);
                if ( response.body().getData().getFull_name()!= null )
                     customerUtils.addString(Constants.PREF_Phone, response.body().getData().getPhone());
                else
                    customerUtils.addString(Constants.PREF_Phone,"");
                if ( response.body().getData().getFull_name()!= null ) {
                    customerUtils.addString(Constants.PREF_Name, response.body().getData().getFull_name());
                }
                else
                {
                    customerUtils.addString(Constants.PREF_Name, "");
                }
                Intent toMain = new Intent(context, MainActivity.class);
                toMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(toMain);
                ((Activity)context).finish();

            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                Utils.getInstance(application).problemWithConnection(application);
            }
        });


    }

    public void addFacebookUser(Context context , String facebook_id , String name , String email){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<FacebookUser>> call=apiService.addFacebookUser(Constants.API_KEY , customerUtils.getString(Constants.PREF_TOKEN),facebook_id,name ,email);
        call.enqueue(new CallbackWithRetry<ApiResponse<FacebookUser>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<FacebookUser>> call, Response<ApiResponse<FacebookUser>> response) {
                if (! response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    return;
                }
                ProgressDialog.getInstance().cancel();
            }
            @Override
            public void onFailure(Call<ApiResponse<FacebookUser>> call, Throwable t) {
                Utils.getInstance(application).problemWithConnection(application);
            }
        });
    }
    public void updateUser(Context context , String name){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<User>> call=apiService.updateUser(Constants.API_KEY , customerUtils.getString(Constants.PREF_TOKEN),name);

        call.enqueue(new CallbackWithRetry<ApiResponse<User>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                if (! response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    return;
                }
                if ( response.body().getData().getFull_name()!= null ) {
                    customerUtils.addString(Constants.PREF_Name, response.body().getData().getFull_name());
                }
                else
                {
                    customerUtils.addString(Constants.PREF_Name, "");
                }
                    Toast.makeText(context, context.getResources().getString(R.string.edit_completed), Toast.LENGTH_SHORT).show();
                    ProgressDialog.getInstance().cancel();
            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                Utils.getInstance(application).problemWithConnection(application);
            }
        });


    }

    public void send(Context context , String message){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<Complaint>> call=apiService.send(Constants.API_KEY , customerUtils.getString(Constants.PREF_TOKEN),message);
        call.enqueue(new CallbackWithRetry<ApiResponse<Complaint>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<Complaint>> call, Response<ApiResponse<Complaint>> response) {
                if (! response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    return;
                }
                Toasty.custom(application,R.string.message_sended,null,
                        application.getResources().getColor(R.color.colorAccent),Constants.TOAST_TIME,false,true).show();
                ProgressDialog.getInstance().cancel();
            }

            @Override
            public void onFailure(Call<ApiResponse<Complaint>> call, Throwable t) {
                ProgressDialog.getInstance().cancel();
            }
        });



    }
}

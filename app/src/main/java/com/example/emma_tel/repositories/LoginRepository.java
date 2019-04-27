package com.example.emma_tel.repositories;

import android.app.Application;
import android.content.Context;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.RegisterActivity;
import com.example.emma_tel.api.ApiClient;
import com.example.emma_tel.api.ApiInterface;
import com.example.emma_tel.api.ApiResponse;
import com.example.emma_tel.api.CallbackWithRetry;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.models.User;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.ProgressDialog;
import com.example.emma_tel.utils.Utils;

import java.util.List;

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
                    Toasty.custom(application,R.string.unexpected_api_error,null,
                            application.getResources().getColor(R.color.colorPrimary),Constants.TOAST_TIME,false,true).show();
                    return;
                }

                ProgressDialog.getInstance().cancel();
                ((RegisterActivity) context).setPhone(phone);
                ((RegisterActivity) context).next();
            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                Utils.getInstance(application).problemWithConnection(application);
            }
        });


    }

}

package com.example.emma_tel.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import com.example.emma_tel.activites.LoginActivity;
import com.example.emma_tel.repositories.LoginRepository;
import com.example.emma_tel.utils.ProgressDialog;



public class LoginViewModel extends AndroidViewModel {
    private LoginRepository repository;

    public LoginViewModel( Application application) {
        super(application);
        repository = LoginRepository.getInstance(application);
    }
    public void registerUser(Context context,  String phone , String password){
         ProgressDialog.getInstance().show(context);
         repository.registerUser(context,phone,password);
    }
    public void verifyUser(Context context,  String phone , String verification_code){
        ProgressDialog.getInstance().show(context);
        repository.verifyUser(context,phone,verification_code);
    }
    public void login(Context context,  String phone , String password){
        ProgressDialog.getInstance().show(context);
        repository.login(context,phone,password);
    }

    public void addFacebookUser(Context context,  String facebookId , String name , String email){
        ProgressDialog.getInstance().show(context);
        repository.addFacebookUser(context,facebookId,name ,email);
    }
    public void     updatekUser(Context context,  String name ){
        ProgressDialog.getInstance().show(context);
        repository.updateUser(context,name);
    }
    public void     send(Context context,  String message ){
        ProgressDialog.getInstance().show(context);
        repository.send(context,message);
    }
}

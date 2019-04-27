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
}

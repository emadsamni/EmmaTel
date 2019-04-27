package com.example.emma_tel.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.RegisterActivity;
import com.example.emma_tel.activites.StepperListener;
import com.example.emma_tel.viewmodels.LoginViewModel;


public class RegisterFragment extends Fragment  implements StepperListener {
    LoginViewModel loginViewModel;



    public RegisterFragment() {
    }


    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_register, container,false);
       assignUIReference();
        return  view;

    }

    public void assignUIReference()
    {
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
    }

    @Override
    public void onNextClicked() {

    }

    @Override
    public void onBackClicked() {

    }

    private void register(String phone ,  String password ){

        loginViewModel.registerUser(getActivity(),phone,password );
    }
}

package com.example.emma_tel.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.RegisterActivity;
import com.example.emma_tel.activites.StepperListener;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.helprs.MatrialEditText;
import com.example.emma_tel.utils.Utils;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment  implements StepperListener {
    LoginViewModel loginViewModel;
    EditText phoneText;
    EditText passwordText,confirmPasswordText;
    TextView phoneTextError ,passwordTextError,confirmPasswordTextError;
    CustomerUtils customerUtils;
    Utils utils;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register, container,false);
         assignUIReference(view);
        return  view;

    }

    public void assignUIReference(View view)
    {
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        confirmPasswordText= view.findViewById(R.id.confirm_password);
        passwordText = view.findViewById(R.id.password);
        phoneText =view.findViewById(R.id.phone);
        phoneTextError =view.findViewById(R.id.phone_error);
        passwordTextError =view.findViewById(R.id.password_error);
        confirmPasswordTextError =view.findViewById(R.id.confirm_password_error);
        customerUtils = CustomerUtils.getInstance(getActivity());
        utils = Utils.getInstance(getActivity());
        confirmPasswordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               confirmPasswordTextError.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordTextError.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               phoneTextError.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onNextClicked() {
       if (isInputValidation())
       {
           register(phoneText.getText().toString(),passwordText.getText().toString());
       }
    }

    @Override
    public void onBackClicked() {

    }
    private boolean isInputValidation() {
        boolean check = true;


        if(!utils.notEmpty(passwordText))
        {
            passwordTextError.setText(getResources().getString(R.string.passwordEmpty));
            check =false;
        }
        else
        {
            if(!utils.notEmpty(confirmPasswordText))
            {
                confirmPasswordTextError.setText(getResources().getString(R.string.confirmPasswordEmpty));
                check =false;
            }
            else
            {
                if(!passwordText.getText().toString().equals(confirmPasswordText.getText().toString()))
                {
                    confirmPasswordTextError.setText(getResources().getString(R.string.confirmNewPasswordError));
                    passwordTextError.setText(getResources().getString(R.string.confirmNewPasswordError));
                    check =false;
                }
            }

        }

        if (!utils.notEmpty(phoneText)){
            phoneTextError.setText(getResources().getString(R.string.phoneEmpty));
            check =false;
        }else if(!((phoneText.getText().toString().length() == 10 ||phoneText.getText().toString().length() == 9)  &&
                isValidPhone(phoneText.getText().toString()) ) )
        {
            phoneTextError.setText(getResources().getString(R.string.phoneInvalidMessage));
            check =false;
        }

        return check;
    }
    private void register(String phone ,  String password ){

        loginViewModel.registerUser(getActivity(),phone,password );
    }

    public static boolean isValidPhone(String phone)
    {
        String expression = "^(09|9)[0-9]{8}";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}

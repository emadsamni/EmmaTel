package com.example.emma_tel.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.StepperListener;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.Utils;
import com.example.emma_tel.viewmodels.LoginViewModel;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment  implements StepperListener {
    LoginViewModel loginViewModel;
    EditText phoneText;
    EditText passwordText,confirmPasswordText;
    TextView phoneTextError ,passwordTextError,confirmPasswordTextError;
    CustomerUtils customerUtils;
    Utils utils;
    Boolean checkTimer = false;
    TextView timerView;

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
        timerView =view.findViewById(R.id.timer);
        setTimer();
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
        if (!checkTimer) {
            if (isInputValidation()) {
                register(phoneText.getText().toString(), passwordText.getText().toString());
            }
        } else {
            Toast.makeText(getActivity(), "Wating timer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackClicked() {

    }
    private boolean isInputValidation() {
        boolean check = true;


        if(utils.notEmpty(passwordText))
        {
            passwordTextError.setText(getResources().getString(R.string.passwordEmpty));
            check =false;
        }
        else
        {
            if(utils.notEmpty(confirmPasswordText))
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

    private void setTimer(){

        if (CustomerUtils.getInstance(getActivity()).getLong(Constants.PREF_TIM) != null) {
            long dd = CustomerUtils.getInstance(getActivity()).getLong(Constants.PREF_TIM);
            long timer = new Date(System.currentTimeMillis()).getTime() - CustomerUtils.getInstance(getActivity()).getLong(Constants.PREF_TIM);
            Log.d("TSTS",""+ String.valueOf(timer));
            if (timer < 300000) {
                checkTimer = true;
               timerView.setVisibility(View.VISIBLE);
                new CountDownTimer(300000-timer, 1000) {

                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                        int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                        timerView.setText(String.format("%d:%d",minutes,seconds));
                    }

                    public void onFinish() {
                        timerView.setVisibility(View.GONE);
                        checkTimer = false;
                    }

                }.start();
            } else{
                checkTimer = false;
                timerView.setVisibility(View.GONE);
            }

        }
    }
}

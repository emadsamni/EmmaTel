package com.example.emma_tel.activites;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Utils;
import com.example.emma_tel.viewmodels.LoginViewModel;

public class ContactActivity extends AppCompatActivity {
    CustomerUtils customerUtils;
    Button sendButton ;
    LoginViewModel loginViewModel;
    EditText descText;
    TextView descTextError ;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        customerUtils = CustomerUtils.getInstance(this);
        customerUtils.setLocalConfigration();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        assignUIReference();
        assignAction();
    }

    private void assignUIReference() {
        descTextError =findViewById(R.id.desc_error);
        sendButton =findViewById(R.id.button_send);
        descText = findViewById(R.id.desc);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        customerUtils = CustomerUtils.getInstance(this);
        utils = Utils.getInstance(this);

    }

    private  void assignAction() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValidation())
                {
                    send(descText.getText().toString());
                }
            }
        });
        descText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                descTextError.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isInputValidation() {
        boolean check = true;


        if(!utils.notEmpty(descText))
        {
            descTextError.setText(getResources().getString(R.string.descEmpty));
            check =false;
        }


        return check;
    }

    private void send(String message){

        loginViewModel.send(ContactActivity.this,message );
    }
    }

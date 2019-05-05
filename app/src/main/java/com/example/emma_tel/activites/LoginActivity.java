package com.example.emma_tel.activites;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Utils;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class LoginActivity extends AppCompatActivity {
    Button createButton , loginButton;
    LoginViewModel loginViewModel;
    EditText phoneText;
    EditText passwordText;
    TextView passwordTextError ,phoneTextError;
    CustomerUtils customerUtils;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        customerUtils =CustomerUtils.getInstance(this);
        customerUtils.setLocalConfigration();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignUIReference();
        assignAction();

    }

    private void assignUIReference() {
        passwordTextError =findViewById(R.id.password_error);
        phoneTextError =findViewById(R.id.phone_error);
        createButton = findViewById(R.id.button_create_account);
        loginButton = findViewById(R.id.button_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        passwordText = findViewById(R.id.password);
        phoneText = findViewById(R.id.phone);
        customerUtils = CustomerUtils.getInstance(this);
        utils = Utils.getInstance(this);

    }

    private  void assignAction()
    {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this ,RegisterActivity.class));
                finish();
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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValidation())
                {
                    login(phoneText.getText().toString(),passwordText.getText().toString());
                }
            }
        });

    }
    private boolean isInputValidation() {
        boolean check = true;


        if(!utils.notEmpty(passwordText))
        {
            passwordTextError.setText(getResources().getString(R.string.passwordEmpty));
            check =false;
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
    private void login(String phone ,  String password ){

        loginViewModel.login(LoginActivity.this,phone,password );
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}

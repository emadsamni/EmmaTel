package com.example.emma_tel.activites;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Utils;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
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
        initFacebookLogin();

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

    private LoginButton loginButton2;
    private CallbackManager callbackManager;
    private void initFacebookLogin() {


        loginButton2 = findViewById(R.id.login_button);
        loginButton2.setReadPermissions(Arrays.asList("public_profile", "email"));
        callbackManager = CallbackManager.Factory.create();

        loginButton2.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    String email = object.getString("email");
                                    String name = object.getString("name");
                                    loginViewModel.addFacebookUser(LoginActivity.this ,loginResult.getAccessToken().getUserId() ,name,email);



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException exception) {
            }
        });
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}

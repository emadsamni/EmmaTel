package com.example.emma_tel.activites;

import android.arch.lifecycle.ViewModelProviders;
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
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.Utils;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ProfileActivity extends AppCompatActivity {
    private EditText name;
    CustomerUtils customerUtils;
    Button editButton;
    Utils utils;
    LoginViewModel loginViewModel;
    TextView errortext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        customerUtils =CustomerUtils.getInstance(this);
        customerUtils.setLocalConfigration();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        assignUIReference();
        assignAction();

    }
    public void assignUIReference()
    {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        name = findViewById(R.id.text_name);
        errortext =findViewById(R.id.text_name_error);
        customerUtils = CustomerUtils.getInstance(this);
        utils = Utils.getInstance(this);
        editButton = findViewById(R.id.button_update);
        name.setText(customerUtils.getString(Constants.PREF_Name));


    }

    public void assignAction()
    {
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (utils.notEmpty(name))
               {
                   edit(name.getText().toString());
               }
               else
               {
                   errortext.setText("Please Enter name");
               }
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errortext.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void edit(  String name ){

        loginViewModel.updatekUser(ProfileActivity.this,name );
    }
}

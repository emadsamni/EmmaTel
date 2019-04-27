package com.example.emma_tel.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;

import com.example.emma_tel.R;

public class LoginActivity extends AppCompatActivity {
      Button createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createButton = findViewById(R.id.button_create_account);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(LoginActivity.this ,RegisterActivity.class));
               finish();
            }
        });

    }
}

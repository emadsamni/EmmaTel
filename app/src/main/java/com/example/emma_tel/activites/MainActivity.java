package com.example.emma_tel.activites;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.emma_tel.R;
import com.example.emma_tel.fragments.HomeFragment;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.LocaleHelper;
import com.example.emma_tel.utils.OnNavigationItemSelected;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener , ViewPagerEx.OnPageChangeListener{
    DrawerLayout drawerLayout;
    LinearLayout homeButton;
     public  NavigationView navigationView;
     CustomerUtils customerUtils;
     Button signInTextView;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerUtils =CustomerUtils.getInstance(this);
        customerUtils.setLocalConfigration();

        setContentView(R.layout.activity_main);
        assignUIReference();
        assignAction();


    }
    private void assignUIReference(){
        customerUtils =CustomerUtils.getInstance(this);
        homeButton = findViewById(R.id.linearLayoutHome);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        signInTextView = headerview.findViewById(R.id.sign_in);
        OnNavigationItemSelected.getInstance(MainActivity.this,drawerLayout,navigationView);
        HomeFragment fragment =  new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fargment_container,fragment).commitNowAllowingStateLoss();
    }
    private void assignAction(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home :
                drawerLayout.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

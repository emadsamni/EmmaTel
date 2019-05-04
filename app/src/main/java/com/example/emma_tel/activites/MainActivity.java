package com.example.emma_tel.activites;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.opengl.Visibility;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Base64;
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
import com.example.emma_tel.fragments.NotificationFragment;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.LocaleHelper;
import com.example.emma_tel.utils.OnNavigationItemSelected;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener , ViewPagerEx.OnPageChangeListener , BottomNavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    LinearLayout homeButton;
     public  NavigationView navigationView;
     CustomerUtils customerUtils;
     //Button signInTextView;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout linearLayoutoff;
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
        linearLayoutoff = findViewById(R.id.linearLayoutOff);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
       // signInTextView = headerview.findViewById(R.id.sign_in);
        OnNavigationItemSelected.getInstance(MainActivity.this,drawerLayout,navigationView);
        HomeFragment fragment =  new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fargment_container,fragment).commitNowAllowingStateLoss();
       if (customerUtils.isFound(Constants.PREF_LANG)) {
            if (customerUtils.getString(Constants.PREF_LANG).equals("ar"))
               navigationView.setBackground(getResources().getDrawable(R.drawable.border_raduis_white_ar));
        }
    }
    private void assignAction(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        if (!customerUtils.isFound(Constants.PREF_TOKEN)) {

            navigationView.getMenu().getItem( navigationView.getMenu().size()-1).setVisible(false);
        }
        linearLayoutoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleBackToExitPressedOnce =true;
                onBackPressed();
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
        navigationView.getMenu().getItem(0).setChecked(true);
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private String getKeyHash() {
        PackageInfo info;
        String keyHash = "";
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
            }
        } catch (PackageManager.NameNotFoundException e1) {
        } catch (NoSuchAlgorithmException e) {
        } catch (Exception e) {
        }

        return keyHash;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment= null;
        switch (menuItem.getItemId())
        {
            case R.id.home_id:
                fragment = new HomeFragment();
                navigationView.getMenu().getItem(0).setChecked(true);
                break;
            case R.id.Notification_id:
                fragment = new NotificationFragment();


                break;
            case R.id.profile_id:
                if (!customerUtils.isFound(Constants.PREF_TOKEN)) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }

                break;
        }
        return loadFragment(fragment);
    }

    public   boolean loadFragment(Fragment fragment)
    {
        if (fragment  != null )
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fargment_container,fragment).commitNowAllowingStateLoss();
            return true;
        }
        return false;
    }




}

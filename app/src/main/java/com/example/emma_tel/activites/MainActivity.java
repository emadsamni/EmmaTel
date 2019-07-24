package com.example.emma_tel.activites;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.emma_tel.R;
import com.example.emma_tel.fragments.HomeFragment;
import com.example.emma_tel.fragments.NotificationFragment;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.OnNavigationItemSelected;
import com.google.firebase.messaging.FirebaseMessaging;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        super.onCreate(savedInstanceState);
        customerUtils =CustomerUtils.getInstance(this);

        customerUtils.setLocalConfigration();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
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
        if (GSYVideoManager.backFromWindowFull(this)) {
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
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
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
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }




}

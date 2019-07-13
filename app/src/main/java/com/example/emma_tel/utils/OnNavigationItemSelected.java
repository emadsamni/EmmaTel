package com.example.emma_tel.utils;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.ContactActivity;
import com.example.emma_tel.activites.LoginActivity;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.fragments.AboutUsFragment;
import com.example.emma_tel.fragments.AccessoriesFragment;
import com.example.emma_tel.fragments.EventsFragment;
import com.example.emma_tel.fragments.MapFragment;
import com.example.emma_tel.fragments.MobilesFragment;
import com.example.emma_tel.fragments.HomeFragment;
import com.example.emma_tel.fragments.OffersFragment;
import com.example.emma_tel.fragments.TabletsFragment;
import com.example.emma_tel.fragments.TipsFragment;
import com.example.emma_tel.fragments.WebViewFragment;
import com.example.emma_tel.helprs.CustomerUtils;
import com.facebook.login.LoginManager;


public class OnNavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {

    private Activity context;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    CustomerUtils customerUtils;

     OnNavigationItemSelected(Activity context , DrawerLayout drawerLayout, NavigationView navigationView) {
        this.context = context;
        this.drawerLayout = drawerLayout;
        this.navigationView = navigationView;
        navigationView.setNavigationItemSelectedListener(this);
        customerUtils = CustomerUtils.getInstance(context);
    }

    public static OnNavigationItemSelected getInstance(Activity context, DrawerLayout drawerLayout, NavigationView navigationView){
        return new OnNavigationItemSelected(context,drawerLayout,navigationView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment;
        switch (id){

            case R.id.Home:
                 fragment =  new HomeFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.Mobiles:
                 fragment =  new MobilesFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.Accessories:
                fragment =  new AccessoriesFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.Offers:
                fragment =  new OffersFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.Tablets:
                fragment =  new TabletsFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;



            case R.id.Branches:
                fragment =  new MapFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;

            case R.id.mobile_status:
                fragment =  new WebViewFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.Tips:
                fragment =  new TipsFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.events:
                fragment =  new EventsFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;
            case R.id.translate:
                if (customerUtils.isFound(Constants.PREF_LANG)) {
                    if (customerUtils.getString(Constants.PREF_LANG).equals("ar"))
                    {
                        customerUtils.addString(Constants.PREF_LANG,"en");
                    }
                    else
                    {
                        customerUtils.addString(Constants.PREF_LANG,"ar");
                    }

                    Intent i = context.getBaseContext().getPackageManager().
                            getLaunchIntentForPackage(context.getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    context.finish();
                }
                return true;
            case R.id.About:
                fragment =  new AboutUsFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();

                return true;
            case R.id.Contact:
                if (customerUtils.isFound(Constants.PREF_TOKEN))
                    context. startActivity(new Intent(context, ContactActivity.class));
                else
                    context. startActivity(new Intent(context, LoginActivity.class));

                drawerLayout.closeDrawers();

                return true;
            case R.id.logout:
                     customerUtils.clear();
                      drawerLayout.closeDrawers();
                     LoginManager.getInstance().logOut();
                     ((MainActivity)context). navigationView.getMenu().getItem( navigationView.getMenu().size()-1).setVisible(false);
                return true;
                }
        return false;


        }


}

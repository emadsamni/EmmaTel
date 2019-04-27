package com.example.emma_tel.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.fragments.AccessoriesFragment;
import com.example.emma_tel.fragments.BranchesFragment;
import com.example.emma_tel.fragments.MobilesFragment;
import com.example.emma_tel.fragments.HomeFragment;
import com.example.emma_tel.fragments.NotificationFragment;
import com.example.emma_tel.fragments.OffersFragment;
import com.example.emma_tel.fragments.PagesFragments;
import com.example.emma_tel.helprs.CustomerUtils;


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
            case R.id.Notifications:
                fragment =  new NotificationFragment();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;

            case R.id.Pages:
                fragment =  new PagesFragments();
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fargment_container,fragment ).commitNowAllowingStateLoss();
                drawerLayout.closeDrawers();
                return true;


            case R.id.Branches:
                fragment =  new BranchesFragment();
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

                    context.recreate();
                }
                return true;
                }
        return false;


        }


}

package com.example.emma_tel.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.emma_tel.R;
import com.example.emma_tel.activites.ContactActivity;
import com.example.emma_tel.activites.LoginActivity;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.adapters.MainSliderAdapter;
import com.example.emma_tel.adapters.MobileAdapter;
import com.example.emma_tel.adapters.SliderAdapter;
import com.example.emma_tel.helprs.CustomerUtils;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.viewmodels.DataViewModels;
import com.example.emma_tel.viewmodels.LoginViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    DataViewModels dataViewModels;
    LoginViewModel loginViewModel;
    private ViewPager viewPager;
    private  LinearLayout  linearLayout;
    private SliderAdapter sliderAdapter;
    private RecyclerView myRecyclerView;
    private MainSliderAdapter mAdapter;
    CustomerUtils customerUtils;
    LinearLayout mobilesLinearLayout ,accessoriesLinearLayout ,offersLinearLayout ,branchesLinearLayout ,contactLinearLayout ,aboutLinearLayout;


    public HomeFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_home, container,false);
          assignUIReference(view);
          assignAction();
          initFacebookLogin(view);

          //getMainSlider();
         return  view;
    }
    private void assignUIReference(final View view){
        mobilesLinearLayout = view.findViewById(R.id.linearLayout_mobiles);
        accessoriesLinearLayout = view.findViewById(R.id.linearLayout_accessory);
        aboutLinearLayout = view.findViewById(R.id.linearLayout_about);
        offersLinearLayout= view.findViewById(R.id.linearLayout_offers);
        branchesLinearLayout = view.findViewById(R.id.linearLayout_branches);
        contactLinearLayout = view.findViewById(R.id.linearLayout_contact);
        customerUtils = CustomerUtils.getInstance(getActivity());
        //viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        linearLayout= (LinearLayout)view.findViewById(R.id.dots);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_main_slider);
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        dataViewModels.getMainSliderList(getActivity()).observe(getActivity(), new Observer<List<MainSlider>>() {
            @Override
            public void onChanged(@Nullable List<MainSlider> mainSliders) {
                mAdapter =new MainSliderAdapter(mainSliders,getActivity());
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                myRecyclerView.setLayoutManager(layoutManager);


            }
        });



    }
    private void assignAction(){
        mobilesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MobilesFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(1).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        accessoriesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AccessoriesFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(2).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        offersLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new OffersFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(3).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        branchesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MapFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(4).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });
        contactLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (customerUtils.isFound(Constants.PREF_TOKEN))
                     startActivity(new Intent(getActivity(), ContactActivity.class));
                else
                    startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        aboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AboutUsFragment();
                ((MainActivity)getActivity()).navigationView.getMenu().getItem(6).setChecked(true);
                ((MainActivity)getActivity()).loadFragment(fragment);
            }
        });

    }
    public void autoSwip(int size ,int current)
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setCurrentItem(current+1);
                if (size == current+1)
                {
                    autoSwip(size,-1);
                }
                else
                {
                    autoSwip(size,current+1);
                }

            }
        }, 2000);

    }

    public  void addDotsIndicator(int size , LinearLayout linearLayout , int position)
    {
        TextView[] mDots;
        mDots = new TextView[size];
        for (int i=0; i<size;i++)
        {
            mDots[i] = new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            if (i ==position)
                mDots[i].setTextSize(45);
            else
                mDots[i].setTextSize(35);
            mDots[i].setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            mDots[i].setGravity(Gravity.CENTER);
            linearLayout.addView(mDots[i]);
            linearLayout.setGravity(Gravity.CENTER);

        }
    }

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private void initFacebookLogin(View container) {
        LoginManager.getInstance().logOut();

        loginButton = container.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.setFragment(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
                                    loginViewModel.addFacebookUser(getActivity() ,loginResult.getAccessToken().getUserId() ,name,email);



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
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

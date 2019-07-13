package com.example.emma_tel.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import com.example.emma_tel.R;
import com.example.emma_tel.api.ApiClient;
import com.example.emma_tel.api.ApiInterface;
import com.example.emma_tel.api.ApiResponse;
import com.example.emma_tel.api.CallbackWithRetry;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Category;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.EMedia;
import com.example.emma_tel.models.Event;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Notification;
import com.example.emma_tel.models.Offer;
import com.example.emma_tel.models.Page;
import com.example.emma_tel.models.Tablet;
import com.example.emma_tel.models.Tips;
import com.example.emma_tel.utils.Constants;
import com.example.emma_tel.utils.ProgressDialog;

import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class DataRepository {
    private MutableLiveData<List<MainSlider>>  mainSliderList;
    private MutableLiveData<List<Mobile>>  mobilesList;
    private MutableLiveData<List<Tablet>>  tabletsList;
    private MutableLiveData<List<Offer>>  offerList;
    private MutableLiveData<List<Event>>  eventList;

    private MutableLiveData<List<EMedia>>  mediaList;
    private MutableLiveData<List<Notification>>  notificationList;
    private MutableLiveData<List<Branch>>  branchList;
    private MutableLiveData<List<Page>> pageList;
    private MutableLiveData<Page> aboutUS;
    private MutableLiveData<Tips> tips;
    private MutableLiveData<List<Category>> categoryList;
    private MutableLiveData<List<Company>> companyList;
    private MutableLiveData<List<Accessory>> accessoryList;

    private Application application;

    private DataRepository(Application application){
        this.application = application;
    }

    public static DataRepository getInstance(Application application){
        return new DataRepository(application);
    }

    public LiveData<List<MainSlider>> getMainSliderList( Context context){
        mainSliderList = new MutableLiveData<>();
        getMainSlider(context);
        return mainSliderList;
    }

    public LiveData<List<Mobile>> getMobileList( Context context){
        mobilesList = new MutableLiveData<>();
        getMobiles(context);
        return mobilesList;
    }
    public LiveData<List<Tablet>> getTabletList( Context context){
        tabletsList = new MutableLiveData<>();
        getTablets(context);
        return tabletsList;
    }

    public LiveData<List<Offer>> getOfferList( Context context){
        offerList = new MutableLiveData<>();
        getOffers(context);
        return offerList;
    }

    public LiveData<List<EMedia>> getMediaList( Context context){
        mediaList = new MutableLiveData<>();
        getMedia(context);
        return mediaList;
    }

    public LiveData<List<Event>> getEventList(Context context) {
        eventList = new MutableLiveData<>();
        getEvents(context);
        return eventList;
    }

    public LiveData<List<Notification>> getNotificationList( Context context){
        notificationList = new MutableLiveData<>();
        getNotifications(context);
        return notificationList;
    }

    public LiveData<List<Branch>> getBranchList( Context context){
        branchList = new MutableLiveData<>();
        getBranches(context);
        return branchList;
    }

    public LiveData<List<Page>> getPageList( Context context){
        pageList = new MutableLiveData<>();
        getPages(context);
        return pageList;
    }


    public LiveData<Page> getAboutUs( Context context){
        aboutUS = new MutableLiveData<>();
        getPage(context);
        return aboutUS;
    }

    public LiveData<Tips> getTips( Context context , String name){
        tips = new MutableLiveData<>();
        getTipsFunction(context , name);
        return tips;
    }


    public LiveData<List<Category>> getCategoryList( Context context){
        categoryList = new MutableLiveData<>();
        getCategories(context);
        return categoryList;
    }

    public LiveData<List<Company>> getCompanyList( Context context){
        companyList = new MutableLiveData<>();
        getCompanies(context);
        return companyList;
    }

    public LiveData<List<Accessory>> getAccessoryList( Context context){
        accessoryList = new MutableLiveData<>();
        getAccessories(context);
        return accessoryList;
    }


    private void getMainSlider(Context context) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<MainSlider>>> call = apiService.getMainSlider(Constants.API_KEY);

        call.enqueue(new CallbackWithRetry<ApiResponse<List<MainSlider>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<MainSlider>>> call, Response<ApiResponse<List<MainSlider>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    mainSliderList.postValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<ApiResponse<List<MainSlider>>> call, Throwable t) {
                onFailure(t);
            }
        });

    }



    private void getMedia(Context context) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<EMedia>>> call = apiService.getMedia(Constants.API_KEY);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<EMedia>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<EMedia>>> call, Response<ApiResponse<List<EMedia>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    mediaList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<EMedia>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }
    private void getMobiles(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Mobile>>> call = apiService.getMobiles(Constants.API_KEY);

        call.enqueue(new CallbackWithRetry<ApiResponse<List<Mobile>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Mobile>>> call, Response<ApiResponse<List<Mobile>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    mobilesList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Mobile>>> call, Throwable t) {
                onFailure(t);
            }
        });
    }

    private void getTablets(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Tablet>>> call = apiService.getTablets(Constants.API_KEY);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<Tablet>>>(call, context, 3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Tablet>>> call, Response<ApiResponse<List<Tablet>>> response) {
                if (!response.isSuccessful()) {
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error, Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    tabletsList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Tablet>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }

    private void getOffers(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Offer>>> call = apiService.getOffers(Constants.API_KEY);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<Offer>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Offer>>> call, Response<ApiResponse<List<Offer>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    offerList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Offer>>> call, Throwable t) {
                onFailure(t);
            }
        });

    }


    private void getEvents(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Event>>> call = apiService.getEvents(Constants.API_KEY);

        call.enqueue(new CallbackWithRetry<ApiResponse<List<Event>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Event>>> call, Response<ApiResponse<List<Event>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    eventList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Event>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }
    private void getNotifications(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Notification>>> call = apiService.getNotifications(Constants.API_KEY);

        call.enqueue(new CallbackWithRetry<ApiResponse<List<Notification>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Notification>>> call, Response<ApiResponse<List<Notification>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null) {
                    Collections.reverse(response.body().getData());
                    notificationList.postValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Notification>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }

    private void getBranches(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Branch>>> call = apiService.getBranches(Constants.API_KEY);

        call.enqueue(new CallbackWithRetry<ApiResponse<List<Branch>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Branch>>> call, Response<ApiResponse<List<Branch>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    branchList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Branch>>> call, Throwable t) {
                onFailure(t);
            }
        });

    }

    private void getPages(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Page>>> call = apiService.getPages(Constants.API_KEY);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<Page>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Page>>> call, Response<ApiResponse<List<Page>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    pageList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Page>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }

    private void getPage(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<Page>> call = apiService.getPage(Constants.API_KEY ,"about-as");
        call.enqueue(new CallbackWithRetry<ApiResponse<Page>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<Page>> call, Response<ApiResponse<Page>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    aboutUS.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<Page>> call, Throwable t) {

            }
        });

    }

    private void getTipsFunction(Context context , String name) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Tips>>> call = apiService.getTips(Constants.API_KEY ,name);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<Tips>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Tips>>> call, Response<ApiResponse<List<Tips>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    if (response.body().getData().size()>0)
                      tips.postValue(response.body().getData().get(0));
                    else
                        tips.postValue(new Tips(context.getResources().getString(R.string.no_tips)));
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Tips>>> call, Throwable t) {

            }
        });


    }

    private void getCategories(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Category>>> call = apiService.getCategories(Constants.API_KEY);

        call.enqueue(new CallbackWithRetry<ApiResponse<List<Category>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Category>>> call, Response<ApiResponse<List<Category>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    categoryList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Category>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }

    private void getCompanies(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Company>>> call = apiService.getCompanies(Constants.API_KEY);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<Company>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Company>>> call, Response<ApiResponse<List<Company>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    companyList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Company>>> call, Throwable t) {
                onFailure(t);
            }
        });


    }

    private void getAccessories(Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<Accessory>>> call = apiService.getAccessories(Constants.API_KEY);
        call.enqueue(new CallbackWithRetry<ApiResponse<List<Accessory>>>(call,context,3) {
            @Override
            public void onResponse(Call<ApiResponse<List<Accessory>>> call, Response<ApiResponse<List<Accessory>>> response) {
                if (!response.isSuccessful()){
                    ProgressDialog.getInstance().cancel();
                    Toast.makeText(application, R.string.unexpected_api_error,Toast.LENGTH_SHORT).show();
                }
                ProgressDialog.getInstance().cancel();
                if (response.body().getData() != null)
                    accessoryList.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Accessory>>> call, Throwable t) {
                onFailure(t);
            }
        });



    }


}

package com.example.emma_tel.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Category;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Notification;
import com.example.emma_tel.models.Offer;
import com.example.emma_tel.models.Page;
import com.example.emma_tel.repositories.DataRepository;
import com.example.emma_tel.utils.ProgressDialog;
import java.util.List;

public class DataViewModels extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<MainSlider>> mainSliderList;
    private LiveData<List<Mobile>> mobileList;
    private LiveData<List<Offer>> offerList;
    private LiveData<List<Notification>> notificationList;
    private LiveData<List<Branch>> branchList;
    private LiveData<List<Page>> pageList;
    private LiveData<List<Company>> companyList;
    private LiveData<List<Category>> categoryList;
    private LiveData<List<Accessory>>  accessoryList;
    private String TAG = this.getClass().getSimpleName();

        public DataViewModels(@NonNull Application application) {
            super(application);
            repository = DataRepository.getInstance(application);
        }

    public LiveData<List<MainSlider>> getMainSliderList(Context context){
        if (mainSliderList==null) {
            ProgressDialog.getInstance().show(context);
            mainSliderList = repository.getMainSliderList(context);
        }

        return mainSliderList;
    }

    public LiveData<List<Mobile>> getMobileList(Context context){
        if (mobileList==null) {
            ProgressDialog.getInstance().show(context);
            mobileList = repository.getMobileList(context);
        }
        return mobileList;
    }

    public LiveData<List<Offer>> getOfferList(Context context){
        if (offerList==null) {
            ProgressDialog.getInstance().show(context);
            offerList = repository.getOfferList(context);
        }
        return offerList;
    }

    public LiveData<List<Notification>> getNotificationList(Context context){
        if (notificationList==null) {
            ProgressDialog.getInstance().show(context);
            notificationList = repository.getNotificationList(context);
        }
        return notificationList;
    }

    public LiveData<List<Branch>> getBranchList(Context context){
        if (branchList==null) {
            ProgressDialog.getInstance().show(context);
            branchList = repository.getBranchList(context);
        }
        return branchList;
    }

    public LiveData<List<Page>> getPageList(Context context){
        if (pageList==null) {
            ProgressDialog.getInstance().show(context);
            pageList = repository.getPageList(context);
        }
        return pageList;
    }

    public LiveData<List<Category>> getCategoryList(Context context){
        if (categoryList==null) {
            ProgressDialog.getInstance().show(context);
            categoryList = repository.getCategoryList(context);
        }
        return categoryList;
    }

    public LiveData<List<Company>> getCompanyList(Context context){
        if (companyList==null) {
            ProgressDialog.getInstance().show(context);
            companyList = repository.getCompanyList(context);
        }
        return companyList;
    }

    public LiveData<List<Accessory>> getAccessoryList(Context context){
        if (accessoryList==null) {
            ProgressDialog.getInstance().show(context);
            accessoryList = repository.getAccessoryList(context);
        }
        return accessoryList;
    }


}

package com.example.emma_tel.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.content.Context;
import androidx.annotation.NonNull;

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
import com.example.emma_tel.repositories.DataRepository;
import com.example.emma_tel.utils.ProgressDialog;
import java.util.List;

public class DataViewModels extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<MainSlider>> mainSliderList;
    private LiveData<List<Mobile>> mobileList;
    private LiveData<List<Tablet>> tabletList;
    private LiveData<List<Offer>> offerList;
    private LiveData<List<EMedia>> mediaList;
    private LiveData<List<Event>> eventList;
    private LiveData<List<Notification>> notificationList;
    private LiveData<List<Branch>> branchList;
    private LiveData<List<Page>> pageList;
    private LiveData<Page> aboutUs;
    private LiveData<Tips> tips;
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

    public LiveData<List<EMedia>> getMediaList(Context context){
        if (mediaList==null) {
            ProgressDialog.getInstance().show(context);
            mediaList = repository.getMediaList(context);
        }

        return mediaList;
    }

    public LiveData<List<Mobile>> getMobileList(Context context){
        if (mobileList==null) {
            ProgressDialog.getInstance().show(context);
            mobileList = repository.getMobileList(context);
        }
        return mobileList;
    }

    public LiveData<List<Tablet>> getTabletList(Context context){
        if (tabletList==null) {
            ProgressDialog.getInstance().show(context);
            tabletList = repository.getTabletList(context);
        }
        return tabletList;
    }

    public LiveData<List<Offer>> getOfferList(Context context){
        if (offerList==null) {
            ProgressDialog.getInstance().show(context);
            offerList = repository.getOfferList(context);
        }
        return offerList;
    }

    public LiveData<List<Event>> getEventList(Context context){
        if (eventList==null) {
            ProgressDialog.getInstance().show(context);
            eventList = repository.getEventList(context);
        }
        return eventList;
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

    public LiveData<Page> getAboutUs(Context context){
        if (aboutUs==null) {
            ProgressDialog.getInstance().show(context);
            aboutUs = repository.getAboutUs(context);
        }
        return aboutUs;
    }

    public LiveData<Tips> getTips(Context context , String  name){
            ProgressDialog.getInstance().show(context);
            tips = repository.getTips(context ,name);
        return tips;
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

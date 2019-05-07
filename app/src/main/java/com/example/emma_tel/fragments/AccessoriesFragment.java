package com.example.emma_tel.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.emma_tel.R;
import com.example.emma_tel.activites.MainActivity;
import com.example.emma_tel.adapters.AccessoryAdapter;
import com.example.emma_tel.adapters.MobileAdapter;
import com.example.emma_tel.interfaces.OnItemRecyclerClicked;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Category;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.MainSlider;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesFragment extends Fragment implements OnItemRecyclerClicked {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private AccessoryAdapter mAdapter;
    Button filterButton;
    private  List<Accessory> accessoryList;
    private  List<Category> categoryList;
    int type=0;
    TextView typeText;

    public AccessoriesFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_accessories, container,false);
        assignUIReference(view);
        return  view;
    }
    private void assignUIReference(View view){
        type =0;
        typeText = (TextView)view.findViewById(R.id.text_type);
        filterButton = (Button) view.findViewById(R.id.filter);
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_accessory);
        dataViewModels.getAccessoryList(getActivity()).observe(getActivity(), new Observer<List<Accessory>>() {
            @Override
            public void onChanged(@Nullable List<Accessory> accessories) {
                accessoryList =accessories;
                categoryList =getCategoryList(accessories);
                setFilterButton(accessoryList);
                mAdapter =new AccessoryAdapter(accessories,getActivity() ,AccessoriesFragment.this);
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager =new GridLayoutManager(getActivity(),2);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });
    }

    public  void  setFilterButton(List<Accessory> accessoryList)
    {
        String[] categories = new String[categoryList.size()+1];
        categories[0] =getResources().getString(R.string.all);
        for (int i=0;i<categoryList.size();i++)
        {
            categories[i+1] =categoryList.get(i).getName();
        }
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =0;
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        type = which;
                    }
                });
                adb.setNegativeButton(getActivity().getResources().getString(R.string.cancel), null);

                adb.setPositiveButton(getActivity().getResources().getString(R.string.select), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (type != 0) {
                            typeText.setText(categoryList.get(type -1).getName());
                            List<Accessory> filterList = new ArrayList<>();
                            for (int i = 0; i < accessoryList.size(); i++) {
                                if (accessoryList.get(i).getCategory().getId() == categoryList.get(type -1).getId()) {
                                    filterList.add(accessoryList.get(i));

                                }
                            }
                            mAdapter = new AccessoryAdapter(filterList, getActivity(), AccessoriesFragment.this);
                            myRecyclerView.setAdapter(mAdapter);
                        }
                        else
                        {
                            typeText.setText(getActivity().getResources().getString(R.string.all));
                            mAdapter = new AccessoryAdapter(accessoryList, getActivity(), AccessoriesFragment.this);
                            myRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
                adb.setTitle(getActivity().getResources().getString(R.string.select_cat));
                adb.show();
            }
        });
    }

    public List<Category> getCategoryList(List<Accessory> accessories)
    {
        List<Category> categoryListTemp=  new ArrayList<>();
        for (int i=0 ;i<accessories.size();i++)
        {
            if (!isFound(categoryListTemp,accessories.get(i)))
                categoryListTemp.add(accessories.get(i).getCategory());
        }

        return categoryListTemp;
    }

    private boolean isFound(List<Category> categoryListTemp, Accessory accessory) {

        for (int i=0;i<categoryListTemp.size();i++)
        {
            if (categoryListTemp.get(i).getId()==accessory.getCategory().getId())
                return true;
        }
        return false;
    }
    @Override
    public void onClickedRecyclerItem(Mobile mobile) {

    }

    @Override
    public void onClickedRecyclerItem(Accessory accessory) {
        AccessoryViewDialog accessoryViewDialog = new AccessoryViewDialog();
        accessoryViewDialog.setAccessory(accessory);
        accessoryViewDialog.show(getActivity().getSupportFragmentManager(), "View JobDialog");

    }
}

package com.example.emma_tel.fragments;

import android.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.TabletAdapter;
import com.example.emma_tel.interfaces.OnItemRecyclerClicked;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Tablet;
import com.example.emma_tel.viewmodels.DataViewModels;

import java.util.ArrayList;
import java.util.List;

public class TabletsFragment extends Fragment implements OnItemRecyclerClicked {

    DataViewModels dataViewModels;
    private RecyclerView myRecyclerView;
    private TabletAdapter mAdapter;

    private List<Tablet> tabletList;
    private List<Company> companyList;
    int type=0;
    TextView typeText;
    Button filterButton;
    public TabletsFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_tablets, container,false);
        assignUIReference(view);
        return  view;
    }

    private void assignUIReference(View view){
        type =0;
        typeText = (TextView)view.findViewById(R.id.text_type);
        dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
        filterButton = (Button) view.findViewById(R.id.filter);
        myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_tablet);

        dataViewModels.getTabletList(getActivity()).observe(getActivity(), new Observer<List<Tablet>>() {
            @Override
            public void onChanged(@Nullable List<Tablet> tablets) {
                tabletList =tablets;
                companyList =getCompanyList(tablets);
                setFilterButton(companyList);
                mAdapter =new TabletAdapter(tablets,getActivity(),TabletsFragment.this);
                myRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager =new LinearLayoutManager( getActivity());
                layoutManager =new GridLayoutManager(getActivity(),2);
                myRecyclerView.setLayoutManager(layoutManager);
            }
        });



    }

    public List<Company> getCompanyList(List<Tablet> tablets)
    {
        List<Company> companyListTemp=  new ArrayList<>();
        for (int i=0 ;i<tablets.size();i++)
        {
            if (!isFound(companyListTemp,tablets.get(i)))
                companyListTemp.add(tablets.get(i).getCompany());
        }

        return companyListTemp;
    }

    private boolean isFound(List<Company> companyListTemp, Tablet tablet) {

        for (int i=0;i<companyListTemp.size();i++)
        {
            if (companyListTemp.get(i).getId()==tablet.getCompany().getId())
                return true;
        }
        return false;
    }


    public  void  setFilterButton(List<Company> companyList)
    {
        String[] companies = new String[companyList.size()+1];
        companies[0] =getResources().getString(R.string.all);
        for (int i=0;i<companyList.size();i++)
        {
            companies[i+1] =companyList.get(i).getName();
        }
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =0;
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setSingleChoiceItems(companies, 0, new DialogInterface.OnClickListener() {


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
                            typeText.setText(companyList.get(type -1).getName());
                            List<Tablet> filterList = new ArrayList<>();
                            for (int i = 0; i < tabletList.size(); i++) {
                                if (tabletList.get(i).getCompany().getId() == companyList.get(type -1).getId()) {
                                    filterList.add(tabletList.get(i));

                                }
                            }
                            mAdapter = new TabletAdapter(filterList, getActivity(), TabletsFragment.this);
                            myRecyclerView.setAdapter(mAdapter);
                        }
                        else
                        {
                            typeText.setText(getActivity().getResources().getString(R.string.all));
                            mAdapter = new TabletAdapter(tabletList, getActivity(), TabletsFragment.this);
                            myRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
                adb.setTitle(getActivity().getResources().getString(R.string.select_company));
                adb.show();
            }
        });
    }
    @Override
    public void onClickedRecyclerItem(Mobile mobile) {

    }

    @Override
    public void onClickedRecyclerItem(Tablet tablet) {
        TabletViewDialog tabletViewDialog = new TabletViewDialog() ;
        tabletViewDialog.setTablet(tablet);
        tabletViewDialog.setTabletList(tabletList);
        tabletViewDialog.show(getActivity().getSupportFragmentManager(), "View JobDialog");
    }

    @Override
    public void onClickedRecyclerItem(Accessory accessory) {

    }
}

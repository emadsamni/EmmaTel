package com.example.emma_tel.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.BranchAdapter;
import com.example.emma_tel.adapters.MobileAdapter;
import com.example.emma_tel.adapters.PopupAdapter;
import com.example.emma_tel.models.Branch;
import com.example.emma_tel.models.Company;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.viewmodels.DataViewModels;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapFragment  extends Fragment {
    DataViewModels dataViewModels;
    int type=0;
    TextView typeText;
    Button filterButton;
    private RecyclerView myRecyclerView;
    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        filterButton = (Button) rootView.findViewById(R.id.filter);
        typeText = (TextView)rootView.findViewById(R.id.text_type);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(35.197078,38.515626))
                        .zoom(6)
                        .bearing(0)
                        .tilt(45)
                        .build();


                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null);

                dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
                // myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_branch);
                dataViewModels.getBranchList(getActivity()).observe(getActivity(), new Observer<List<Branch>>() {
                    @Override
                    public void onChanged(@Nullable List<Branch> branches) {
                        setFilterButton(branches,mMap);
                        for (Branch b:branches) {
                            String address =getActivity().getResources().getString(R.string.address)
                                    +b.getAddress()+"\n"+getActivity().getResources().getString(R.string.phonemap)
                                    +b.getPhone()+"\n"+getActivity().getResources().getString(R.string.fixed_phone)
                                    +b.getFixed_phone()+"\n"+getActivity().getResources().getString(R.string.whatsapp)
                                    +b.getWhatsapp();
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble( b.getLat()), Double.parseDouble( b.getLng())))
                                    .title(b.getTitle())
                                    .snippet(address));
                        }

                    }
                });

                mMap.setInfoWindowAdapter(new PopupAdapter(getActivity()));





            }
        });


        return rootView;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public  void  setFilterButton(List<Branch> branchList ,GoogleMap mMap)
    {

        String[] branches = new String[branchList.size()+1];
        branches[0] =getResources().getString(R.string.all);
        for (int i=0;i<branchList.size();i++)
        {
            branches[i+1] =branchList.get(i).getTitle();
        }
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =0;
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setSingleChoiceItems(branches, 0, new DialogInterface.OnClickListener() {


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
                            CameraPosition googlePlex = CameraPosition.builder()
                                    .target(new LatLng(Double.parseDouble(branchList.get(type-1).getLat()),Double.parseDouble(branchList.get(type-1).getLng())))
                                    .zoom(8)
                                    .bearing(0)
                                    .tilt(45)
                                    .build();
                            typeText.setText(branchList.get(type -1).getTitle());


                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null);
                        }
                        else
                        {
                            CameraPosition googlePlex = CameraPosition.builder()
                                    .target(new LatLng(35.197078,38.515626))
                                    .zoom(6)
                                    .bearing(0)
                                    .tilt(45)
                                    .build();

                            typeText.setText(getActivity().getResources().getString(R.string.all));


                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null);
                        }
                    }
                });
                adb.setTitle(getActivity().getResources().getString(R.string.select_company));
                adb.show();
            }
        });
    }
}

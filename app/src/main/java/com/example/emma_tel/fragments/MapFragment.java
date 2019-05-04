package com.example.emma_tel.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import com.example.emma_tel.R;
import com.example.emma_tel.adapters.BranchAdapter;
import com.example.emma_tel.adapters.PopupAdapter;
import com.example.emma_tel.models.Branch;
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

import java.util.List;

public class MapFragment  extends Fragment {
    DataViewModels dataViewModels;
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

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(33.519706,36.302430))
                        .zoom(8)
                        .bearing(0)
                        .tilt(45)
                        .build();


                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null);

                dataViewModels = ViewModelProviders.of(getActivity()).get(DataViewModels.class);
                // myRecyclerView =(RecyclerView) view.findViewById(R.id.recycler_branch);
                dataViewModels.getBranchList(getActivity()).observe(getActivity(), new Observer<List<Branch>>() {
                    @Override
                    public void onChanged(@Nullable List<Branch> branches) {
                        for (Branch b:branches) {
                            String address =getActivity().getResources().getString(R.string.address)
                                    +b.getAddress()+"\n"+getActivity().getResources().getString(R.string.phonemap)
                                    +b.getPhone()+"\n"+getActivity().getResources().getString(R.string.fixed_phone)
                                    +b.getFixed_phone()+"\n"+getActivity().getResources().getString(R.string.whatsapp)
                                    +b.getWhatsapp();
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(33.519706, 36.302430))
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
}

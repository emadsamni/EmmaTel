package com.example.emma_tel.fragments;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.activites.ComparationActivity;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MobileViewDialog extends AppCompatDialogFragment {
    private Mobile mobile;
    private List<Mobile> mobileList;
    TextView mobileName ,mobileTitle, cpuText,ramText,screenText ,networkText,cameraText,frontCameraText,batteryText,storageText,detailsText,mobilePrice;
    ImageView mobileImage;
    LinearLayout linearLayout;
    Button compareButton;
    int type;


    public  void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }


    public  void setMobileList(List<Mobile> mobileList) {
        this.mobileList = mobileList;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view  =inflater.inflate(R.layout.layout_mobile_dialog,null);
        builder.setView(view);
        assignUIReference(view);
        return builder.create();

    }

    private  void assignUIReference(View view)
    {
        mobileName = view.findViewById(R.id.text_mobile);
        mobilePrice = view.findViewById(R.id.text_view_Mobile_price);
        compareButton = view.findViewById(R.id.compare);
        linearLayout =view.findViewById(R.id.linearLayout);
        mobileTitle = view.findViewById(R.id.text_Mobile_title);
        cpuText = view.findViewById(R.id.text_cpu);
        ramText = view.findViewById(R.id.text_ram);
        screenText = view.findViewById(R.id.text_screen);
        cameraText = view.findViewById(R.id.text_camera);

        frontCameraText = view.findViewById(R.id.text_front_camera);
        batteryText = view.findViewById(R.id.text_battery);
        storageText = view.findViewById(R.id.text_storage);
        detailsText = view.findViewById(R.id.text_details);
        mobileImage = view.findViewById(R.id.mobile_photo);
        networkText = view.findViewById(R.id.text_network);
        mobilePrice.setText(mobile.getPrice() +" "+getActivity().getResources().getString(R.string.sp));
        linearLayout = view.findViewById(R.id.color);
        Picasso.with(getActivity()).load(Constants.IMG_URL+mobile.getImage()).into(mobileImage);
        mobileName.setText(mobile.getCompany().getName());
        mobileTitle.setText(mobile.getName());
        cpuText.setText(mobile.getCpu());
        ramText.setText(mobile.getRam());
        screenText.setText(mobile.getScreen());
        networkText.setText(mobile.getNetworks());
        cameraText.setText(mobile.getMain_camera());
        frontCameraText.setText(mobile.getFront_camera());
        batteryText.setText(mobile.getBattery());
        storageText.setText(mobile.getInternal_storage());
        detailsText.setText(mobile.getOther_details());


        String[] colors = mobile.getColors().split(";");
        for (int i =0 ;i<colors.length;i++)
        {
            LinearLayout color = new LinearLayout(getActivity());
            color.setBackgroundColor(Color.parseColor(colors[i]));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80 ,30);
            params.setMargins(5,5,5,5);
            color.setLayoutParams(params);
            linearLayout.addView(color);

        }
        setCompareButton(mobileList);










    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        Window window = d.getWindow();
        window.setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
    }


    public  void  setCompareButton(List<Mobile> mobileList)
    {
        String[] mobiles = new String[mobileList.size()];
        mobiles[0] =getResources().getString(R.string.all);
        for (int i=0;i<mobileList.size();i++)
        {
            if (!mobileList.get(i).getId().equals(mobile.getId()))
            mobiles[i] =mobileList.get(i).getName();
        }
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =0;
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setSingleChoiceItems(mobiles, 0, new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       type = which;
                    }

                });
                adb.setNegativeButton(getActivity().getResources().getString(R.string.cancel), null);

                adb.setPositiveButton(getActivity().getResources().getString(R.string.select), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =  new Intent(getActivity() , ComparationActivity.class);
                        intent.putExtra("first" ,mobile);
                        intent.putExtra("second" ,mobileList.get(type));
                        getActivity().startActivity(intent);
                    }
                });
                adb.setTitle(getActivity().getResources().getString(R.string.select_company));
                adb.show();
            }
        });
    }
}

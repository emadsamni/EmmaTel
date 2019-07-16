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
import com.example.emma_tel.activites.TabletComparationActivity;
import com.example.emma_tel.models.Tablet;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TabletViewDialog  extends AppCompatDialogFragment {
    private Tablet tablet;
    private List<Tablet> tabletList;
    TextView mobileName ,mobileTitle, cpuText,ramText,screenText ,networkText,cameraText,frontCameraText,batteryText,storageText,detailsText ,tabletPrice;
    ImageView mobileImage;
    LinearLayout linearLayout;
    Button compareButton;
    int type;

    public  void setTablet(Tablet tablet) {
        this.tablet = tablet;}

    public  void setTabletList(List<Tablet> tabletList) {
        this.tabletList = tabletList;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view  =inflater.inflate(R.layout.layout_tablet_dialog,null);
        builder.setView(view);
        assignUIReference(view);
        return builder.create();

    }

    private  void assignUIReference(View view)
    {
        mobileName = view.findViewById(R.id.text_mobile);
        tabletPrice = view.findViewById(R.id.text_view_tablet_price);
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
        linearLayout = view.findViewById(R.id.color);
        Picasso.with(getActivity()).load(Constants.IMG_URL+tablet.getImage()).into(mobileImage);
        mobileName.setText(tablet.getCompany().getName());
        mobileTitle.setText(tablet.getName());
        cpuText.setText(tablet.getCpu());
        ramText.setText(tablet.getRam());
        tabletPrice.setText(tablet.getPrice()+" "+getActivity().getResources().getString(R.string.sp));
        screenText.setText(tablet.getScreen());
        networkText.setText(tablet.getNetworks());
        cameraText.setText(tablet.getMain_camera());
        frontCameraText.setText(tablet.getFront_camera());
        batteryText.setText(tablet.getBattery());
        storageText.setText(tablet.getInternal_storage());
        detailsText.setText(tablet.getOther_details());


        String[] colors = tablet.getColors().split(";");
        for (int i =0 ;i<colors.length;i++)
        {
            LinearLayout color = new LinearLayout(getActivity());
            color.setBackgroundColor(Color.parseColor(colors[i]));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80 ,30);
            params.setMargins(5,5,5,5);
            color.setLayoutParams(params);
            linearLayout.addView(color);

        }
        setCompareButton(tabletList);


    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        Window window = d.getWindow();
        window.setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
    }

    public  void  setCompareButton(List<Tablet> tabletList)
    {
        String[] tables = new String[tabletList.size()];
        tables[0] =getResources().getString(R.string.all);
        for (int i=0;i<tabletList.size();i++)
        {
            if (!tabletList.get(i).getId().equals(tablet))
            tables[i] =tabletList.get(i).getName();
        }
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =0;
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setSingleChoiceItems(tables, 0, new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        type = which;
                    }

                });
                adb.setNegativeButton(getActivity().getResources().getString(R.string.cancel), null);

                adb.setPositiveButton(getActivity().getResources().getString(R.string.select), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =  new Intent(getActivity() , TabletComparationActivity.class);
                        intent.putExtra("first" ,tablet);
                        intent.putExtra("second" ,tabletList.get(type));
                        getActivity().startActivity(intent);
                    }
                });
                adb.setTitle(getActivity().getResources().getString(R.string.select_company));
                adb.show();
            }
        });
    }

}

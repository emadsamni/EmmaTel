package com.example.emma_tel.fragments;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

public class AccessoryViewDialog extends AppCompatDialogFragment {
    private Accessory accessory;
    TextView accessoryName ,accessoryTitle, detailsText , companytext;
    ImageView accessoryImage;
    LinearLayout linearLayout;


    public  void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view  =inflater.inflate(R.layout.layout_accessory_dilaog,null);
        builder.setView(view);
        assignUIReference(view);
        return builder.create();

    }

    private  void assignUIReference(View view)
    {
        accessoryName =view.findViewById(R.id.text_accessory);
        accessoryTitle =view.findViewById(R.id.text_accessory_title);
        accessoryImage =view.findViewById(R.id.accessory_photo);
        //linearLayout =view.findViewById(R.id.linearLayout);
        detailsText = view.findViewById(R.id.text_details);
        linearLayout = view.findViewById(R.id.color);
        Picasso.with(getActivity()).load(Constants.IMG_URL+accessory.getImage()).into(accessoryImage);
        accessoryName.setText(accessory.getCategory().getName());
        accessoryTitle.setText(accessory.getName());
       detailsText.setText(accessory.getDetails());

        String[] colors = accessory.getColors().split(";");
        for (int i =0 ;i<colors.length;i++)
        {
            LinearLayout color = new LinearLayout(getActivity());
            color.setBackgroundColor(Color.parseColor(colors[i]));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80 ,30);
            params.setMargins(5,5,5,5);
            color.setLayoutParams(params);
            linearLayout.addView(color);

        }








    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        Window window = d.getWindow();
        window.setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
    }
}


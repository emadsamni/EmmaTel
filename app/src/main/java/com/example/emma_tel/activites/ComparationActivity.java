package com.example.emma_tel.activites;

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emma_tel.R;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.utils.Constants;
import com.squareup.picasso.Picasso;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ComparationActivity extends AppCompatActivity {
    TextView mobileName ,mobileTitle, cpuText,ramText,screenText ,networkText,cameraText,frontCameraText,batteryText,storageText,detailsText , mobilePrice;
    ImageView mobileImage;
    LinearLayout linearLayout;
    Mobile mobile ,mobile2;

    TextView mobileName2 ,mobileTitle2, cpuText2,ramText2,screenText2 ,networkText2,cameraText2,frontCameraText2,batteryText2,storageText2,detailsText2,mobilePrice2;
    ImageView mobileImage2;
    LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparation);
        mobile =(Mobile) getIntent().getSerializableExtra("first");
        mobile2 =(Mobile) getIntent().getSerializableExtra("second");
        assignUIReferenceMobile1();
        assignUIReferenceMobile2();

    }

    private  void assignUIReferenceMobile1()
    {

        linearLayout =findViewById(R.id.linearLayout);
        mobileTitle = findViewById(R.id.text_Mobile_title);
        cpuText = findViewById(R.id.text_cpu);
        ramText = findViewById(R.id.text_ram);
        screenText = findViewById(R.id.text_screen);
        cameraText = findViewById(R.id.text_camera);
        frontCameraText = findViewById(R.id.text_front_camera);
        batteryText = findViewById(R.id.text_battery);
        storageText = findViewById(R.id.text_storage);
        detailsText = findViewById(R.id.text_details);
        mobileImage = findViewById(R.id.mobile_photo);
        networkText = findViewById(R.id.text_network);
        linearLayout = findViewById(R.id.color);
        Picasso.with(this).load(Constants.IMG_URL+mobile.getImage()).into(mobileImage);
        mobilePrice = findViewById(R.id.text_view_mobile_price);
        mobilePrice.setText(mobile.getPrice() +" "+getResources().getString(R.string.sp));
        mobilePrice2 = findViewById(R.id.text_view_mobile_price2);
        mobilePrice2.setText(mobile2.getPrice() +" "+getResources().getString(R.string.sp));
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
            LinearLayout color = new LinearLayout(this);
            color.setBackgroundColor(Color.parseColor(colors[i]));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80 ,30);
            params.setMargins(5,5,5,5);
            color.setLayoutParams(params);
            linearLayout.addView(color);

        }

    }
    private  void assignUIReferenceMobile2()
    {

        linearLayout2 =findViewById(R.id.linearLayout);
        mobileTitle2 = findViewById(R.id.text_Mobile_title2);
        cpuText2 = findViewById(R.id.text_cpu2);
        ramText2 = findViewById(R.id.text_ram2);
        screenText2 = findViewById(R.id.text_screen2);
        cameraText2 = findViewById(R.id.text_camera2);
        frontCameraText2 = findViewById(R.id.text_front_camera2);
        batteryText2 = findViewById(R.id.text_battery2);
        storageText2 = findViewById(R.id.text_storage2);
        detailsText2 = findViewById(R.id.text_details2);
        mobileImage2 = findViewById(R.id.mobile_photo2);
        networkText2 = findViewById(R.id.text_network2);
        linearLayout2= findViewById(R.id.color2);
        Picasso.with(this).load(Constants.IMG_URL+mobile2.getImage()).into(mobileImage2);

        mobileTitle2.setText(mobile2.getName());
        cpuText2.setText(mobile2.getCpu());
        ramText2.setText(mobile.getRam());
        screenText2.setText(mobile.getScreen());
        networkText2.setText(mobile.getNetworks());
        cameraText2.setText(mobile.getMain_camera());
        frontCameraText2.setText(mobile.getFront_camera());
        batteryText2.setText(mobile.getBattery());
        storageText2.setText(mobile.getInternal_storage());
        detailsText2.setText(mobile.getOther_details());


        String[] colors = mobile2.getColors().split(";");
        for (int i =0 ;i<colors.length;i++)
        {
            LinearLayout color = new LinearLayout(this);
            color.setBackgroundColor(Color.parseColor(colors[i]));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80 ,30);
            params.setMargins(5,5,5,5);
            color.setLayoutParams(params);
            linearLayout2.addView(color);

        }

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}

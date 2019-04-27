package com.example.emma_tel.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.emma_tel.R;
import com.example.emma_tel.helprs.MatrialEditText;

import com.rengwuxian.materialedittext.MaterialEditText;



public class Utils {
    private Context mContext;


    public Utils(Context mContext) {
        this.mContext = mContext;
    }



    public static Utils getInstance(Context context){
        return new Utils(context);
    }


    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean notEmpty(MaterialEditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return true;

        return false;
    }




    public void setFont(){
       // Calligrapher calligrapher = new Calligrapher(mContext);
        //calligrapher.setFont((Activity) mContext,Constants.FONT_STYLE,true);
    }
}

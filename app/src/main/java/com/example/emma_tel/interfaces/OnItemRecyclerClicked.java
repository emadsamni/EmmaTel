package com.example.emma_tel.interfaces;

import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Mobile;
import com.example.emma_tel.models.Tablet;

public interface OnItemRecyclerClicked {
    void onClickedRecyclerItem(Mobile mobile);
    void onClickedRecyclerItem(Tablet tablet);
    void onClickedRecyclerItem(Accessory accessory);
}

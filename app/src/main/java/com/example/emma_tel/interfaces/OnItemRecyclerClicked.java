package com.example.emma_tel.interfaces;

import com.example.emma_tel.models.Accessory;
import com.example.emma_tel.models.Mobile;

public interface OnItemRecyclerClicked {
    void onClickedRecyclerItem(Mobile mobile);
    void onClickedRecyclerItem(Accessory accessory);
}

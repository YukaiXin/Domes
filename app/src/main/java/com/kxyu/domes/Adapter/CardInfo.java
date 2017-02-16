package com.kxyu.domes.Adapter;

/**
 * Created by yuki_cool on 2017/2/16.
 */

import com.google.gson.annotations.SerializedName;

public class CardInfo {
    @SerializedName("cardname")
    public String cardName;

    @SerializedName("showname")
    public String showTitleName;

    @SerializedName("truename")
    public String category;

    @SerializedName("cardtype")
    public int type;

    @SerializedName("isopen")
    public int isOpen;

    @SerializedName("display")
    public int isdisplay;

    @SerializedName("greeting")
    public String greeting;

    @SerializedName("is_setting")
    public int isSetting;

    public int cardTitleRes;

    public int cardColorRes;

    public int cardGreetings;

    public String companyCustomTitle;

    public String cardGreeting;

    public int show;

    public String webCard;

    public int settting;

    public CardInfo() {

    }

    public CardInfo(int cardTitleRes, int cardColorRes) {
        this.cardTitleRes = cardTitleRes;
        this.cardColorRes = cardColorRes;
    }

    public CardInfo(int cardTitleRes, int cardColorRes, int cardGreetings) {
        this.cardTitleRes = cardTitleRes;
        this.cardColorRes = cardColorRes;
        this.cardGreetings = cardGreetings;
    }
}


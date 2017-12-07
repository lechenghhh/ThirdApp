package com.lecheng.hello.thirdapp.ActivityList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

//项目地址：https://github.com/medyo/fancybuttons#supported-attributes
public class Aty066FancyBtn extends AppCompatActivity {

    @Bind(R.id.btnSpotify)
    FancyButton btnSpotify;
    @Bind(R.id.btn2)
    FancyButton btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty066_fancybtn);
        ButterKnife.bind(this);

        btnFacebook.setText("Login with Facebook");
        btnFacebook.setBackgroundColor(Color.parseColor("#3b5998"));
        btnFacebook.setFocusBackgroundColor(Color.parseColor("#5474b8"));
        btnFacebook.setTextSize(17);
        btnFacebook.setRadius(5);
        btnFacebook.setIconResource("\uf082");
        btnFacebook.setIconPosition(FancyButton.POSITION_LEFT);
        btnFacebook.setFontIconSize(30);
    }
/*
Supported Attributes
XML Attribute	Java Attribute	Description
fancy:fb_text	setText(String)	Text of the button
fancy:fb_textColor	setTextColor(int)	Text Color of the button
fancy:fb_textSize	setTextSize(int)	Size of the text
fancy:fb_textFont	setCustomTextFont(String)	FontFamily of the text
fancy:fb_textGravity	setTextGravity(Int)	Gravity of the text
fancy:fb_iconResource	setIconResource(Drawable)	Drawable icon of the button
fancy:fb_iconPosition	setIconPosition(int)	Position of the icon : Left, Right, Top, Bottom
fancy:fb_fontIconResource	setIconResource(String)	font icon of the button
fancy:fb_fontIconSize	setFontIconSize(int)	Size of the icon
fancy:fb_iconFont	setCustomIconFont(String)	FontFamily of the icon
fancy:fb_borderWidth	setBorderWidth(int)	Width of the border
fancy:fb_borderColor	setBorderColor(int)	Color of the border
fancy:fb_defaultColor	setBackgroundColor(int)	Background color of the button
fancy:fb_focusColor	setFocusBackgroundColor(int)	Focus Color of button background
fancy:fb_disabledColor	setDisableBackgroundColor(int)	Disabled Color of button background
fancy:fb_disabledTextColor	setDisableTextColor(int)	Disabled Color of button text
fancy:fb_disabledBorderColor	setDisableBorderColor(int)	Disabled Color of button border
fancy:fb_radius	setRadius(int)	Radius of the button
fancy:fb_radius(TopLeft, TopRight,BottomLeft,BottomRight)	setRadius(int[] radius)	Custom Radius for each button corner
fancy:fb_iconPaddingLeft	setIconPadding(int,int,int,int)	Icon Padding
fancy:fb_iconPaddingRight	setIconPadding(int,int,int,int)	Icon Padding
fancy:fb_iconPaddingTop	setIconPadding(int,int,int,int)	Icon Padding
fancy:fb_iconPaddingBottom	setIconPadding(int,int,int,int)	Icon Padding
fancy:fb_ghost	setGhost(boolean)	Ghost (Hollow)
fancy:fb_useSystemFont	setUsingSystemFont(boolean)	If enabled, the button title will ignore its custom font and use the default system font
* */
}

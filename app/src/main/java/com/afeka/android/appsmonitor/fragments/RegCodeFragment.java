package com.afeka.android.appsmonitor.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.afeka.android.appsmonitor.R;
import com.google.android.gms.vision.text.Text;

public class RegCodeFragment extends DialogFragment {
    String regCode;

    public static RegCodeFragment newInstance(String regCode) {
        RegCodeFragment f = new RegCodeFragment();

        // Supply regCode input as an argument.
        Bundle args = new Bundle();
        args.putString("regCode", regCode);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regCode = getArguments().getString("regCode");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = android.R.style.Theme_Holo_Light_Dialog;

        setStyle(style, theme);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Your Registration Code");
        View v = inflater.inflate(R.layout.fragment_show_reg_code, container, false);
        TextView tv = (TextView) v.findViewById(R.id.regcode);

        tv.setText(regCode);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        return v;
    }
}
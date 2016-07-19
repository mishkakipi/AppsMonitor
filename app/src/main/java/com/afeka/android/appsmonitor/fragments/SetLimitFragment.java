package com.afeka.android.appsmonitor.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afeka.android.appsmonitor.R;
import com.google.android.gms.plus.PlusOneButton;

import java.util.Calendar;

public class SetLimitFragment  extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    OnTimePickedListener mCallback;


    public interface OnTimePickedListener {
        void onTimePicked(int hour, int minute);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnTimePickedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTimePickedListener.");
        }
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        if(mCallback != null)
        {
            mCallback.onTimePicked(hour, minute);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*
        // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
        */

        mCallback = (OnTimePickedListener)getActivity();

        Bundle bundle = this.getArguments();
        int hour = bundle.getInt("hour");
        int minute = bundle.getInt("minute");

        // Create a new instance of TimePickerDialog and return it
        //return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        TimePickerDialog tpd = new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT
                ,this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        TextView tvTitle = new TextView(getActivity());
        tvTitle.setText("Set time limit for application");
        tvTitle.setBackgroundColor(Color.parseColor("#EEE8AA"));
        tvTitle.setPadding(5, 3, 5, 3);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        tpd.setCustomTitle(tvTitle);
        // Create a new instance of TimePickerDialog and return it
            return tpd;
        }
}

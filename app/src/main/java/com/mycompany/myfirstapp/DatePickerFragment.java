package com.mycompany.myfirstapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by garrett on 2/10/15.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    public final static String EXTRA_DATE_DAY = "com.mycompany.myfirstapp.DATE_DAY";
    public final static String EXTRA_DATE_MONTH = "com.mycompany.myfirstapp.DATE_MONTH";
    public final static String EXTRA_DATE_YEAR = "com.mycompany.myfirstapp.DATE_YEAR";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        ((MyActivity) getActivity()).renderUsersDepartTime(year, month + 1, day);
    }
}
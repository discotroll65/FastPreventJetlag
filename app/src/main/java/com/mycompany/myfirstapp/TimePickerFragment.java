package com.mycompany.myfirstapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by garrett on 2/10/15.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    public final static String EXTRA_TIME_HOUR = "com.mycompany.myfirstapp.TIME_HOUR";
    public final static String EXTRA_TIME_MINUTE = "com.mycompany.myfirstapp.TIME_MINUTE";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Intent intent = new Intent(getActivity(), MyActivity.class);

        intent.putExtra(EXTRA_TIME_HOUR, hourOfDay);
        intent.putExtra(EXTRA_TIME_MINUTE, minute);

//        startActivity(intent);
        ((MyActivity) getActivity()).renderUsersDepartTime(hourOfDay, minute);


    }
}

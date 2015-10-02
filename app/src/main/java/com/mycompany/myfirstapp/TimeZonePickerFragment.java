package com.mycompany.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.codetroopers.betterpickers.recurrencepicker.RecurrencePickerDialogFragment;
import com.mycompany.myfirstapp.R;
import com.mycompany.myfirstapp.BaseSampleActivity;
import com.codetroopers.betterpickers.timezonepicker.TimeZoneInfo;
import com.codetroopers.betterpickers.timezonepicker.TimeZonePickerDialogFragment;

import java.util.Date;
import java.util.GregorianCalendar;

public class TimeZonePickerFragment extends BaseSampleActivity
        implements TimeZonePickerDialogFragment.OnTimeZoneSetListener {

    private TextView text;
    private Button button;

    private EventRecurrence mEventRecurrence = new EventRecurrence();

    private static final String FRAG_TAG_TIME_ZONE_PICKER = "timeZonePickerDialogFragment";

    private String mRrule;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mycompany.myfirstapp.R.layout.text_and_button);

        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.tzbutton);

        text.setText("--");
        button.setText("Set Time Zone");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                Bundle b = new Bundle();
                GregorianCalendar t = new GregorianCalendar();
                t.setTime(new Date());
                b.putLong(TimeZonePickerDialogFragment.BUNDLE_START_TIME_MILLIS, t.getTimeInMillis());
                b.putString(TimeZonePickerDialogFragment.BUNDLE_TIME_ZONE, t.getTimeZone().toString());

                // may be more efficient to serialize and pass in EventRecurrence
                b.putString(RecurrencePickerDialogFragment.BUNDLE_RRULE, mRrule);

                TimeZonePickerDialogFragment tzpd = (TimeZonePickerDialogFragment) fm
                        .findFragmentByTag(FRAG_TAG_TIME_ZONE_PICKER);
                if (tzpd != null) {
                    tzpd.dismiss();
                }
                tzpd = new TimeZonePickerDialogFragment();
                tzpd.setArguments(b);
                tzpd.setOnTimeZoneSetListener(TimeZonePickerFragment.this);
                tzpd.show(fm, FRAG_TAG_TIME_ZONE_PICKER);
            }
        });
    }

    @Override
    public void onTimeZoneSet(TimeZoneInfo tzi) {
        text.setText(tzi.mDisplayName);
    }
}
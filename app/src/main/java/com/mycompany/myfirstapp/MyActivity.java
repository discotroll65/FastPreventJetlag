package com.mycompany.myfirstapp;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import net.danlew.android.joda.JodaTimeAndroid;
import net.danlew.android.joda.ResourceZoneInfoProvider;
import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;

public class MyActivity extends AppCompatActivity {
    private LocalTime usersDepartTime;
    public DateTime usersDepartDate;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView departingDate;
    private TextView departingTime;
    private int year, month, day;
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    /** Called when the user clicks the Send button */

    public void setUsersDepartTime(int hour, int min){
        usersDepartTime = new LocalTime(hour, min);
    }

    public void setUsersDepartDate(int year, int month, int day){
        usersDepartDate = new DateTime(year,month,day,0,0);
    }

    public void renderUsersDepartTime(int year, int month, int day){
        setUsersDepartDate(year, month, day);
        showDepartingDate(usersDepartDate);
    }

    public void renderUsersDepartTime(int hour, int min){
        setUsersDepartTime(hour, min);
        showDepartingTime(usersDepartTime);
    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalTime currentTime = new LocalTime();
        LocalTime departTimeToShow;
        if (usersDepartTime.toString().equals(new LocalTime(0,0,0).toString())) {
            departTimeToShow = currentTime;
        } else {
            departTimeToShow = usersDepartTime;
        }
        showDepartingTime(departTimeToShow);


        DateTime departDateToShow = usersDepartDate;

        if (usersDepartDate.toString().equals(new DateTime(0,1,1,0,0).toString())){
            departDateToShow =  currentTime.toDateTimeToday().plusMonths(1);
        }

        showDepartingDate(departDateToShow);
    }
    private void showDepartingDate(DateTime dateToShow) {
        departingDate = (TextView) findViewById(R.id.departing_date);
        departingDate.setText(dateToShow.toString("MMM/d/y"));
    }

    private void showDepartingTime(LocalTime departingTimeToShow) {
        departingTime = (TextView) findViewById(R.id.departing_time);
        departingTime.setText(departingTimeToShow.toString(DateTimeFormat.forPattern("hh:mma")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);

        // Get the message from the intent
        Intent intent = getIntent();
        int departHour = intent.getIntExtra(TimePickerFragment.EXTRA_TIME_HOUR, 0);
        int departMin = intent.getIntExtra(TimePickerFragment.EXTRA_TIME_MINUTE, 0);
        int departDay = intent.getIntExtra(DatePickerFragment.EXTRA_DATE_DAY, 0);
        int departMonth = intent.getIntExtra(DatePickerFragment.EXTRA_DATE_MONTH, 0);
        int departYear = intent.getIntExtra(DatePickerFragment.EXTRA_DATE_YEAR, 0);


        //Make the Text View
        if((departHour != -1) && departMin != -1) {
            usersDepartTime = new LocalTime(departHour, departMin);
        }

        if(departMonth != 0){
            usersDepartDate = new DateTime(departYear, departMonth, departDay,0,0);
        } else {
            usersDepartDate = new DateTime(0,1,1,0,0);
        }

        setContentView(R.layout.activity_my);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

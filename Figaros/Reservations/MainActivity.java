package hichamwakrim.me.reservations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

import java.util.Calendar;

public class MainActivity extends Activity {


    CalendarView myCalendarView;
    //these ints store the date of the appointment
    int yearSecond;
    int monthSecond;
    int dayOfMonthSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeCalendar();
        //code for the continue button which starts the next activity
        Button buttonX = (Button)findViewById(R.id.dateButton);
        buttonX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // this starts the next activity and send the date data with it
                Intent intent = new Intent(MainActivity.this, Timeselect.class);
                intent.putExtra("year", yearSecond);
                intent.putExtra("month", monthSecond);
                intent.putExtra("day", dayOfMonthSecond);
                startActivity(intent);
            }

        });
    }

    //here are the settings for the calendarview
    private void initializeCalendar() {
        myCalendarView = (CalendarView)findViewById(R.id.calendar);
        myCalendarView.setOnDateChangeListener(new OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                yearSecond = year;
                monthSecond = month;
                dayOfMonthSecond = dayOfMonth;
            }
        });
        myCalendarView.setShowWeekNumber(false);
        myCalendarView.setFirstDayOfWeek(2);
    }
}


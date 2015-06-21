package hichamwakrim.me.reservations;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class confirmationScreen extends Activity {

    // passed on info from the previous activities
    int year;
    int month;
    int day;
    int time;

    //these strings are used in the confirmation screen to display the date and time better
    String timefinal;
    String monthfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_screen);

        //data from previous activities
        Intent intent = getIntent();
        year = intent.getIntExtra("year",0);
        month = intent.getIntExtra("month",0);
        day = intent.getIntExtra("day",0);
        time = intent.getIntExtra("time", 0);
        //this switch statement is used to change the time from an int to a string with the appropriate time
        switch(time) {
            case 0: timefinal = "8:00";
                    break;
            case 1: timefinal = "9:00";
                    break;
            case 2: timefinal = "10:00";
                    break;
            case 3: timefinal = "11:00";
                    break;
            case 4: timefinal = "12:00";
                    break;
            case 5: timefinal = "13:00";
                    break;
            case 6: timefinal = "14:00";
                    break;
            case 7: timefinal = "15:00";
                    break;
            case 8: timefinal = "16:00";
                    break;
            case 9: timefinal = "17:00";
                    break;
        }
        //this switch statement is used to change the month of the date from a number to a string
        switch(month) {
            case 0: monthfinal = "januari";
                break;
            case 1: monthfinal = "februari";
                break;
            case 2: monthfinal = "maart";
                break;
            case 3: monthfinal = "april";
                break;
            case 4: monthfinal = "mei";
                break;
            case 5: monthfinal = "juni";
                break;
            case 6: monthfinal = "juli";
                break;
            case 7: monthfinal = "augustus";
                break;
            case 8: monthfinal = "september";
                break;
            case 9: monthfinal = "october";
                break;
            case 10: monthfinal = "november";
                break;
            case 11: monthfinal = "december";
                break;
        }

        //the empty TextView is found with this statement, and then the values from the previous activities are loaded in the text
        TextView tv = (TextView) findViewById(R.id.tv_date);
        tv.setText(String.valueOf(day)+ " " + monthfinal+ " " + String.valueOf(year) + " om " + timefinal + " uur?");
    }

    //this function is bound to a button which confirms the appointment, the data of the appointment is sent to the database
    public void confirmation(View view){
        Button button = (Button) findViewById(R.id.confirmationButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Afspraak staat gepland",Toast.LENGTH_LONG).show();
                //TODO add appointment to the table in parse using the data passes on by the previous activities
            }
        });
    }
}

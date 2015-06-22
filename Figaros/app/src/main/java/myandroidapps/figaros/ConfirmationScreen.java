package myandroidapps.figaros;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class ConfirmationScreen extends BaseActivity {

    // passed on info from the previous activities
    int year;
    int month;
    int day;
    int time;

    //these strings are used in the confirmation screen to display the date and time better
    String timeFinal;
    String monthFinal;

    Button yesButton, noButton;

    StoreItemInfo currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_screen);

        yesButton = (Button) findViewById(R.id.confirmationButton);
        noButton = (Button) findViewById(R.id.declineButton);

        //data from previous activities
        Intent intent = getIntent();
        year = intent.getIntExtra("year",0);
        month = intent.getIntExtra("month",0);
        day = intent.getIntExtra("day",0);
        time = intent.getIntExtra("time", 0);
        currentItem = (StoreItemInfo) intent.getSerializableExtra("storeitem");

        //this switch statement is used to change the time from an int to a string with the appropriate time
        switch(time) {
            case 0: timeFinal = "8:00";
                break;
            case 1: timeFinal = "9:00";
                break;
            case 2: timeFinal = "10:00";
                break;
            case 3: timeFinal = "11:00";
                break;
            case 4: timeFinal = "12:00";
                break;
            case 5: timeFinal = "13:00";
                break;
            case 6: timeFinal = "14:00";
                break;
            case 7: timeFinal = "15:00";
                break;
            case 8: timeFinal = "16:00";
                break;
            case 9: timeFinal = "17:00";
                break;
        }
        //this switch statement is used to change the month of the date from a number to a string
        switch(month) {
            case 0: monthFinal = "januari";
                break;
            case 1: monthFinal = "februari";
                break;
            case 2: monthFinal = "maart";
                break;
            case 3: monthFinal = "april";
                break;
            case 4: monthFinal = "mei";
                break;
            case 5: monthFinal = "juni";
                break;
            case 6: monthFinal = "juli";
                break;
            case 7: monthFinal = "augustus";
                break;
            case 8: monthFinal = "september";
                break;
            case 9: monthFinal = "october";
                break;
            case 10: monthFinal = "november";
                break;
            case 11: monthFinal = "december";
                break;
        }

        //the empty TextView is found with this statement, and then the values from the previous activities are loaded in the text
        TextView tv = (TextView) findViewById(R.id.tv_date);
        tv.setText(String.valueOf(day)+ " " + monthFinal+ " " + String.valueOf(year) + " om " + timeFinal + " uur?");
        confirmation();
    }

    //this function is bound to a button which confirms the appointment, the data of the appointment is sent to the database
    public void confirmation(){
        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ParseQuery query = new ParseQuery("Barberpage");
                query.whereEqualTo("barbername", currentItem.getStoreName());
                query.whereEqualTo("year", year);
                query.whereEqualTo("start", timeFinal);
                query.whereEqualTo("month", monthFinal);
                query.whereEqualTo("day", day);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Er is al een afspraak op deze tijd", Toast.LENGTH_SHORT).show();
                        } else {
                            if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                                ParseObject a = new ParseObject("Barberpage");
                                a.put("start", timeFinal);
                                a.put("month", monthFinal);
                                a.put("barbername", currentItem.getStoreName());
                                a.put("year", year);
                                a.put("day", day);
                                a.saveInBackground();
                                Toast.makeText(getApplicationContext(), "Afspraak staat gepland", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        }
                    }
                });
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationScreen.this, AppointmentActivity.class);
                startActivity(intent);
            }
        });

    }
}
package myandroidapps.figaros;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TimeSelectActivity extends BaseActivity {

    //this is the arraylist for the times which need to be displayed in the ListView
    List<String> times = new ArrayList<String>();
    private ListView lv;
    //These ints are passed on from the previous activity
    int year;
    int month;
    int day;
    StoreItemInfo currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_select);
        //this finds the ListView
        lv = (ListView) findViewById(R.id.lvTimes);
        populateTimes();
        //This adds items from an ArrayList to the ListView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                times );
        lv.setAdapter(arrayAdapter);

        //This gets the data from the previous activity
        Intent intent = getIntent();
        year = intent.getIntExtra("year",0);
        month = intent.getIntExtra("month",0);
        day = intent.getIntExtra("day",0);
        currentItem = (StoreItemInfo) intent.getSerializableExtra("storeitem");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TimeSelectActivity.this, ConfirmationScreen.class);
                //this sends the data of the date + the time to the next activity
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("time",i);
                intent.putExtra("storeitem", currentItem);
                startActivity(intent);
            }
        });
    }

    //this function adds times to the ListView. It only adds times that are not stored already in the database
    private void populateTimes() {
        //TODO get appointment data from parse based on the date selected on the previous screen
        //TODO if there are appointment(s) on the date that is selected, check the dateTimeNumber of the appointment(s) and exclude those from being added to the listview
        /*
        time selected is an int going from 0 to 9, it needs to be stored as an int on parse but converted to a String with the appropriate time so:
        8:00 = 0
        9:00 = 1
        enz.
        17:00 = 9
         */
        times.add("8:00");
        times.add("9:00");
        times.add("10:00");
        times.add("11:00");
        times.add("12:00");
        times.add("13:00");
        times.add("14:00");
        times.add("15:00");
        times.add("16:00");
        times.add("17:00");
    }
}

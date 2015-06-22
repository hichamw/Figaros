package myandroidapps.figaros;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyAppointmentsActivity extends BaseActivity {

    List<Appointment> appointmentList = new ArrayList<>();
    Appointment currentAppointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        retrieveFromDB();
    }

    public void retrieveFromDB() {
        ParseQuery<ParseObject> query = new ParseQuery<>("Barberpage");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject parsedObject: parseObjects) {
                        String name = parsedObject.getString("barbername");
                        String time = parsedObject.getString("start");
                        String day = String.valueOf(parsedObject.getInt("day"));
                        String month = parsedObject.getString("month");
                        String year = String.valueOf(parsedObject.getInt("year"));
                        String objID = parsedObject.getObjectId();
                        Log.d("app", name);
                        Log.d("app", time);
                        Log.d("app", day);
                        Log.d("app", month);
                        Log.d("app", year);
                        Appointment a = new Appointment(time, day, month, year, name, objID);
                        appointmentList.add(a);
                    }
                } else {
                    e.printStackTrace();
                }
                // function is called here to prevent the page from loading before
                // it retrieves any data
                populateAppointmentListView();
            }
        });

    }
    private void populateAppointmentListView() {
        ArrayAdapter<Appointment> adapter= new StoreListAdapter();
        ListView storeMenuList = (ListView) findViewById(R.id.listViewAppointments);
        storeMenuList.setAdapter(adapter);
    }

    private class StoreListAdapter extends ArrayAdapter<Appointment> implements Serializable {
        public StoreListAdapter() {
            super(MyAppointmentsActivity.this, R.layout.list_appointment_layout, appointmentList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View storeItemView = convertView; // the view which has to be shown
            if (storeItemView == null) { // if the view somehow isn't there, create it
                storeItemView = getLayoutInflater().inflate(R.layout.list_appointment_layout, parent, false);
            }

            currentAppointment = appointmentList.get(position); // gets item from ArrayList with stores

            TextView nameTxt = (TextView) storeItemView.findViewById(R.id.txtaName);
            nameTxt.setText(currentAppointment.getBarberName());

            TextView timeTxt = (TextView) storeItemView.findViewById(R.id.txtaTime);
            timeTxt.setText(currentAppointment.getStartTime());

            TextView dateTxt = (TextView) storeItemView.findViewById(R.id.txtaDate);
            dateTxt.setText(currentAppointment.getDay() + " " + currentAppointment.getMonth() + " " + currentAppointment.getYear());




            return storeItemView;
        }
    }
}

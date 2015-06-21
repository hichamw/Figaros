package myandroidapps.figaros;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class BarberPageActivity extends BaseActivity {


    List<StoreItemInfo> storeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_page);
        Intent bpIntent = this.getIntent();
        StoreItemInfo currentItem = (StoreItemInfo) bpIntent.getSerializableExtra("storeItem");
        TextView nameTxt = (TextView) findViewById(R.id.txtBname);
        nameTxt.setText(currentItem.getStoreName());

        TextView addressTxt = (TextView) findViewById(R.id.txtBaddress);
        addressTxt.setText(currentItem.getStoreAddress());

        TextView cityTxt = (TextView) findViewById(R.id.txtBcity);
        cityTxt.setText(currentItem.getStoreCity());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barber_page, menu);
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

package myandroidapps.figaros;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);



        final ImageButton searchBtn = (ImageButton) findViewById(R.id.btnSearch);
        final ImageButton favBtn = (ImageButton) findViewById(R.id.btnFavourites);




        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent switchPage = new Intent(MainScreen.this, SecondActivity.class);
                //startActivity(switchPage);
                Toast.makeText(getApplicationContext(), "Search Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent switchPage = new Intent(MainScreen.this, SecondActivity.class);
                //startActivity(switchPage);
                Toast.makeText(getApplicationContext(), "Favourites Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }




}

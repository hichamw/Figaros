package myandroidapps.figaros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                Toast.makeText(getApplicationContext(), "Search Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainScreen.this, SearchActivity.class));
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Favourites Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainScreen.this, FavoritesActivity.class));
            }
        });
    }




}

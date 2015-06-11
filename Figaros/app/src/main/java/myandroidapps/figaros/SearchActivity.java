package myandroidapps.figaros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends BaseActivity {


    private List<StoreItemInfo> storeList = new ArrayList<StoreItemInfo>();
    StoreItemInfo currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        populateStoreList();
        populateStoreListView();
        processItemClick();
    }

    private void populateStoreList() {
        storeList.add(new StoreItemInfo("Booms salon", "Schubertlaan 79", "3055 HN Rotterdam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("Kapsalon Pino & Co", "Pannekoekstraat 20a", "3011 LG  Rotterdam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("Kapsalon Kapperazzi", "Hof van Spaland 20a", "3121 CB  Schiedam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("Christiaan Lifestyle Salon", "Nieuwe Binnenweg 66", "3015 BB  Rotterdam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("WOW Kappers", "Corsicalaan 29", "3059 XX  Rotterdam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("Team Kapsalon", "Zuidplein Hoog 426", "3083 BL  Rotterdam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("Kapsalon All-Inn", "Strevelsweg 79", "3073 DV  Rotterdam", R.drawable.ic_photo));
        storeList.add(new StoreItemInfo("Salt & Pepper Kappers", "Straatweg 55s", "3051 BD  Rotterdam", R.drawable.ic_photo));
    }

    private void populateStoreListView() {
        ArrayAdapter<StoreItemInfo> adapter= new StoreListAdapter();
        ListView storeMenuList = (ListView) findViewById(R.id.listViewStores);
        storeMenuList.setAdapter(adapter);
    }

    private void processItemClick() {
        ListView storeMenuList = (ListView) findViewById(R.id.listViewStores);
        storeMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                StoreItemInfo currentItem = storeList.get(position);
                String message = "You have pressed " + currentItem.getStoreName();
                Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class StoreListAdapter extends ArrayAdapter<StoreItemInfo> implements Serializable {
        public StoreListAdapter() {
            super(SearchActivity.this, R.layout.list_item_layout, storeList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View storeItemView = convertView; // the view which has to be shown
            if (storeItemView == null) { // if the view somehow isn't there, create it
                storeItemView = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
            }

            currentItem = storeList.get(position); // gets item from ArrayList with stores

            TextView nameTxt = (TextView) storeItemView.findViewById(R.id.txtName);
            nameTxt.setText(currentItem.getStoreName());

            TextView addressTxt = (TextView) storeItemView.findViewById(R.id.txtAddress);
            addressTxt.setText(currentItem.getStoreAddress());

            TextView cityTxt = (TextView) storeItemView.findViewById(R.id.txtCity);
            cityTxt.setText(currentItem.getStoreCity());

            ImageView storePhoto = (ImageView) storeItemView.findViewById(R.id.imgStore);
            storePhoto.setImageResource(currentItem.getStorePhoto());

            final ImageButton favBtn = (ImageButton) storeItemView.findViewById(R.id.btnFavorite);
            favBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) { // here probably something went wrong
                    String message = "Your pos is: " + position;
                    Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();
                    Intent addFavorite = new Intent(SearchActivity.this, FavoritesActivity.class);
                    addFavorite.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    addFavorite.putExtra("dataObject", storeList.get(position)); // Here i send a
                    startActivity(addFavorite);
                }
            });



            return storeItemView;
        }
    }




}

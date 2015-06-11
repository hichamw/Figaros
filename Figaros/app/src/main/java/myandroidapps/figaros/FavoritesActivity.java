package myandroidapps.figaros;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class FavoritesActivity extends BaseActivity {

    public List<StoreItemInfo> favoriteList = new ArrayList<StoreItemInfo>();
    ArrayAdapter<StoreItemInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        processIntent();
    }

    private void processIntent() {
        Intent intent = getIntent();
        Log.w("fav", "1");
        Log.w("fav", "2");
    }

    private void addIntent(Intent intent) {
        StoreItemInfo receivedItem = (StoreItemInfo) intent.getSerializableExtra("dataObject");
        Log.w("fav", "3");
        favoriteList.add(receivedItem);
        Log.w("fav", "4");
        populateStoreListView();
        Log.w("fav", "5");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.w("fav", "11");
        super.onNewIntent(intent);
        setIntent(intent);
        Log.w("fav", "22");
        addIntent(intent);

    }

    private void populateStoreListView() {
        adapter = new StoreListAdapter();
        ListView storeMenuList = (ListView) findViewById(R.id.favoriteListView);
        storeMenuList.setAdapter(adapter);
    }

    private class StoreListAdapter extends ArrayAdapter<StoreItemInfo> {
        public StoreListAdapter() {
            super(FavoritesActivity.this, R.layout.list_item_layout, favoriteList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View storeItemView = convertView;
            if (storeItemView == null) {
                storeItemView = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
            }
            if(favoriteList.get(position) != null) {
                StoreItemInfo currentItem = favoriteList.get(position);

                TextView nameTxt = (TextView) storeItemView.findViewById(R.id.txtName);
                nameTxt.setText(currentItem.getStoreName());

                TextView addressTxt = (TextView) storeItemView.findViewById(R.id.txtAddress);
                addressTxt.setText(currentItem.getStoreAddress());

                TextView cityTxt = (TextView) storeItemView.findViewById(R.id.txtCity);
                cityTxt.setText(currentItem.getStoreCity());

                ImageView storePhoto = (ImageView) storeItemView.findViewById(R.id.imgStore);
                storePhoto.setImageResource(currentItem.getStorePhoto());

                final ImageButton favBtn = (ImageButton) storeItemView.findViewById(R.id.btnFavorite);
                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = "Favorite deleted";
                        Toast.makeText(FavoritesActivity.this, message, Toast.LENGTH_SHORT).show();
                        StoreItemInfo itemRemove = getItem(position);
                        remove(itemRemove);
                    }
                });
            }
            return storeItemView;
        }
    }

}

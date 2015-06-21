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

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class FavoritesActivity extends BaseActivity {

    public List<StoreItemInfo> favoriteList = new ArrayList<StoreItemInfo>();
    ArrayAdapter<StoreItemInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        retrieveFromDB();
    }


    public void retrieveFromDB() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Barber");
        query.addDescendingOrder("Barber");
        query.whereEqualTo("favorite", 1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject parsedObject: parseObjects) {
                        String name = parsedObject.getString("name");
                        String address = parsedObject.getString("address");
                        String city = parsedObject.getString("city");
                        int photo = R.drawable.ic_photo;
                        String objID = parsedObject.getObjectId();
                        StoreItemInfo sii = new StoreItemInfo(name, address, city, photo, objID);
                        favoriteList.add(sii);
                    }
                } else {
                    e.printStackTrace();
                }
                populateStoreListView();

            }
        });

    }
    /*private void processIntent() {
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
    */

    private void populateStoreListView() {
        Log.d("TEST", "1");
        adapter = new StoreListAdapter();
        Log.d("TEST", "2");
        ListView storeMenuList = (ListView) findViewById(R.id.favoriteListView);
        Log.d("TEST", "3");
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
                        StoreItemInfo selectedItem = favoriteList.get(position);
                        String objID = selectedItem.getObjID();
                        Log.d("CURRENTITEM ID", objID);
                        Log.d("CURRENTITEM NAME", selectedItem.getStoreName());
                        ParseObject obj = ParseObject.createWithoutData("Barber", objID);

                        obj.put("favorite", 0);

                        obj.saveInBackground(new SaveCallback() {
                            public void done(com.parse.ParseException e) {
                                if (e == null) {
                                    Toast.makeText(FavoritesActivity.this, "Verwijderd", Toast.LENGTH_SHORT).show();
                                } else {
                                    // The save failed.
                                }
                            }
                        });
                        remove(selectedItem);

                    }
                });
            }
            return storeItemView;
        }
    }

}

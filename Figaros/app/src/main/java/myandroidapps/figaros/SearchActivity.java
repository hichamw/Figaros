package myandroidapps.figaros;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends BaseActivity {


    private List<StoreItemInfo> storeList = new ArrayList<>();
    StoreItemInfo currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        retrieveFromDB();

        processItemClick();

    }
        public void retrieveFromDB() {
            ParseQuery<ParseObject> query = new ParseQuery<>("Barber");
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
                            Log.d("STOREITEMINFO", sii.getStoreName());
                            storeList.add(sii);
                        }
                    } else {
                        e.printStackTrace();
                    }
                    // function is called here to prevent the page from loading before
                    // it retrieves any data
                    populateStoreListView();
                }
            });

        }

    private void populateStoreListView() {
        Log.d("TEST", "1");
        ArrayAdapter<StoreItemInfo> adapter= new StoreListAdapter();
        Log.d("TEST", "2");
        ListView storeMenuList = (ListView) findViewById(R.id.listViewStores);
        Log.d("TEST", "3");
        storeMenuList.setAdapter(adapter);
        Log.d("TEST", "4");
    }

    private void processItemClick() {
        ListView storeMenuList = (ListView) findViewById(R.id.listViewStores);
        storeMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                StoreItemInfo currentItem = storeList.get(position);
                Intent bpIntent = new Intent(getApplicationContext(), BarberPageActivity.class);
                bpIntent.putExtra("storeItem", currentItem);
                startActivity(bpIntent);
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
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StoreItemInfo selectedItem = storeList.get(position);
                    String objID = selectedItem.getObjID();

                    Log.d("CURRENTITEM ID", objID);
                    Log.d("CURRENTITEM NAME", selectedItem.getStoreName());
                    ParseObject obj = ParseObject.createWithoutData("Barber", objID);

                    obj.put("favorite", 1);

                    obj.saveInBackground(new SaveCallback() {
                        public void done(com.parse.ParseException e) {
                            if (e == null) {
                                Toast.makeText(SearchActivity.this, "Opgeslagen", Toast.LENGTH_SHORT).show();
                            } else {
                                // The save failed.
                            }
                        }
                    });

                }
            });
            final ImageButton callBtn = (ImageButton) storeItemView.findViewById(R.id.btnCall);
            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StoreItemInfo selectedItem = storeList.get(position);
                    String objID = selectedItem.getObjID();
                    ParseQuery query = ParseQuery.getQuery("Barber");
                    query.getInBackground(objID, new GetCallback<ParseObject>() {
                        public void done(ParseObject obj, com.parse.ParseException e) {
                            if (e == null) {
                                String telnr = obj.getString("telnr");
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + telnr));
                                startActivity(callIntent);
                            } else {
                                // something went wrong
                            }
                        }
                    });



            }
            });

            return storeItemView;
        }
    }

    public List<StoreItemInfo> getSiiList() {
        return storeList;
    }




}

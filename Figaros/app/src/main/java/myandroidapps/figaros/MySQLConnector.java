package myandroidapps.figaros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MySQLConnector {

    private static final String dbName = "deb90608_figaros";
    private static final String url = "jdbc:mysql://bahbv.nl:3306/deb90608_figaros";
    private static final String user = "deb90608_admin";
    private static final String pass = "zgZTbHimMA";

    ArrayList<StoreItemInfo> storeList = null;

    public ArrayList<StoreItemInfo> retrieveSearchPage() throws SQLException {
        try {
            Log.d("Connection", "Connecting1");
            Class.forName("com.mysql.jdbc.Driver");
            Log.d("Connection", "Connecting2");
            Connection con = DriverManager.getConnection(url, user, pass);
            Log.d("Connection", "Connected!");


            String result = "Database connection success\n";
            String query = "select NAME, ADRES, STAD from" + dbName +".BARBER";
            Log.d("Statement", "Creating Statement");
            Statement stmt = con.createStatement();
            Log.d("Statement", "Statement created");
            ResultSet rs = stmt.executeQuery(query);
            storeList = new ArrayList<StoreItemInfo>();
            while (rs.next()) {
                String storeName = rs.getString("NAME");
                String storeAddress = rs.getString("ADRES");
                String storeCity = rs.getString("STAD");
                StoreItemInfo sii = new StoreItemInfo(storeName, storeAddress, storeCity, R.drawable.ic_photo);
                storeList.add(sii);

            }
            return storeList;
        }

        catch(Exception e) {
            e.printStackTrace();
            Log.w("SQL", "SQLException occurred.");
        }
        return null;
    }
}
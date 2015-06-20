package myandroidapps.figaros;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/** Created by ninc__000 on 18-6-2015.
 */
public class ParseApp extends Application {
    String appID = "aZG14hANJEdzaWJ5MCCrrBYxoBksVwjbGkeEAtdy";
    String clientKey = "sMc8pIiG97bmngJMORRUu4OXUZPcHhvzditXfPoF";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, appID, clientKey);

    }




}

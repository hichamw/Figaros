package myandroidapps.figaros;


import java.io.Serializable;

/**
 * Created by ninc__000 on 6-6-2015.
 */
public class StoreItemInfo extends BaseActivity implements Serializable {


    private String storeName;
    private String storeAddress;
    private String storeCity;
    private int storePhoto;
    private String objID;

    public StoreItemInfo(String storeName, String storeAddress, String storeCity, int storePhoto, String objID) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeCity = storeCity;
        this.storePhoto = storePhoto;
        this.objID = objID;

    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public int getStorePhoto() {
        return storePhoto;
    }

    public String getObjID() {
        return objID;
    }
}

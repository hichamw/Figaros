package myandroidapps.figaros;

import java.io.Serializable;

public class Appointment extends BaseActivity implements Serializable {

    private String startTime;
    private String day;
    private String month;
    private String year;
    private String barberName;
    private String objID;

    public Appointment(String startTime, String day, String month, String year, String barberName, String objID) {
        this.startTime = startTime;
        this.day = day;
        this.month = month;
        this.year = year;
        this.barberName = barberName;
        this.objID = objID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getBarberName() {
        return barberName;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getObjID() {
        return objID;
    }
}

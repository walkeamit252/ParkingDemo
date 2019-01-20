package app.com.parkingdemo.database;

public class ParkingTable {

    public static final String TABLE_PARKING_NAME = "Parking";

    public static final String COLUMN_PARKING_ID = "parking_id";
    public static final String COLUMN_USER_ID = "user_email";
    public static final String COLUMN_PARKING_ISALLOCATED= "is_parking_allocated";

    public static final String CREATE_TABLE = "create table " + TABLE_PARKING_NAME + "(" + COLUMN_PARKING_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_ID + " TEXT, " + COLUMN_PARKING_ISALLOCATED + " TEXT);";

    public static final String DROP_USER_TABLE="DROP TABLE IF EXISTS "+TABLE_PARKING_NAME;

    private String parkingId;
    private String userId;
    private String parkingStatus;

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(String parkingStatus) {
        this.parkingStatus = parkingStatus;
    }
}

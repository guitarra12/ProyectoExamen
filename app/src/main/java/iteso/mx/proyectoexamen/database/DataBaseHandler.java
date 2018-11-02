package iteso.mx.proyectoexamen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ItesoStore.db";
    private static final int DATABASE_VERSION = 1;

    private static DataBaseHandler dataBaseHandler;

    //Tables
    public static final String TABLE_CITY = "City";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_STORE = "Store";
    public static final String TABLE_PRODUCT= "Product";
    public static final String TABLE_STORE_PRODUCT = "StoreProduct";

    //columns
    public static final String CITY_ID = "id";
    public static final String CITY_NAME = "name";

    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "name";

    public static final String STORE_ID = "id";
    public static final String STORE_NAME = "name";
    public static final String STORE_PHONE = "phone";
    public static final String STORE_IDCITY = "idcity";
    public static final String STORE_THUMBNAIL = "thumbnail";
    public static final String STORE_LATITUDE = "latitude";
    public static final String STORE_LONGITUDE = "longitude";

    public static final String PRODUCT_IDPRODUCT = "idproduct";
    public static final String PRODUCT_TITLE = "title";
    public static final String PRODUCT_IMAGE = "image";
    public static final String PRODUCT_IDCATEGORY = "idcategory";

    public static final String STORE_PRODUCT_ID = "id";
    public static final String STORE_PRODUCT_IDPRODUCT = "idproduct";
    public static final String STORE_PRODUCT_IDSTORE = "idstore";


    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context) {
        if(dataBaseHandler == null) {
            dataBaseHandler = new DataBaseHandler(context);
        }

        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCity = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT)", TABLE_CITY, CITY_ID, CITY_NAME);
        db.execSQL(tableCity);

        String tableCategory = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY," +
                "%s TEXT)", TABLE_CATEGORY, CATEGORY_ID, CATEGORY_NAME);
        db.execSQL(tableCategory);

        String tableProduct = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT, %s INTEGER, %s INTEGER)", TABLE_PRODUCT, PRODUCT_IDPRODUCT, PRODUCT_TITLE,
                PRODUCT_IMAGE, PRODUCT_IDCATEGORY);
        db.execSQL(tableProduct);

        String tableStoreProduct = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s INTEGER, %s INTEGER)", TABLE_STORE_PRODUCT, STORE_PRODUCT_ID,
                STORE_PRODUCT_IDPRODUCT, STORE_PRODUCT_IDSTORE);
        db.execSQL(tableStoreProduct);

        String tableStore = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT," +
                        "%s TEXT, %s INTEGER, %s INTEGER, %s DOUBLE, %s DOUBLE)", TABLE_STORE,
                STORE_ID, STORE_NAME, STORE_PHONE, STORE_IDCITY, STORE_THUMBNAIL, STORE_LATITUDE,
                STORE_LONGITUDE);
        db.execSQL(tableStore);

        db.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES (1,'TECHNOLOGY')",TABLE_CATEGORY,
                CATEGORY_ID, CATEGORY_NAME));
        db.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES (2,'HOME')",TABLE_CATEGORY,
                CATEGORY_ID, CATEGORY_NAME));
        db.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES (3,'ELECTRONICS')",TABLE_CATEGORY,
                CATEGORY_ID, CATEGORY_NAME));

        db.execSQL(String.format("INSERT INTO %s (%s) VALUES ('%s')", TABLE_CITY, CITY_NAME, "Zapopan"));
        db.execSQL(String.format("INSERT INTO %s (%s) VALUES ('%s')", TABLE_CITY, CITY_NAME, "Guadalajara"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Sin actualizaciones por aquí...
    }
}

package iteso.mx.proyectoexamen.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import iteso.mx.proyectoexamen.beans.Category;
import iteso.mx.proyectoexamen.beans.City;
import iteso.mx.proyectoexamen.beans.Store;

public class StoreControl {

    public void addStore(Store store, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseHandler.STORE_ID, store.getId());
        values.put(DataBaseHandler.STORE_NAME, store.getName());
        values.put(DataBaseHandler.STORE_PHONE, store.getPhone());
        values.put(DataBaseHandler.STORE_IDCITY, store.getCity().getId());
        values.put(DataBaseHandler.STORE_THUMBNAIL, store.getThumbnail());
        values.put(DataBaseHandler.STORE_LATITUDE, store.getLatitude());
        values.put(DataBaseHandler.STORE_LONGITUDE, store.getLongitude());

        db.insert(DataBaseHandler.TABLE_STORE, null, values);
        try{
            db.close();
        } catch (Exception e){}
    }

    public ArrayList<Store> getStores(DataBaseHandler  dh) {
        ArrayList<Store> val = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String select = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s", DataBaseHandler.STORE_ID,
                DataBaseHandler.STORE_NAME, DataBaseHandler.STORE_PHONE, DataBaseHandler.STORE_IDCITY
        ,DataBaseHandler.STORE_THUMBNAIL, DataBaseHandler.STORE_LATITUDE, DataBaseHandler.STORE_LONGITUDE
        ,DataBaseHandler.TABLE_STORE);

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()) {
            Store store = new Store();
            City temp = new City();

            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            temp.setId(cursor.getInt(3));
            store.setCity(temp);
            store.setThumbnail(cursor.getInt(4));
            store.setLatitude(cursor.getDouble(5));
            store.setLongitude(cursor.getDouble(6));

            val.add(store);
        }

        try {
            cursor.close();
            db.close();
        } catch (Exception e) {}

        return val;
    }

    public Store getStoreById(int  idStore,  DataBaseHandler  dh) {
        SQLiteDatabase db = dh.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = %d", DataBaseHandler.TABLE_STORE
        , DataBaseHandler.STORE_ID, idStore);
        Store store = null;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            store = new Store();
            City temp = new City();

            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            temp.setId(cursor.getInt(3));
            store.setCity(temp);
            store.setThumbnail(cursor.getInt(4));
            store.setLatitude(cursor.getDouble(5));
            store.setLongitude(cursor.getDouble(6));

            break;
        }

        try {
            cursor.close();
            db.close();
        } catch (Exception e) {}

        return store;
    }
}

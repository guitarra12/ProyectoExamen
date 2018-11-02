package iteso.mx.proyectoexamen.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import iteso.mx.proyectoexamen.beans.Category;

public class CategoryControl {

    public ArrayList<Category> getCategories(DataBaseHandler  dh) {
        ArrayList<Category> val = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String select = String.format("SELECT %s, %s FROM %s", DataBaseHandler.CATEGORY_ID,
                DataBaseHandler.CATEGORY_NAME, DataBaseHandler.TABLE_CATEGORY);

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            val.add(category);
        }

        try {
            cursor.close();
            db.close();
        } catch (Exception e) {}

        return val;
    }
}

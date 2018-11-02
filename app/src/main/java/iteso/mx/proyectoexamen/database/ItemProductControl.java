package iteso.mx.proyectoexamen.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import iteso.mx.proyectoexamen.beans.Category;
import iteso.mx.proyectoexamen.beans.City;
import iteso.mx.proyectoexamen.beans.ItemProduct;
import iteso.mx.proyectoexamen.beans.Store;

public class ItemProductControl {
    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(DataBaseHandler.PRODUCT_IDPRODUCT, itemProduct.getCode());
        values.put(DataBaseHandler.PRODUCT_TITLE, itemProduct.getTitle());
        values.put(DataBaseHandler.PRODUCT_IMAGE, itemProduct.getImage());
        values.put(DataBaseHandler.PRODUCT_IDCATEGORY, itemProduct.getCategory().getId());

        itemProduct.setCode((int) db.insert(DataBaseHandler.TABLE_PRODUCT, null, values));

        ContentValues vals = new ContentValues();

        vals.put(DataBaseHandler.STORE_PRODUCT_IDPRODUCT, itemProduct.getCode());
        vals.put(DataBaseHandler.STORE_PRODUCT_IDSTORE, itemProduct.getStore().getId());

        db.insert(DataBaseHandler.TABLE_STORE_PRODUCT, null, vals);
        try{
            db.close();
        } catch (Exception e){}
    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler  dh) {
        ArrayList<ItemProduct> val = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        /*String select = String.format("SELECT %s, %s, %s, %s FROM %s where %s = %d"
                , DataBaseHandler.PRODUCT_IDPRODUCT, DataBaseHandler.PRODUCT_TITLE
                , DataBaseHandler.PRODUCT_IMAGE, DataBaseHandler.PRODUCT_IDCATEGORY
                ,DataBaseHandler.TABLE_PRODUCT, DataBaseHandler.PRODUCT_IDCATEGORY, idCategory);*/
        String select = "SELECT P."+DataBaseHandler.PRODUCT_IDPRODUCT+", P."+DataBaseHandler.PRODUCT_TITLE
                            +", P."+DataBaseHandler.PRODUCT_IMAGE+", P."+DataBaseHandler.PRODUCT_IDCATEGORY
                            +", B."+DataBaseHandler.STORE_PRODUCT_IDSTORE
                +" FROM "+DataBaseHandler.TABLE_PRODUCT+" P INNER JOIN "+DataBaseHandler.TABLE_STORE_PRODUCT+" B "
                +" ON P."+DataBaseHandler.PRODUCT_IDPRODUCT+"=B."+DataBaseHandler.STORE_PRODUCT_IDPRODUCT
                +" WHERE P."+DataBaseHandler.PRODUCT_IDCATEGORY+"="+idCategory;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()) {
            ItemProduct itemProduct = new ItemProduct();
            CategoryControl categoryControl = new CategoryControl();
            List<Category> lCategorias = categoryControl.getCategories(dh);
            Category tCategory = null;
            int idCat;
            StoreControl storeControl = new StoreControl();

            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            idCat = cursor.getInt(3);
            for(Category cat : lCategorias) {
                if( cat.getId() == idCat ) {
                    tCategory = cat;
                    break;
                }
            }
            itemProduct.setCategory(tCategory);
            itemProduct.setStore(storeControl.getStoreById(cursor.getInt(4), dh));

            val.add(itemProduct);
        }

        try {
            cursor.close();
            db.close();
        } catch (Exception e) {}

        return val;
    }
}

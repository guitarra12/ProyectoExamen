package iteso.mx.proyectoexamen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import iteso.mx.proyectoexamen.beans.Category;
import iteso.mx.proyectoexamen.beans.ItemProduct;
import iteso.mx.proyectoexamen.beans.Store;
import iteso.mx.proyectoexamen.database.CategoryControl;
import iteso.mx.proyectoexamen.database.DataBaseHandler;
import iteso.mx.proyectoexamen.database.ItemProductControl;
import iteso.mx.proyectoexamen.database.StoreControl;

public class ActivityItem extends AppCompatActivity {
    private Spinner productos;
    private EditText titulo;
    private Spinner categorias;
    private Spinner tiendas;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        productos = findViewById(R.id.activity_item_productos);
        titulo = findViewById(R.id.activity_item_titulo);
        categorias = findViewById(R.id.activity_item_categorias);
        tiendas = findViewById(R.id.activity_item_tiendas);
        guardar = findViewById(R.id.activity_item_guardar);

        //Primer spinner, el de las imágenes de los productos
        ArrayAdapter<CharSequence> adapterProductos = ArrayAdapter.createFromResource(this
                , R.array.products_array, android.R.layout.simple_spinner_item);
        adapterProductos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productos.setAdapter(adapterProductos);

        DataBaseHandler dh = DataBaseHandler.getInstance(this);

        //Segundo spinner, el de las categorías
        CategoryControl categoryControl = new CategoryControl();
        List<Category> listaCategorias = categoryControl.getCategories(dh);
        ArrayAdapter<Category> adapterCategorias = new ArrayAdapter<Category>(this
                , android.R.layout.simple_spinner_item, listaCategorias) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setText(getItem(position).getName());
                return textView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setText(((Category)getItem(position)).getName());
                return view;
            }
        };
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(adapterCategorias);

        //Tercer spinner, el de las tiendas
        StoreControl storeControl = new StoreControl();
        List<Store> listaTiendas = storeControl.getStores(dh);
        ArrayAdapter<Store> adapterTiendas = new ArrayAdapter<Store>(this
        , android.R.layout.simple_spinner_item, listaTiendas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setText(getItem(position).getName());
                return textView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setText(((Store)getItem(position)).getName());
                return view;
            }
        };

        adapterTiendas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiendas.setAdapter(adapterTiendas);

        guardar.setOnClickListener((View v)->{
            ItemProduct nuevo = new ItemProduct();
            //DataBaseHandler dh = DataBaseHandler.getInstance(ActivityItem.this);
            ItemProductControl itemProductControl = new ItemProductControl();

            nuevo.setTitle(titulo.getText().toString());
            if( productos.getSelectedItem().equals("Mac") )
                nuevo.setImage(0);
            else
                nuevo.setImage(1);

            nuevo.setCategory(((Category)categorias.getSelectedItem()));
            nuevo.setStore(((Store)tiendas.getSelectedItem()));
            itemProductControl.addItemProduct(nuevo, dh);

            finish();
        });
    }
}

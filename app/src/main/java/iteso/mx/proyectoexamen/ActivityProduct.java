package iteso.mx.proyectoexamen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import iteso.mx.proyectoexamen.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {
    //:c not for this
    /*private TextView title;
    private TextView store;
    private TextView location;
    private TextView phone;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ItemProduct itemProduct = getIntent().getParcelableExtra("ITEM");
        title = findViewById(R.id.activity_detail_title);
        store = findViewById(R.id.activity_detail_store);
        location = findViewById(R.id.activity_detail_location);
        phone = findViewById(R.id.activity_detail_phone);
        image = findViewById(R.id.activity_detail_image);
        title.setText(itemProduct.getTitle());
        store.setText(itemProduct.getStore());
        location.setText(itemProduct.getLocation());
        phone.setText(itemProduct.getPhone());
        switch (itemProduct.getImage()) {
            case 0:
                image.setImageResource(R.drawable.mac);
                break;
            case 1:
                image.setImageResource(R.drawable.alienware);
                break;
        }
    }

    public void onClick(View v) {
        if(v.getId() == R.id.activity_detail_save) {
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setTitle(title.getText().toString());
            itemProduct.setStore(store.getText().toString());
            itemProduct.setPhone(phone.getText().toString());
            itemProduct.setLocation(location.getText().toString());

            Intent intent = new Intent();
            intent.putExtra("ITEM", itemProduct);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else if(v.getId() == R.id.activity_detail_cancel) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }*/
}

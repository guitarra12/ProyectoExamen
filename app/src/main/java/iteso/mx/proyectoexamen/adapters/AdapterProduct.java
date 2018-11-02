package iteso.mx.proyectoexamen.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import iteso.mx.proyectoexamen.ActivityMain;
import iteso.mx.proyectoexamen.ActivityProduct;
import iteso.mx.proyectoexamen.R;
import iteso.mx.proyectoexamen.beans.ItemProduct;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private ArrayList<ItemProduct> mDataSet;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterProduct(Context context, ArrayList<ItemProduct> myDataSet) {
        mDataSet = myDataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getTitle());
        holder.mProductStore.setText(mDataSet.get(position).getStore().getName());
        holder.mProductLocation.setText(mDataSet.get(position).getStore().getCity().getName());
        holder.mProductPhone.setText(mDataSet.get(position).getStore().getPhone());
        switch (mDataSet.get(position).getImage()) {
            case 0:
                holder.mProductImage.setImageResource(R.drawable.mac);
                break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.alienware);
                break;
        }
        switch (mDataSet.get(position).getStore().getThumbnail()) {
            case 0:
                holder.mProductThumbnail.setImageResource(R.drawable.bestbuy);
                break;
            case 1:
                holder.mProductThumbnail.setImageResource(R.drawable.ffrancia);
                break;
            case 2:
                holder.mProductThumbnail.setImageResource(R.drawable.rshack);
                break;
        }
        holder.mDetail.setOnClickListener((View v)->{
            Toast.makeText(context, mDataSet.get(position).toString(), Toast.LENGTH_LONG).show();

            /*Intent intent = new Intent(context, ActivityProduct.class);
            intent.putExtra("ITEM", mDataSet.get(position));
            ((ActivityMain)context).startActivityForResult(intent,1);*/
        });
        holder.mProductPhone.setOnClickListener((View v)->{
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mDataSet.get(position).getStore().getPhone()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button mDetail;
        public TextView mProductTitle;
        public TextView mProductStore;
        public TextView mProductLocation;
        public TextView mProductPhone;
        public ImageView mProductImage;
        public ImageView mProductThumbnail;
        public RelativeLayout mEventLayout;

        public ViewHolder(View v) {
            super(v);
            mEventLayout = (RelativeLayout)v.findViewById(R.id.item_product_layout);
            mDetail = (Button)v.findViewById(R.id.item_product_detail);
            mProductTitle = (TextView)v.findViewById(R.id.item_product_title);
            mProductStore = (TextView)v.findViewById(R.id.item_product_store);
            mProductLocation = (TextView)v.findViewById(R.id.item_product_location);
            mProductPhone = (TextView)v.findViewById(R.id.item_product_phone);
            mProductImage = (ImageView)v.findViewById(R.id.item_product_image);
            mProductThumbnail = (ImageView)v.findViewById(R.id.item_product_thumbnail);
        }
    }
}

package iteso.mx.proyectoexamen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

import iteso.mx.proyectoexamen.adapters.AdapterProduct;
import iteso.mx.proyectoexamen.beans.ItemProduct;
import iteso.mx.proyectoexamen.database.DataBaseHandler;
import iteso.mx.proyectoexamen.database.ItemProductControl;

public class FragmentTechnology extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemProduct> myDataSet;

    public FragmentTechnology() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_technology_recycler_view);
        recyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        ItemProductControl itemProductControl = new ItemProductControl();
        //technology es 1
        myDataSet = itemProductControl.getItemProductsByCategory(1
                , DataBaseHandler.getInstance(getActivity()));
        mAdapter = new AdapterProduct(getActivity(),myDataSet);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ItemProduct itemProduct = data.getParcelableExtra("ITEM");
        Iterator<ItemProduct> iterator = myDataSet.iterator();
        int position = 0;
        while (iterator.hasNext()) {
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()) {
                myDataSet.set(position, itemProduct);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        ItemProductControl itemProductControl = new ItemProductControl();
        ArrayList<ItemProduct> lista = itemProductControl.getItemProductsByCategory(1
                , DataBaseHandler.getInstance(getActivity()));

        //Comprobación fea para ver si esta lista tiene que actualizarse.
        //Actualización igualmente fea
        if( lista.size() > myDataSet.size()) {
            myDataSet.clear();
            myDataSet.addAll(lista);
            mAdapter.notifyDataSetChanged();
        }
    }
}

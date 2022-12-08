package com.uni.plovdiv.hapnitopni.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.plovdiv.hapnitopni.R;
import com.uni.plovdiv.hapnitopni.adapters.MenuAdapter;
import com.uni.plovdiv.hapnitopni.entities.Products;
import com.uni.plovdiv.hapnitopni.repository.MyDBHandler;

import java.util.ArrayList;

public class MenuFragment extends Fragment  {

    private ArrayList<Products> productsArrayList;
    private RecyclerView recycleview;
    MyDBHandler myDbHandler;
    Products product;

    private ArrayList<Products> products;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //productsInfo = myDbHandler.allProducts();
        return inflater.inflate(R.layout.menu_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        products = new ArrayList<>();
        myDbHandler = new MyDBHandler(getContext(),null,null,1);
        productsArrayList = myDbHandler.allProducts();
        dataInitialize();

        recycleview = view.findViewById(R.id.recycleView);
        recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleview.setHasFixedSize(true);

        MenuAdapter menuAdapter = new MenuAdapter(getContext(), products);
        recycleview.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {

        for (int i = 0; i<productsArrayList.size(); i++){

             product = new Products(productsArrayList.get(i).getImage(),productsArrayList.get(i).getName(),
                                   productsArrayList.get(i).getDescription()
                                   ,productsArrayList.get(i).getPrice());

            products.add(product);
        }

    }
}
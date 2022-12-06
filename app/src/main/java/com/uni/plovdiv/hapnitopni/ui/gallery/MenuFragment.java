package com.uni.plovdiv.hapnitopni.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.uni.plovdiv.hapnitopni.R;
import com.uni.plovdiv.hapnitopni.adapters.productsAdapter;

import java.util.ArrayList;

public class MenuFragment extends Fragment  {
    ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.menu_fragment, container, false);
        listView = (ListView) root.findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<100;i++)
            list.add("Item "+i);

        productsAdapter listAdapter = new productsAdapter(list);
        listView.setAdapter(listAdapter);

       return root;
    }




}
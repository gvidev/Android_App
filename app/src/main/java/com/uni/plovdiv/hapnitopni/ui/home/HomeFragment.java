package com.uni.plovdiv.hapnitopni.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.plovdiv.hapnitopni.R;
import com.uni.plovdiv.hapnitopni.activities.LoginActivity;
import com.uni.plovdiv.hapnitopni.activities.RegisterActivity;
import com.uni.plovdiv.hapnitopni.databinding.FragmentHomeBinding;
import com.uni.plovdiv.hapnitopni.repository.MyDBHandler;
import com.uni.plovdiv.hapnitopni.ui.gallery.MenuFragment;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }



}
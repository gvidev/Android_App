package com.uni.plovdiv.hapnitopni.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.uni.plovdiv.hapnitopni.R;


public class HomeFragment extends Fragment {

    Button toMenuB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        toMenuB = root.findViewById(R.id.buttonToMenu);



        toMenuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //i was stuck on this button about from the beginning,
                //but finally i find the solution in documentations
                //https://developer.android.com/guide/navigation/navigation-navigate#id
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_menu);
            }
        });


        return root;
    }
}
package com.uni.plovdiv.hapnitopni.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.plovdiv.hapnitopni.R;
import com.uni.plovdiv.hapnitopni.entities.Products;

import java.util.ArrayList;

public class productsAdapter extends RecyclerView.Adapter<productsAdapter.viewHolder>{

    ArrayList<Products> products;
    Context context;

    public productsAdapter(ArrayList<Products> productsArrayList, Context context) {
        this.products = productsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_fragment, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final Products model = products.get(position);
        holder.foodImageIV.setImageResource(model.getImage());
        holder.nameTV.setText(model.getName());
        holder.descriptionTV.setText(model.getDescription());
        holder.priceTV.setText(model.getPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView foodImageIV;
        TextView nameTV;
        TextView descriptionTV;
        TextView priceTV;
       // Button buyB;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            foodImageIV = itemView.findViewById(R.id.product_image);
            nameTV = itemView.findViewById(R.id.product_name);
            descriptionTV = itemView.findViewById(R.id.product_description);
            priceTV = itemView.findViewById(R.id.product_price);
            //buyB = itemView.findViewById(R.id.product_button);

        }


    }

}

package com.mbytessolution.cocktail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    private Context mContext;
    private ArrayList<Cocktails> cocktailsArrayList;

    public CocktailAdapter(Context mContext, ArrayList<Cocktails> cocktailsArrayList) {
        this.mContext = mContext;
        this.cocktailsArrayList = cocktailsArrayList;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cocktail, parent, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {

        final Cocktails cocktails = cocktailsArrayList.get(position);

        holder.name.setText(cocktails.getName());
        Picasso.get().load(cocktails.getImage()).placeholder(R.drawable.placeholder_loading).error(R.drawable.placeholder_loading).into(holder.imageView);

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("name", cocktails.getName());
                intent.putExtra("image", cocktails.getImage());
                intent.putExtra("instructions", cocktails.getInstructions());
                intent.putExtra("Glass", cocktails.getGlass());
                mContext.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("name", cocktails.getName());
                intent.putExtra("image", cocktails.getImage());
                intent.putExtra("instructions", cocktails.getInstructions());
                intent.putExtra("Glass", cocktails.getGlass());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cocktailsArrayList.size();
    }

    public class CocktailViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name;
        private Button details;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView1);
            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.details);
        }
    }

}

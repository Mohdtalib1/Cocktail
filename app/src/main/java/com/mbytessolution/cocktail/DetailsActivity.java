package com.mbytessolution.cocktail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private ImageView drinkImage;
    private TextView name, glass, instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        drinkImage = (ImageView) findViewById(R.id.drinkImage);
        name = (TextView) findViewById(R.id.name);
        glass = (TextView) findViewById(R.id.glass);
        instructions = (TextView) findViewById(R.id.instructions);

        String drinkName = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String glassName = getIntent().getStringExtra("Glass");
        String instructions1 = getIntent().getStringExtra("instructions");

        Picasso.get().load(image).placeholder(R.drawable.placeholder_loading).error(R.drawable.placeholder_loading).into(drinkImage);

        name.setText(drinkName);
        glass.setText("Serve : "+glassName);
        instructions.setText(instructions1);

    }
}
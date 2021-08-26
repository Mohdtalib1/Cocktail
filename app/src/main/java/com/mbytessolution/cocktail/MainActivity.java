package com.mbytessolution.cocktail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView cocktailRecyclerView;
    private ArrayList<Cocktails> cocktailsArrayList;
    private CocktailAdapter cocktailAdapter;
    private ProgressDialog progressDialog;
    private EditText category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cocktailRecyclerView = (RecyclerView) findViewById(R.id.cocktail_recycler_view);
        cocktailsArrayList = new ArrayList<>();

        category = (EditText) findViewById(R.id.category);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        cocktailRecyclerView.setLayoutManager(layoutManager);
        cocktailRecyclerView.setNestedScrollingEnabled(false);
        cocktailRecyclerView.setItemAnimator(new DefaultItemAnimator());
        cocktailRecyclerView.setHasFixedSize(true);

        cocktailAdapter = new CocktailAdapter(this, cocktailsArrayList);
        cocktailRecyclerView.setAdapter(cocktailAdapter);

        getAllCocktails();

        category.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               if (editable.toString().length() >=3) {
                   getSearchedData(editable.toString());
               }
            }
        });

    }

    private void getAllCocktails() {

        cocktailsArrayList.clear();

        String apiKey = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                , apiKey, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("drinks");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                            String drinkName = jsonObject.getString("strDrink");
                            String instructions = jsonObject.getString("strInstructions");
                            String image = jsonObject.getString("strDrinkThumb");
                            String strGlass = jsonObject.getString("strGlass");

                            Cocktails cocktails = new Cocktails(image, drinkName, instructions, strGlass);

                            cocktailsArrayList.add(cocktails);


                    }

                    cocktailAdapter.notifyDataSetChanged();


                    progressDialog.dismiss();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse response = error.networkResponse;

                if (error == null || error.networkResponse == null) {
                    return;
                }

                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;

            }
        };

        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjectRequest.setRetryPolicy(policy);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

    private void getSearchedData(final String category) {


        cocktailsArrayList.clear();

        progressDialog.show();

        String apiKey = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                , apiKey, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                        JSONArray jsonArray = response.getJSONArray("drinks");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (jsonObject.getString("strCategory").toLowerCase().contains(category.toLowerCase())) {

                                String drinkName = jsonObject.getString("strDrink");
                                String instructions = jsonObject.getString("strInstructions");
                                String image = jsonObject.getString("strDrinkThumb");
                                String strGlass = jsonObject.getString("strGlass");

                                Cocktails cocktails = new Cocktails(image, drinkName, instructions, strGlass);

                                cocktailsArrayList.add(cocktails);
                            }

                        }

                        cocktailAdapter.notifyDataSetChanged();


                    progressDialog.dismiss();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse response = error.networkResponse;

                if (error == null || error.networkResponse == null) {
                    return;
                }

                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;

            }
        };

        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjectRequest.setRetryPolicy(policy);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

    }
}
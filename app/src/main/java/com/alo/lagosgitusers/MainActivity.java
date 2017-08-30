package com.alo.lagosgitusers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private static String url = "https://api.github.com/search/users?q=language:java+location:lagos&per_page=225&";
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private Button retryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retryButton = (Button) findViewById(R.id.retry);


        listItems = new ArrayList<>();
        loadRecyclerViewData();


    }

    private void loadRecyclerViewData(){
        //hides the retryButton
        retryButton.setVisibility(View.GONE);

        //progress dialog
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();



        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String s) {
                        pDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(s);

                            JSONArray array = jsonObject.getJSONArray("items");

                            for (int i = 0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        o.getString("login"),
                                        o.getString("html_url"),
                                        o.getString("avatar_url")
                                );
                                listItems.add(item);
                            }

                            adapter = new MyAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // if it fails to get information
                        //make a toast, show retryButton
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Unable to retrieve information ", Toast.LENGTH_SHORT)
                                .show();
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadRecyclerViewData();
                            }
                        });

                    }
                });

        //making the request call using volley

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}

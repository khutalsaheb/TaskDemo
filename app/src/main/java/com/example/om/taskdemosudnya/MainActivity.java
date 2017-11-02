package com.example.om.taskdemosudnya;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    Button BtnAsc,Btndsc;

   String url = "https://raw.githubusercontent.com/mobilesiri/Android-Custom-Listview-Using-Volley/master/richman.json";
    RecyclerView recyclerView;
    List<Adaptelass> AdaptelasssList = new ArrayList<Adaptelass>();
    MyRecyclerAdapter adapter;
    Adaptelass Adaptela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyRecyclerAdapter(this, AdaptelasssList);
        BtnAsc=(Button)findViewById(R.id.asc);
        Btndsc=(Button)findViewById(R.id.dsc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        recyclerView.setAdapter(adapter);

        queue = AppController.getInstance(this).getRequestQueue();

        JsonArrayRequest newsReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        Adaptela = new Adaptelass(obj.getString("name"),
                                obj.getString("image"));

                        AdaptelasssList.add(Adaptela);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {

                        adapter.notifyItemChanged(i);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        //Adding JsonArrayRequest to Request Queue
        queue.add(newsReq);
        BtnAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.sort(AdaptelasssList, new Comparator<Adaptelass>() {
                    @Override
                    public int compare(Adaptelass s1, Adaptelass s2) {
                        return s2.getimgName().compareTo(s1.getimgName());

                    }
                });

            }
        });
        Btndsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.reverse(AdaptelasssList);

            }
        });
    }


}
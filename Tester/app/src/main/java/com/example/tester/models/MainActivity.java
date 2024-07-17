package com.example.tester.models;

import com.example.tester.R;
import com.example.tester.adapters.UserListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/* fetch data from remote API (in this case, "https://reqres.in/api/users?page=1")
using Volley library, parse the JSON response, and display the data in a RecyclerView */
public class MainActivity extends AppCompatActivity {

    // declare 'ProgressBar' instance variable
    ProgressBar progressBar;


    // initialize api url
    String url = "https://reqres.in/api/users?page=1";

    // initialize JSON request object
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hold reference to recyclerview in 'activity_main.xml'
        RecyclerView recyclerView = findViewById(R.id.list);

        // reference 'progressBar' by id in ' activity_main.xml' layout
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        // initialize Volley request 'queue' object and pass the context
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // define JSON 'request' object to use 'GET' method to fetch data from the api 'url' via 'response' object
        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                // initialize 'UserBean' model class object as a 'list' array
                UserBean userBean = null;
                ArrayList<UserBean> list = new ArrayList<>();

                try {

                    // initialize JSON 'response' object to get JSON array mapped by name 'data'
                    JSONArray userArray = response.getJSONArray("data");

                    // for every item in the 'userArray' list
                    for (int i = 0; i < userArray.length(); i++) {

                        // initialize 'UserBean' model class
                        userBean = new UserBean();

                        // initialize JSON object
                        JSONObject object = (JSONObject) userArray.get(i);

                        // set the 'id', 'first_name', 'last_name', 'avatar', 'email' from JSON object
                        // to corresponding UserBean model class object properties('id', 'name', 'image', 'email')
                        // using the UserBean's class setter methods(for eg, setName or setImage)
                        userBean.setId(object.getInt("id"));
                        userBean.setName(object.getString("first_name") + " " + object.getString("last_name"));
                        userBean.setImage(object.getString("avatar"));
                        userBean.setEmail(object.getString("email"));

                        // add 4 properties from 'userBean' object to 'list' array
                        list.add(userBean);

                    }// end FOR

                    // initialize JSON support 'response' object
                    JSONObject supportObject = response.getJSONObject("support");

                    String url = supportObject.getString("url");
                    String text = supportObject.getString("text");

                    // initialize 'UserListAdapter' adapter object & pass the now populated 'list' array(with 4 elements) to it as argument
                    UserListAdapter adapter = new UserListAdapter(list);

                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {

                    progressBar.setVisibility(View.GONE);
                    throw new RuntimeException(e);

                }// end CATCH block


            }// end 'onResponse' method
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // show toast with error from 'error' object converted to a string
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }// end 'onErrorResponse' callback method

        });// end 'JsonObjectRequest'

        // add the 'request' object to the 'Volley' RequestQueue 'queue'
        queue.add(request);

    }// end 'onCreate' method

}// end 'MainActivity' class
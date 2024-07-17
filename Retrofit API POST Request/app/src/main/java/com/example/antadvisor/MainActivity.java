package com.example.antadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antadvisor.interfaces.APIInterface;
import com.example.antadvisor.models.RequestChild;
import com.example.antadvisor.models.RequestModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// initialize Retrofit for API communication, send POST request with data, & handle response & failure with toast messages
public class MainActivity extends AppCompatActivity {

    // textView to show the result of the API call
    TextView myTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize TextView in 'activity_main.xml'
        myTxtView = findViewById(R.id.text_view_result);

        // initialize Retrofit for API communication
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.restful-api.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create APIInterface instance
        APIInterface apiInterface = retrofit.create(APIInterface.class);

        // initialize a 'RequestModel' object and set its data later
        RequestModel requestModel =  new RequestModel();

        // initialize a new 'RequestChild' object
        RequestChild requestChild = new RequestChild();

        // set year of the request to 2024
        requestChild.setYear(2024);

        // set CPU model of the request to "Intel Core"
        requestChild.setCPUModel("Intel Core");
        requestChild.setPrice(3000d);
        requestChild.setHardDiskSize("2 TB");

        requestModel.setName("Apple Mac book pro");

        // set the data of the request to the initialized 'RequestChild' object
        requestModel.setData(requestChild);

        // make a POST request to get data using 'calls' object
        Call<JsonObject> calls = apiInterface.getList(requestModel);

        // queue the request and define 'onResponse' callback method
        calls.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {

                    // extract relevant data from 'response' JsonObject
                    String responseData = response.body().toString();

                    // set the extracted data to the TextView
                    myTxtView.setText(responseData);

                } else {

                    // handle unsuccessful response
                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();

                }// end ELSE

            }// end 'onResponse' callback method

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

                // handle failure
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }// end 'onFailure' callback method

        });// end 'enqueue' method

    }// end 'onCreate' method

}// end 'MainActivity' class

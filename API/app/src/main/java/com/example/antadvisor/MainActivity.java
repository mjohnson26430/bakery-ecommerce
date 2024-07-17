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


public class MainActivity extends AppCompatActivity {

    TextView myTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myTxtView = findViewById(R.id.text_view_result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.restful-api.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APIInterface apiInterface = retrofit.create(APIInterface.class);

        RequestModel requestModel =  new RequestModel();

        RequestChild requestChild = new RequestChild();


        requestChild.setYear(2024);

        requestChild.setCPUModel("Intel Core");
        requestChild.setPrice(3000d);
        requestChild.setHardDiskSize("2 TB");

        requestModel.setName("Apple Mac book pro");


        requestModel.setData(requestChild);

        Call<JsonObject> calls = apiInterface.getList(requestModel);


        calls.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {


                    String responseData = response.body().toString();


                    myTxtView.setText(responseData);

                } else {


                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {


                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }

        });

    }

}

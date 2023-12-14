package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String api = "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String key = "04373417429e8e1c6a6b3fd4ad5970ce";
    EditText cityName;
 //   MaterialButton getWeather;
    TextView temptv , humidity , windtv, tv;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.et);
       // getWeather= findViewById(R.id.getW);
        temptv = findViewById(R.id.temp);
        humidity=findViewById(R.id.humidity);
        windtv= findViewById(R.id.wind);
       tv = findViewById(R.id.tv);
    }

    public  void getWeather(View v){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api myapi = retrofit.create(Api.class);
        Call<Example> myEx = myapi.Weather(cityName.getText().toString().trim(),key);
       myEx.enqueue(new Callback<Example>() {
           @Override
           public void onResponse(Call<Example> call, Response<Example> response) {
               if (!response.isSuccessful()) {
                   Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
               } else if (response.code() == 404) {
                   Toast.makeText(MainActivity.this, "please enter valid city name", Toast.LENGTH_SHORT).show();
               }
               Example mydata = response.body();
               Main main = mydata.getMain();
               double temp = main.getTemp();
               Integer temperature = (int) (temp - 273.15);

               temptv.setText((temperature) + "°C");
               int humi = main.getHumidity();
               humidity.setText((humi) + "%");

               Wind wind = mydata.getWind();
               double speed = wind.getSpeed();
               windtv.setText((speed) + "km/h");

           }

           @Override
           public void onFailure(Call<Example> call, Throwable t) {
               Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
           }
       });






    }
}
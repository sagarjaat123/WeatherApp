package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Example {

@SerializedName("main")
    Main main;

public Main getMain(){
    return main;
}
public void setMain(Main main){
this.main=main;
}

@SerializedName("wind")
    Wind wind;

public Wind getWind(){
    return wind;

}
public void setWind(Wind wind){
    this.wind= wind;
}


}


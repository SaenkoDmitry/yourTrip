package ru.yourtrip.repo.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;

public class GoogleDataLoader {
    private static GoogleDataLoader instance;
    private static String apiKey = "AIzaSyAkbxYusNvVljEYDc15nKTfdmPCFRDQyyM";
    private static String baseUrl = "https://maps.googleapis.com/maps/api/place/details/json?";

    @Autowired
    private static AmazonS3Client amazonS3Client;

    private GoogleDataLoader() { }

//    public synchronized static GoogleDataLoader getInstance() {
//        if (instance == null) {
//            instance = new GoogleDataLoader();
//        }
//        return instance;
//    }
    private static String addParam(String url, String paramName, String paramValue) {
        return url + "&" + paramName + "=" + paramValue;
    }

    // connect to GeoApi and get coordinates place b
    public static String getCoords(String showplaceName) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        GeocodingResult[] results = new GeocodingResult[0];

        try {
            results = GeocodingApi.geocode(context, showplaceName).await();
        } catch (ApiException | InterruptedException  | IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(results[0].geometry.location.lat + ":" + results[0].geometry.location.lng);
    }

    public static Time getSpentTime(String showplaceNameFrom, String showplaceNameTo) {
        return new Time(System.currentTimeMillis());
    }

    public static Integer getDistance(String showplaceNameFrom, String showplaceNameTo) {
        return new Integer(1);
    }

    public static void saveToGDrive() {
        File fileMetadata = new File("");
    }


    public static void main(String[] args) {
//        System.out.println(getCoords("Red Square"));
//        System.out.println(getCoords("Moscow"));
    }
}
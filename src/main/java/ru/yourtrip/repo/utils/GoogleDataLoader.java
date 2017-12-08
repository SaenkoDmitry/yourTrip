package ru.yourtrip.repo.utils;

import java.sql.Time;

public class GoogleDataLoader {
    private static GoogleDataLoader instance;

    private GoogleDataLoader() { }

//    public synchronized static GoogleDataLoader getInstance() {
//        if (instance == null) {
//            instance = new GoogleDataLoader();
//        }
//        return instance;
//    }

    public static String getCoords(String showplaceName) {
        return "";
    }

    public static Time getSpentTime(String showplaceNameFrom, String showplaceNameTo) {
        return new Time(System.currentTimeMillis());
    }

    public static Integer getDistance(String showplaceNameFrom, String showplaceNameTo) {
        return new Integer(1);
    }
}
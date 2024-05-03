package com.simpleapp.movieinfo.utils.cache;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.lang.reflect.Type;

public class CacheManager {
    private static final String PREF_NAME = "MyCachePrefs";
    private static final Gson gson = new Gson();

    public static <T> void saveToCache(Context context, String key, T data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(data);
        editor.putString(key, json);
        editor.apply();
    }

    public static <T> T getFromCache(Context context, String key, Class<T> clazz) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(key, null);
        if (json != null) {
            return gson.fromJson(json, clazz);
        } else {
            return null;
        }
    }

    public static <T> T getFromCache(Context context, String key, Type type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(key, null);
        if (json != null) {
            return gson.fromJson(json, type);
        } else {
            return null;
        }
    }

    public static <T> void saveToCache(Context context, String key, T data, Type type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(data, type);
        editor.putString(key, json);
        editor.apply();
    }

    public static <T> boolean updateCache(Context context, String key, T newData) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(newData);
        editor.putString(key, json);
        boolean success = editor.commit();
        return success;
    }

    public static <T> void saveToCacheDir(Context context, String fileName, T data) {
        try {
            String json = gson.toJson(data);
            java.io.FileWriter writer = new java.io.FileWriter(new java.io.File(context.getCacheDir(), fileName));
            writer.write(json);
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getFromCacheDir(Context context, String fileName, Type type) {
        try {
            java.io.FileReader reader = new java.io.FileReader(new java.io.File(context.getCacheDir(), fileName));
            return gson.fromJson(reader, type);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

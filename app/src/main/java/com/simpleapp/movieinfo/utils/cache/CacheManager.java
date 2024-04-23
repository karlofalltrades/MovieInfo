package com.simpleapp.movieinfo.utils.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CacheManager<T> {
    private static final String PREFS_NAME = "MyPrefs";
    private Context context;

    public CacheManager(Context context) {
        this.context = context;
    }

    public void saveToDiskCache(String key, T data) {
        File cacheFile = new File(context.getCacheDir(), key);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(cacheFile);
            fos.write(serialize(data).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveToSharedPreferences(String key, T data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, serialize(data));
        editor.apply();
    }

    public T retrieveFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String serializedData = sharedPreferences.getString(key, null);
        return deserialize(serializedData);
    }

    public T retrieveFromDiskCache(String key) {
        File cacheFile = new File(context.getCacheDir(), key);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(cacheFile);
            byte[] buffer = new byte[(int) cacheFile.length()];
            fis.read(buffer);
            return deserialize(new String(buffer));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String serialize(T data) {
        return new Gson().toJson(data);
    }

    private T deserialize(String serializedData) {
        return new Gson().fromJson(serializedData, new TypeToken<T>(){}.getType());
    }
}

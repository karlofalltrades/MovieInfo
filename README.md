# MovieInfo
https://github.com/barissaglam/Movie-App

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class CacheManager<T> {
    private static final String PREFS_NAME = "MyPrefs";
    private Context context;

    public CacheManager(Context context) {
        this.context = context;
    }

    // Save data to disk cache
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

    // Save data to SharedPreferences
    public void saveToSharedPreferences(String key, T data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, serialize(data));
        editor.apply();
    }

    // Retrieve data from SharedPreferences
    public T retrieveFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String serializedData = sharedPreferences.getString(key, null);
        return deserialize(serializedData);
    }

    // Retrieve data from disk cache
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

    // Additional methods for clearing cache, etc. can be added here

    // Helper method to serialize data
    private String serialize(T data) {
        // Implement serialization logic here
        // Example using Gson:
        // return new Gson().toJson(data);
        return String.valueOf(data); // Placeholder implementation
    }

    // Helper method to deserialize data
    private T deserialize(String serializedData) {
        // Implement deserialization logic here
        // Example using Gson:
        // return new Gson().fromJson(serializedData, new TypeToken<T>(){}.getType());
        return (T) serializedData; // Placeholder implementation
    }
}

package naukri.utils;

import java.util.HashMap;
import java.util.Map;

public class ContextDataStore {
    private static final Map<String, Object> dataStore = new HashMap<>();

    // Store data in the map
    public static void setData(String key, Object value) {
        dataStore.put(key, value);
    }

    // Retrieve data by key
    public static Object getData(String key) {
        return dataStore.get(key);
    }

    // Check if a key exists
    public static boolean containsKey(String key) {
        return dataStore.containsKey(key);
    }

    // Remove a key-value pair
    public static void removeData(String key) {
        dataStore.remove(key);
    }

    // Clear all stored data
    public static void clearData() {
        dataStore.clear();
    }
}
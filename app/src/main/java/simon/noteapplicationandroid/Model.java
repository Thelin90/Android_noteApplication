package simon.noteapplicationandroid;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The engine of the application
 * @author Simon Thelin
 * @version 1.0
 * 2017-09-04.
 *
 */
public class Model {
    private ArrayList<String> tasks;
    private HashMap<String, String> map;

    /**
     * Constructor
     */
    public Model() {
        tasks = new ArrayList<>();
        map = new HashMap();
    }

    public void push(String value) {
        tasks.add(value);
    }

    public ArrayList<String> getArrayList() {
        return this.tasks;
    }

    /**
     * Will push data to the map structure 2017-09-04 10:00 -> Hello hello
     * @param date
     * @param note
     */
    public void push(String date, String note) {
        this.map.put(date, note);
    }

    /**
     * Will return the map structure
     * @return
     */
    public HashMap<String, String> getSet() {
        return this.map;
    }

    /**
     * Will save the map objects to a json string to save it with sharedpreferences, this is because
     * I wanted to avoid making the hashmap static, and this is good when going in and out from different
     * activitys and restarting the app. A good feature would be that it remembers the notes, that is how
     * I thought.
     * @param key
     * @param activity
     */
    public void saveHashMap(String key, Activity activity) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        Map<String, Object> map0 = getMap(key, activity);
        Map<String, String> map1 = getSet();
        Map<String, Object> map3 = new HashMap<>();
        if(map0 == null) {
            map3.putAll(map1);
        } else {
            map3.putAll(map0);
            map3.putAll(map1);
        }
        String jsonString = new Gson().toJson(map3);
        editor.putString(key, jsonString);
        editor.apply();
    }

    /**
     * Get the saved map object in sharedpreferences
     * @param key
     * @param activity
     * @return
     */
    public Map<String, Object> getMap(String key, Activity activity) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String json = prefs.getString(key, "");

        Map<String, Object> retMap = new Gson().fromJson(
                json, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return retMap;
    }
}

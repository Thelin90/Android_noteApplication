package simon.noteapplicationandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * ShowNotes class is the activity where the actual notes are displayed.
 *
 * @author Simon Thelin
 * @version 1.0
 * 2017-09-04.
 */
public class ShowNotes extends AppCompatActivity {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        new Fetch();
    }

    /**
     * Inner class Fetch, to fetch data from Model
     */
    public class Fetch extends Model {
        ArrayAdapter<String> adapter;
        private ListView listview;

        /**
         * Constructor
         */
        public Fetch() {
            listview = (ListView)findViewById(R.id.list);
            fetchNotes();
        }

        /**
         * Will get the data from the map data structure, not the best solution, but it will work
         * for now. Would rather have implemented a better solution where the map where better integrated
         * with the ArrayAdapter, but not that much time.
         */
        public void fetchNotes() {

            adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, getArrayList());
            listview.setAdapter(adapter);

            Map<String, Object> map = getMap("map", ShowNotes.this);

            if(map != null) {
                Iterator myVeryOwnIterator = map.keySet().iterator();
                while (myVeryOwnIterator.hasNext()) {
                    String key = (String) myVeryOwnIterator.next();
                    String value = (String) map.get(key);
                    push(value + "\n" + key);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "There is no notes to print, maybe you should make some notes first!", Toast.LENGTH_LONG).show();
            }
        }
    }
}

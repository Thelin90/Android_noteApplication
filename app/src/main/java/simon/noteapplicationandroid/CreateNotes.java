package simon.noteapplicationandroid;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * CreatesNotes will make sure that the user can add new notes to the application.
 *
 * @author Simon Thelin
 * @version 1.0
 * 2017-09-04.
 */
public class CreateNotes extends AppCompatActivity {

    private Button add_note;
    private String note = "";
    private EditText editText;
    private Model m = new Model();

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);
        action();
    }

    /**
     * Will set the given note
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Will return the note
     * @return
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Will reset the textfield
     */
    public void resetTextField() {
        editText.getText().clear();
        Toast.makeText(getApplicationContext(), "Note added, please enter a new one or go back to view notes", Toast.LENGTH_LONG).show();
    }

    /**
     * For something, not useful
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * I always perform saving of data when onPause is called.
     */
    @Override
    public void onPause() {
        super.onPause();
        m.saveHashMap("map", CreateNotes.this);
    }

    /**
     * Get the note from the textfield and get the local time in UTC. The date will be
     * the key inside the map.
     */
    public void action() {
        this.add_note = (Button) findViewById(R.id.addNote);

        this.add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = (EditText) findViewById(R.id.notetext);
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
                f.setTimeZone(TimeZone.getTimeZone("Sweden/Stockholm"));
                String key = f.format(new Date());
                if (editText.getText().toString().length() != 0) {
                    setNote(editText.getText().toString());
                    m.push(key, getNote());
                    resetTextField();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter something that can be addded as a note!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

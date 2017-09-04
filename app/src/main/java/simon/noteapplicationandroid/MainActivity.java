package simon.noteapplicationandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *
 * MainActivity will provide the "main-page" activity for the user.
 *
 * @author Simon Thelin
 * @version 1.0
 * 2017-09-04.
 */
public class MainActivity extends AppCompatActivity {

    private Button create_note, view_notes;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.create_note = (Button)findViewById(R.id.button1);
        this.view_notes = (Button)findViewById(R.id.button2);
        action();
    }

    /**
     * Method action will start the chosen acitvity by the user input.
     */
    public void action() {
        this.create_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, CreateNotes.class);
                startActivity(add);
            }
        });

        this.view_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, ShowNotes.class);
                startActivity(add);
            }
        });
    }
}
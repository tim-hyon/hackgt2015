package lc.mark.ties;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String FIREBASE_URL = "https://ties.firebaseio.com";
    public static Firebase firebaseHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        firebaseHandle = new Firebase(FIREBASE_URL);
        setContentView(R.layout.activity_main);
        final Context c = this;

        firebaseHandle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                ArrayList<Map<String, Object>> contacts = (ArrayList) data.get("Contacts");

                ListView lv = (ListView) findViewById(R.id.contacts_list);
                List<String> contact = new ArrayList<String>();


               for (Map<String, Object> m : contacts)
                   contact.add((String) m.get("name"));

                String[] ss = contact.toArray(new String[contact.size()]);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, ss);

                ListView listView = (ListView) findViewById(R.id.contacts_list);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, AddContactActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

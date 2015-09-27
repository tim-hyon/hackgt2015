package lc.mark.ties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddContactActivity extends AppCompatActivity {

    public static final long TIMEOUT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void registerContact(View view) {
        String fullName = view2text(R.id.full_name);
        String email = view2text(R.id.email);
        uploadToFirebase(fullName, email);
        finish();
    }

    private void uploadToFirebase(final String fullName, final String email) {

        MainActivity.firebaseHandle.child("Counter").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> uploadMap = new HashMap<String, Object>();
                Long x = (Long) snapshot.getValue();
                long expDate = new Date().getTime()/1000 + TIMEOUT;

                uploadMap.put("name", fullName);
                uploadMap.put("email", email);
                uploadMap.put("expired", false);
                uploadMap.put("timelimit", TIMEOUT);
                uploadMap.put("expDate", expDate);

                MainActivity.firebaseHandle.child("Contacts").child(x.toString()).setValue(uploadMap);
                MainActivity.firebaseHandle.child("Counter").setValue(x+1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });



    }

    private String view2text(int id) {
        return ((EditText) findViewById(id)).getText().toString();
    }
}

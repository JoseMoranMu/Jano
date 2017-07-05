package android.app.jano;

import android.app.jano.model.DBConnect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button bName, bSelect;
    EditText editTextName;
    View.OnClickListener listener;
    DBConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        onPrepareListener();
        bName.setOnClickListener(listener);
        bSelect.setOnClickListener(listener);
    }

    private void initComponents(){
        bName = (Button) findViewById(R.id.button2);
        bSelect = (Button) findViewById(R.id.button3);
        editTextName = (EditText) findViewById(R.id.editText);
        dbConnect = new DBConnect(getApplicationContext(), "prueba", null, 1);
    }

    private void onPrepareListener(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button2:
                        insertName();
                        break;
                    case R.id.button3:
                        dbConnect.showData();
                        break;
                }
            }
        };
    }

    private void insertName(){
        dbConnect.addName(editTextName.getText().toString());
    }
}

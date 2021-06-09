package sg.edu.rp.c346.id20022678.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etItem;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvItems;
    ArrayList<String> alItems;
    ArrayAdapter<String> aaItems;
    Spinner spnReAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.editItems);
        btnClear = findViewById(R.id.buttonClearItem);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        lvItems = findViewById(R.id.listViewItem);
        spnReAd = findViewById(R.id.spinner);

        alItems = new ArrayList<>();

        aaItems = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alItems);
        lvItems.setAdapter(aaItems);

        spnReAd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etItem.setInputType(InputType.TYPE_CLASS_TEXT);
                        etItem.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etItem.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etItem.setHint("Type in the index of the task to removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                alItems.add(item);
                aaItems.notifyDataSetChanged();
                etItem.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etItem.getText().toString())){
                    Toast.makeText(MainActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                }
                else {
                    int pos = Integer.parseInt(etItem.getText().toString());
                    if (alItems.size() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    } else if (pos + 1 > alItems.size()) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        alItems.remove(pos);
                        aaItems.notifyDataSetChanged();
                        etItem.setText("");
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alItems.clear();
                aaItems.notifyDataSetChanged();
            }
        });

//        lvColour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, position, Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
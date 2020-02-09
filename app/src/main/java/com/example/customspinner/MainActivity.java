package com.example.customspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner fromList;
    Spinner toList;
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromList = (Spinner) findViewById(R.id.fromList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fruit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromList.setAdapter(adapter);

        toList = (Spinner) findViewById(R.id.toList);
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toList.setAdapter(dataAdapter);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
        Button remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(this);
    }
    public void onClick(View v) {

        String myData = fromList.getSelectedItem().toString();
        int position = dataAdapter.getPosition(myData);

        switch (v.getId()) {

            case R.id.add:

                if(position >= 0){
                    Toast.makeText(getBaseContext(), myData + " already in Spinner", Toast.LENGTH_LONG).show();
                }
                else {
                    dataAdapter.add(myData);
                    dataAdapter.notifyDataSetChanged();
                    toList.setSelection(dataAdapter.getPosition(myData));
                }
                break;

            case R.id.remove:

                if(position >= 0){
                    dataAdapter.remove(myData);
                    dataAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getBaseContext(), myData + " not in Spinner", Toast.LENGTH_LONG).show();
                }
                break;
            // More buttons go here (if any) ...

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

package com.example.xuezhu.soccerfieldandtrainingplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Field;

public class MainActivity extends AppCompatActivity {

    private EditText fieldName, fieldAddr, fieldType, fieldTel;
    private Button submitButton;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        fieldName = (EditText) findViewById(R.id.fieldNameText);
        fieldAddr = (EditText) findViewById(R.id.fieldAddrText);
        fieldType = (EditText) findViewById(R.id.fieldTypeText);
        fieldTel = (EditText) findViewById(R.id.fieldTelText);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataToDB();
            }
        });
    }

    private void submitDataToDB() {
        Field field = new Field();
        String name = fieldName.getText().toString().trim();
        String addr = fieldAddr.getText().toString().trim();
        String type = fieldType.getText().toString().trim();
        String tel = fieldTel.getText().toString().trim();


        if (name.equals("") || addr.equals("") || tel.equals("") || type.equals(""))
            Toast.makeText(getApplicationContext(), "No empty fields allowed", Toast.LENGTH_LONG).show();
        else {
            field.setName(name);
            field.setAddr(addr);
            field.setTel(tel);
            field.setType(type);

            databaseHandler.addField(field);
            databaseHandler.close();

            // Clear the form
            fieldName.setText("");
            fieldAddr.setText("");
            fieldTel.setText("");
            fieldType.setText("");

            // Go to next screen
            startActivity(new Intent(MainActivity.this, DisplayFieldInfoActivity.class));
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

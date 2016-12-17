package com.example.xuezhu.soccerfieldandtrainingplan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Field;

public class FieldInfoDetailActivity extends AppCompatActivity {
    private TextView fieldName, fieldAddr, fieldTel, fieldType;
    private Button planButton;
    private int fieldId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_info_detail);

        fieldName = (TextView) findViewById(R.id.detsFieldName);
        fieldAddr = (TextView) findViewById(R.id.detsAddr);
        fieldTel = (TextView) findViewById(R.id.detsTel);
        fieldType = (TextView) findViewById(R.id.detsType);


        Field field = (Field) getIntent().getSerializableExtra("userObj");
        fieldName.setText(field.getName());
        fieldAddr.setText(field.getAddr());
        fieldTel.setText(field.getTel());
        fieldType.setText(field.getType());
        fieldId = field.getFieldId();
        planButton = (Button) findViewById(R.id.detPlanButton);
        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlan();
            }
        });

    }

    public void addPlan() {
        startActivity(new Intent(FieldInfoDetailActivity.this, NewPlanActivity.class));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display_field_info, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteItem ) {
            AlertDialog.Builder alter = new AlertDialog.Builder(FieldInfoDetailActivity.this);
            alter.setTitle("Delete?");
            alter.setMessage("Are you sure to delete the filed?");
            alter.setNegativeButton("No", null);
            alter.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                    databaseHandler.deleteField(fieldId);
                    Toast.makeText(getApplicationContext(), "Field deleted!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FieldInfoDetailActivity.this, DisplayFieldInfoActivity.class));
                    FieldInfoDetailActivity.this.finish();
                }

            });
            alter.show();
        }
        return super.onOptionsItemSelected(item);
    }
}

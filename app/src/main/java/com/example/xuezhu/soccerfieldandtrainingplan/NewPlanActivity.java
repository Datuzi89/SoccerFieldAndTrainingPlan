package com.example.xuezhu.soccerfieldandtrainingplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Plan;

public class NewPlanActivity extends AppCompatActivity {

    private EditText planTime, planDate;
    private TextView cancel, add, planField;

    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);

        databaseHandler = new DatabaseHandler(NewPlanActivity.this);
        planTime = (EditText) findViewById(R.id.planTime);
        planDate = (EditText) findViewById(R.id.planDate);
        cancel = (TextView) findViewById(R.id.cancelPlan);
        add = (TextView) findViewById(R.id.addPlan);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataToDB();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPlanActivity.this.finish();
            }
        });
    }

    private void submitDataToDB() {
        Plan plan = new Plan();
        String time = planTime.getText().toString().trim();
        String date = planTime.getText().toString().trim();


        if (time.equals("") || date.equals("") )
            Toast.makeText(getApplicationContext(), "No empty fields allowed", Toast.LENGTH_LONG).show();
        else {
            plan.setTime(time);
            plan.setDate(date);


            databaseHandler.addPlan(plan);
            databaseHandler.close();

            // Clear the form
            planTime.setText("");
            planDate.setText("");

            // Go to next screen
            startActivity(new Intent(NewPlanActivity.this, FieldInfoDetailActivity.class));
        }
    }
}

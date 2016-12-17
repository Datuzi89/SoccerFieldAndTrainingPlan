package com.example.xuezhu.soccerfieldandtrainingplan;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.CustonListviewAdapter;
import data.DatabaseHandler;
import model.Field;

public class DisplayFieldInfoActivity extends Activity {

    private DatabaseHandler databaseHandler;
    private ArrayList<Field> dbFields = new ArrayList<>();
    private CustonListviewAdapter fieldAdapter;
    private ListView listView;
    private Field myField;
    private TextView totalFields, totalPlans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_field_info);
        listView = (ListView) findViewById(R.id.list);
        totalFields = (TextView) findViewById(R.id.fieldNumText);
        totalPlans = (TextView) findViewById(R.id.planNumText);

        refreshData();
    }

    private void refreshData() {
        dbFields.clear();
        databaseHandler = new DatabaseHandler(getApplicationContext());
        ArrayList<Field> fieldsFromDB = databaseHandler.getFields();
        int totalfieldsValue = databaseHandler.getFieldNum();
        int totalplansValue = databaseHandler.getPlanNum();

        totalFields.setText("" + totalfieldsValue + " Fields");
        totalPlans.setText("" + totalplansValue + " Plans");

        for (int i = 0; i < fieldsFromDB.size(); i++) {
            String name = fieldsFromDB.get(i).getName();
            String addr = fieldsFromDB.get(i).getAddr();
            String tel = fieldsFromDB.get(i).getTel();
            String type = fieldsFromDB.get(i).getType();
            int fieldId = fieldsFromDB.get(i).getFieldId();

            Log.v("FieldId is ", String.valueOf(fieldId)); // For test

            myField = new Field();
            myField.setName(name);
            myField.setTel(tel);
            myField.setAddr(addr);
            myField.setType(type);
            myField.setFieldId(fieldId);

            dbFields.add(myField);
        }
        databaseHandler.close();

        fieldAdapter = new CustonListviewAdapter( R.layout.list_row, DisplayFieldInfoActivity.this, dbFields);
        listView.setAdapter(fieldAdapter);
        fieldAdapter.notifyDataSetChanged();

    }


}

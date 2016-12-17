package data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import model.Field;
import model.Plan;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final ArrayList<Field> fields = new ArrayList<>();
    private final ArrayList<Plan> plans = new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        String CREATE_TABLE_FIELD = "CREATE TABLE " + Constants.TABLE_FIELD +
                "(" + Constants.FIELD_ID + " INTEGER PRIMARY KEY, " +
                Constants.FIELD_NAME + " TEXT, " + Constants.FIELD_ADDR +
                " TEXT, " + Constants.FIELD_TEL + " TEXT, " + Constants.FIELD_TYPE +
                " TEXT);";

        String CREATE_TABLE_PLAN = "CREATE TABLE " + Constants.TABLE_PLAN +
                "(" + Constants.PLAN_ID + " INTEGER PRIMARY KEY, " + Constants.FIELD_NAME + " TEXT, "
                + Constants.PLAN_TIME + " TEXT, " + Constants.PLAN_DATE + " TEXT)" ;

        Log.v("Create tables","yes");
        db.execSQL(CREATE_TABLE_FIELD);
        db.execSQL(CREATE_TABLE_PLAN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_FIELD);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PLAN);

        // Create a new database
        onCreate(db);
    }

    // Get total number of soccer fields
    public int getFieldNum() {
        int total = 0;

        String query = "SELECT * FROM " + Constants.TABLE_FIELD;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        total = cursor.getCount();
        cursor.close();
        return total;
    }

    //Get total number of plans
    public int getPlanNum() {
        int total = 0;

        String query = "SELECT * FROM " + Constants.TABLE_PLAN;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        total = cursor.getCount();
        cursor.close();
        return total;
    }

    //Delete soccer fields
    public void deleteField(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        database.delete(Constants.TABLE_FIELD, Constants.FIELD_ID + " = ?",
                new String[]{String.valueOf(id)});

        database.delete(Constants.TABLE_PLAN, Constants.FIELD_ID + " = ?",
                new String[]{String.valueOf(id)});

        database.close();
    }

    //Add soccer field
    public void addField(Field field) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.FIELD_NAME, field.getName());
        values.put(Constants.FIELD_ADDR, field.getAddr());
        values.put(Constants.FIELD_TEL, field.getTel());
        values.put(Constants.FIELD_TYPE, field.getType());

        database.insert(Constants.TABLE_FIELD, null, values);
        Log.v("Add new field", "yes");
        database.close();
    }

    //Add plan
    public void addPlan(Plan plan) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.FIELD_NAME, plan.getFieldName());
        values.put(Constants.PLAN_TIME, plan.getTime());
        values.put(Constants.PLAN_DATE, plan.getDate());


        database.insert(Constants.TABLE_PLAN, null, values);
        Log.v("Add new plan", "yes");
        database.close();
    }

    //Get all fields
    public ArrayList<Field> getFields() {
        fields.clear();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Constants.TABLE_FIELD,
                new String[]{Constants.FIELD_ID, Constants.FIELD_NAME, Constants.FIELD_ADDR,
                Constants.FIELD_TEL, Constants.FIELD_TYPE},
                null, null, null, null, Constants.FIELD_NAME + " ASC");
        if (cursor.moveToFirst())
            do {
                Field field = new Field();
                field.setFieldId(cursor.getInt(cursor.getColumnIndex(Constants.FIELD_ID)));
                field.setName(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME)));
                field.setAddr(cursor.getString(cursor.getColumnIndex(Constants.FIELD_ADDR)));
                field.setTel(cursor.getString(cursor.getColumnIndex(Constants.FIELD_TEL)));
                field.setType(cursor.getString(cursor.getColumnIndex(Constants.FIELD_TYPE)));
                fields.add(field);

            } while (cursor.moveToNext());
        cursor.close();
        return fields;
    }

    // Get all plans
    public ArrayList<Plan> getPlans() {
        plans.clear();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Constants.TABLE_PLAN,
                new String[]{Constants.PLAN_ID, Constants.FIELD_NAME, Constants.PLAN_TIME, Constants.PLAN_DATE},
                null, null, null, null, Constants.PLAN_TIME + " DESC");
        if (cursor.moveToFirst())
            do {
                Plan plan = new Plan();
                plan.setFieldName(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME)));
                plan.setDate(cursor.getString(cursor.getColumnIndex(Constants.PLAN_DATE)));
                plan.setTime(cursor.getString(cursor.getColumnIndex(Constants.PLAN_TIME)));
                plans.add(plan);

            } while (cursor.moveToNext());
        cursor.close();
        return plans;
    }

    // Get plans of one field
    public ArrayList<Plan> getPlansOfAField(String fieldName) {
        plans.clear();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Constants.TABLE_PLAN, new String[]{Constants.PLAN_ID,
                Constants.PLAN_TIME, Constants.PLAN_DATE, Constants.FIELD_NAME}, Constants.FIELD_NAME + " = ?",
                new String[]{fieldName}, null, null, null, Constants.PLAN_DATE + " DESC");
        if (cursor.moveToFirst())
            do {
                Plan plan = new Plan();
                plan.setDate(cursor.getString(cursor.getColumnIndex(Constants.PLAN_DATE)));
                plan.setTime(cursor.getString(cursor.getColumnIndex(Constants.PLAN_TIME)));
                plan.setFieldName(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME)));
                plans.add(plan);

            } while (cursor.moveToNext());
        cursor.close();
        return plans;
    }
}

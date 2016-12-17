package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.xuezhu.soccerfieldandtrainingplan.FieldInfoDetailActivity;
import com.example.xuezhu.soccerfieldandtrainingplan.R;

import java.util.ArrayList;


import model.Field;

public class CustonListviewAdapter extends ArrayAdapter<Field>{

    private int layoutResource;
    private Activity activity;
    private ArrayList<Field> fields = new ArrayList<>();


    public CustonListviewAdapter( int layoutResource, Activity activity, ArrayList<Field> fields) {
        super(activity, layoutResource, fields);
        this.layoutResource = layoutResource;
        this.activity = activity;
        this.fields = fields;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fields.size();
    }

    @Override
    public Field getItem(int position) {
        return fields.get(position);
    }

    @Override
    public int getPosition(Field item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null || (row.getTag() == null)) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);
            holder = new ViewHolder();
            holder.fieldName = (TextView)row.findViewById(R.id.name);
            holder.fieldAddr = (TextView)row.findViewById(R.id.addr);
            holder.fieldType = (TextView)row.findViewById(R.id.type);
            row.setTag(holder);
        }
        else holder = (ViewHolder) row.getTag();

        holder.field = getItem(position);
        holder.fieldName.setText(holder.field.getName());
        holder.fieldAddr.setText(holder.field.getAddr());
        holder.fieldType.setText(holder.field.getType());
        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FieldInfoDetailActivity.class);

                // Passing field info to detail activity
                Bundle bundle = new Bundle();
                bundle.putSerializable("userObj", finalHolder.field);
                intent.putExtras(bundle);

                activity.startActivity(intent);
            }
        });
        return row;
    }

    public class ViewHolder {
        Field field;
        TextView fieldName;
        TextView fieldAddr;
        TextView fieldType;

    }
}

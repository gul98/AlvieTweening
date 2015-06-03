package com.example.gul.multiplecontactpicker;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by gul on 6/3/15.
 */
public class CustomArrayAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] name;
    private final String[] number;
    public CustomArrayAdapter(Activity context,String[] number, String[] name){
        super(context, R.layout.sample_listview,name);
        this.context=context;
        this.name=name;
        this.number=number;

    }
    @Override
    public View getView(int position , View view, ViewGroup parent){

        Log.d("CustomArrayAdapter", String.valueOf(position));
        LayoutInflater inflater= context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.sample_listview, null, true);
        TextView txtTitle=(TextView)rowView.findViewById(R.id.txtPresidentName);
        TextView txtDescription = (TextView)rowView.findViewById(R.id.txtDescription);
        txtTitle.setText(name[position]);
        txtDescription.setText(number[position]);
        CheckBox check=(CheckBox)rowView.findViewById(R.id.checkbox);

        return rowView;
    }
}

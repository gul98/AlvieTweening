package com.example.gul.multiplecontactpicker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    LinearLayout parentLayout;
    String[] name;
    String[] contactnum;
    EditText edit;
    int i=0;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ListView list = getListView();
        //list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ListView lstView = getListView();
        edit=(EditText)findViewById(R.id.editText);
        lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);

        String[] nameNumberArray = new String[cursor.getCount()];
        Log.d("rows", Integer.toString(cursor.getCount()));
        String[] ContactName= new String[cursor.getCount()];
        String[] ContactNumber=new String[cursor.getCount()];
        Log.d("phone", "here");

        while (cursor.moveToNext()) {

            String phoneNumber =

                    cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.i("Number + Name", phoneNumber + name);
            ContactNumber[i]=phoneNumber;
            ContactName[i]=name;
            i++;


           // / parentLayout.addView(cookMessageLayout("Hi", true));
        }
        name=new String[cursor.getCount()];
        contactnum=new String[cursor.getCount()];
        name=ContactName;
        contactnum=ContactNumber;
        String[] FinalString=new String[cursor.getCount()];
        for(int i=0 ; i<cursor.getCount(); i++){
            FinalString[i]=ContactName[i]+" : "+ContactNumber[i];
        }
        //CustomArrayAdapter adapter=new CustomArrayAdapter(this,ContactNumber,ContactName);
        //setListAdapter(adapter);
        adapter=new ArrayAdapter<String>(this, R.layout.mysimple,FinalString );
        setListAdapter(adapter);


    }



  @Override
   public void onListItemClick(ListView parent, View v , int position, long id){
       //Toast.makeText(this, "Number Pushed -> Name: " + name[position] + " Number: "+contactnum[position],Toast.LENGTH_SHORT ).show();

      CheckedTextView item = (CheckedTextView) v;
      Toast.makeText(this, contactnum[position] + " checked : " +
              !item.isChecked(), Toast.LENGTH_SHORT).show();
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

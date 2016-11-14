package com.example.dell.dailychores;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DisplayListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Chores_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        DatabaseHelper databaseHelper=DatabaseHelper.getDbInstance(getApplicationContext());

        Log.v("DisplayListActivity","in OnCreate1");

        recyclerView=(RecyclerView)findViewById(R.id.chores_recyclerView);

        Log.v("DisplayListActivity","in OnCreate2");

        adapter=new Chores_Adapter(getBaseContext(),databaseHelper.getChores());

        Log.v("DisplayListActivity","in OnCreate3");

        recyclerView.setAdapter(adapter);

        Log.v("DisplayListActivity","in OnCreate4");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Log.v("DisplayListActivity","in OnCreate5");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.tool_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.createMenu:
                createChore();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }}

    private void createChore() {
        Intent intent = new Intent(getBaseContext(), EnterChore.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

private void removeChore(){
    Intent intent = new Intent(getBaseContext(), RemoveChore.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}


}

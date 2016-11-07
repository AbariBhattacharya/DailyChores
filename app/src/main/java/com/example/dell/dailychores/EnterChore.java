package com.example.dell.dailychores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterChore extends AppCompatActivity {
    DatabaseHelper testDb;
    EditText mEditTitle;
    EditText mEditDetail;
    Button mAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_chore);
        testDb = new DatabaseHelper(this);

        mEditTitle = (EditText) findViewById(R.id.newTitleEditText);
        mEditDetail = (EditText) findViewById(R.id.newDetailEditText);
        mAddButton = (Button) findViewById(R.id.addButton);
        saveData();
    }
    public void saveData(){
        mAddButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        boolean isInserted=testDb.insertData(mEditTitle.getText().toString(), mEditDetail.getText().toString()
                        );
                        if(isInserted==true) {
                            //Log.d(TAG, "in save data function");
                            Toast.makeText(EnterChore.this, "Data Successfully Inserted", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(EnterChore.this,"Data not inserted",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}

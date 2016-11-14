package com.example.dell.dailychores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveChore extends AppCompatActivity {

    DatabaseHelper testDb;
    EditText mEditTitleDel;

    Button mRemoveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_chore);

        testDb = new DatabaseHelper(this);

        mEditTitleDel = (EditText) findViewById(R.id.newTitleEditText);

        mRemoveButton = (Button) findViewById(R.id.removeButton);
        removeData();
    }
    public void removeData(){
        mRemoveButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        int deleteData=testDb.deleteData(mEditTitleDel.getText().toString());
                        if(deleteData!=0) {
                            //Log.d(TAG, "in save data function");
                            Toast.makeText(RemoveChore.this, "Data Successfully Deleted", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(RemoveChore.this,"Data not inserted",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), DisplayListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                }
        );
    }

}

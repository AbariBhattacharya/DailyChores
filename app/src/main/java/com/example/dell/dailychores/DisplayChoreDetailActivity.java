package com.example.dell.dailychores;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class DisplayChoreDetailActivity extends AppCompatActivity {

    //public static final String CHORE_TITLE="title";
    //public static final String CHORE_DETAIL="detail";
    ViewPager mviewPager=null;
    List<Chores> mData;

    String curString=null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chore_detail);

       Toolbar toolbar=(Toolbar)findViewById(R.id.toolBar);

       setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        //Log.v("DisplayChore","UPbutton1");
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Log.v("DisplayChore","UPbutton2");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);




       /*Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {

            TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
            }
        else {
            NavUtils.navigateUpTo(this, upIntent);
            }

        final String title1=getIntent().getStringExtra("Title");*/
       // Log.v("msin",title1);
        //Log.v("msin",abc.toString());

        DatabaseHelper databaseHelper=DatabaseHelper.getDbInstance(getBaseContext());
        mData=databaseHelper.getChores();
        curString=getIntent().getStringExtra("Title");
        mviewPager=(ViewPager)findViewById(R.id.pager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        mviewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager){
            @Override
            public Fragment getItem(int position) {
                Chores chore=mData.get(position);
                Log.v("Displaychoredetail","title::"+chore.title);
                Fragment mfragment=DisplayChoreDetailFragment.getInstance(chore.title);
                return mfragment;
            }

            @Override
            public int getCount() {
                return mData.size();
            }
        });

        for(int i=0;i<mData.size();i++){
            if(mData.get(i).title.equals(curString)){
                mviewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home,menu);
        return true;
    }

    @Override
public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()) {
// Respond to the action bar's Up/Home button
case android.R.id.home:
NavUtils.navigateUpFromSameTask(this);
return true;
}
return super.onOptionsItemSelected(item);
}

  /*  public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //use onBackPressed() OR finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}



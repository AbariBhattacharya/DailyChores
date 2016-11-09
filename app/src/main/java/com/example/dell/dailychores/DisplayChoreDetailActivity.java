package com.example.dell.dailychores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class DisplayChoreDetailActivity extends FragmentActivity {

    public static final String CHORE_TITLE="title";
    public static final String CHORE_DETAIL="detail";
    ViewPager mviewPager=null;
    List<Chores> mData;

    String curString=null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chore_detail);

       // final String title1=getIntent().getStringExtra("Title");
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

}



package com.example.android.moviesapp.Activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.Adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {



    public Context context;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            toolbar=findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);
            viewPager=findViewById(R.id.pager);

            viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout=findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);


    }


}

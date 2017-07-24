package com.dawn.basefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 90449 on 2017/7/24.
 */

public class HomeFragmentActivity extends AppCompatActivity implements View.OnClickListener{
    Fragment fragment01;
    Fragment fragment02;
    Fragment fragment03;
    Fragment fragment04;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);
        fragment01 = new ContentFragment01();
        setFragment(R.id.fragment_content, fragment01);
        findViewById(R.id.tv_01).setOnClickListener(this);
        findViewById(R.id.tv_02).setOnClickListener(this);
        findViewById(R.id.tv_03).setOnClickListener(this);
        findViewById(R.id.tv_04).setOnClickListener(this);
    }
    private void setFragment(@IdRes int idRes, Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(idRes, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_01:
                if(fragment01 == null)
                    fragment01 = new ContentFragment01();
                setFragment(R.id.fragment_content, fragment01);
                break;
            case R.id.tv_02:
                if(fragment02 == null)
                    fragment02 = new ContentFragment02();
                setFragment(R.id.fragment_content, fragment02);
                break;
            case R.id.tv_03:
                if(fragment03 == null)
                    fragment03 = new ContentFragment03();
                setFragment(R.id.fragment_content, fragment03);
                break;
            case R.id.tv_04:
                if(fragment04 == null)
                    fragment04 = new ContentFragment04();
                setFragment(R.id.fragment_content, fragment04);
                break;

        }
    }
}

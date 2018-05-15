package com.example.jam68ty.foodbattle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jam68ty.foodbattle.FragmentOne;
import com.example.jam68ty.foodbattle.FragmentTwo;
import com.example.jam68ty.foodbattle.FragmentThree;

import android.app.FragmentTransaction;


public class Navigation extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.recommend);
                    return true;
                case R.id.navigation_space:
                    mTextMessage.setText(R.string.space);
                    return true;
                case R.id.navigation_market:
                    mTextMessage.setText(R.string.market);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        init();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void init() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(R.id.content, fragmentOne).add(R.id.content, fragmentTwo).add(R.id.content, fragmentThree);//开启一个事务将fragment动态加载到组件
        beginTransaction.hide(fragmentOne).hide(fragmentTwo).hide(fragmentThree);//隐藏fragment
        beginTransaction.addToBackStack(null);//返回到上一个显示的fragment
        beginTransaction.commit();//每一个事务最后操作必须是commit（），否则看不见效果
        showNav(R.id.navigation_home);
    }

    private void showNav(int navid) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        switch (navid) {
            case R.id.navigation_home:
                beginTransaction.hide(fragmentTwo).hide(fragmentThree);
                beginTransaction.show(fragmentOne);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_space:
                beginTransaction.hide(fragmentOne).hide(fragmentThree);
                beginTransaction.show(fragmentTwo);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_market:
                beginTransaction.hide(fragmentTwo).hide(fragmentOne);
                beginTransaction.show(fragmentThree);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
        }
    }
}

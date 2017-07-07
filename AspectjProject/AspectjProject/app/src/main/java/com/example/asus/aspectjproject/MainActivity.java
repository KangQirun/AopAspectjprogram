package com.example.asus.aspectjproject;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.mylibrary.SystemInformation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SystemInformation.ac=this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请输入");
        builder.setIcon(R.drawable.abc_ic_menu_selectall_mtrl_alpha);
        builder.setView(new EditText(MainActivity.this));
        builder.setPositiveButton("是" ,  null );
        builder.setNegativeButton("否", null);
        builder.show();
        myprin1(12,20);
        myprin2(8);

    }

    public  void myprin1(int b,int c)
    {

        Log.e("1111111111","sssssssssssssssssssss");
        myprin2(6);

    }
    public  void myprin2(int b)
    {

        // System.out.println(Integer.toString(b));
    }
}
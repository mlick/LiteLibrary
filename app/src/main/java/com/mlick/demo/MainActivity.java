package com.mlick.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mlick.demo.butterknife.DemoActivity;
import com.mlick.demo.butterknife.DemoFragmentActivity;
import com.mlick.demo.rxandroid.OriginMainActivity;
import com.mlick.demo.rxandroid.SimpleMainActivity;


public class MainActivity extends ListActivity {

    private String[] items = {
            /*0*/"ButterKnifeDemo",
            /*1*/"ButterKnifeFragmentDemo",
            /*2*/"SimpleMainActivity",
            /*3*/"OriginMainActivity"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
//        DigUtils.getCustomProgressDialog(this, "加载中", R.style.ProgressDialogTheme).show();
//        DigUtils.getCustomProgressDialog(this, "加载中").show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, DemoActivity.class);
                break;
            case 1:
                intent = new Intent(this, DemoFragmentActivity.class);
                break;
            case 2:
                intent = new Intent(this, SimpleMainActivity.class);
                break;
            case 3:
                intent = new Intent(this, OriginMainActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}

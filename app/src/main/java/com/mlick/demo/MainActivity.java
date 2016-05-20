package com.mlick.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mlick.example.R;

public class MainActivity extends ListActivity {

    private String[] items = {"DemoActivity", "DemoFragmentActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
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
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}

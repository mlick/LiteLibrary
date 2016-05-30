package com.mlick.demo;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.util.Log;

/**
 * Created by lxx on 2016/5/12 13:37
 */
public class TestActivity extends ActivityUnitTestCase<MainActivity> {


    public TestActivity() {
        super(MainActivity.class);
        Log.d("TestActivity", "onCreate");
    }

    public void testAdd() {
        Log.d("TestActivity", "TestCase");
    }

    public void testCase() {
        Log.d("TestActivity", "TestCase");
        getStartedActivityIntent();
        startActivity(new Intent(getInstrumentation().getContext(), MainActivity.class), null, null);
    }

    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getContext(), MainActivity.class);
        startActivity(intent, null, null);
    }



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate");
//    }
}

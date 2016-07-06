package com.mlick.demo;

import android.content.Intent;
import android.os.Environment;
import android.test.ActivityUnitTestCase;
import android.util.Log;

import com.lite.library.utils.CrashHandler;
import com.lite.library.utils.zip.DirTraversal;
import com.lite.library.utils.zip.ZipUtils;

import java.io.File;
import java.io.IOException;

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
        startActivity(new Intent(getInstrumentation()
                .getContext(), MainActivity.class), null, null);
    }

    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getContext(), MainActivity.class);
        startActivity(intent, null, null);
    }


    public void testAct() {

    }

    public void testZip() {
        final String dirName = Environment.getExternalStorageDirectory()
                                          .getAbsolutePath() + File.separator + CrashHandler.DIRNAME + File.separator;
        File outFile = DirTraversal.getFilePath(dirName);
        try {
            ZipUtils.zipFiles(DirTraversal.listLinkedFiles(dirName), outFile);
//            DirTraversal.deleteListFiles(dirName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate");
//    }
}

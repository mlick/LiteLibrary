package com.mlick.lite.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lxx on 2016/5/12 14:07
 * 文件操作工具类
 */
public class FileUtils {


    private static void savaFiles(String dirName, String fileName) {
//        String fileName = "crash-test" + ".log";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
//                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "QIYueUserCrash" + File.separator);
                File dir = new File(dirName);
                Log.i("CrashHandler", dir.toString());
                boolean isCreadteSuccess = true;
                if (!dir.exists()) {
                    isCreadteSuccess = dir.mkdirs();
                }
                if (isCreadteSuccess) {
                    FileOutputStream fos = new FileOutputStream(new File(dir,
                            fileName));
                    fos.write("test".toString().getBytes());
                    fos.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileAbsPath(String dirName, Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + dirName + File.separator;
        } else {
            return context.getFilesDir().getPath() + dirName;
        }
    }

    public String getFileAbsPath(String dirName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + dirName + File.separator;
        } else {
            return "/data/data/" + dirName;
        }
    }
}

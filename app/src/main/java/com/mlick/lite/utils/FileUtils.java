package com.mlick.lite.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lxx on 2016/5/12 14:07
 * 文件操作工具类
 */
public class FileUtils {


    /**
     * 保存内容到文件中
     *
     * @param dirName  目录名字
     * @param fileName 文件名字
     * @param comtent  要存储的内容
     */
    private static void savaFiles(String dirName, String fileName, String comtent) {
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
                    FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
                    fos.write(comtent.getBytes());
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到文件的绝对路径
     */
    public String getFileAbsPath(String dirName, Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + dirName + File.separator;
        } else {
            return context.getFilesDir().getPath() + dirName;
        }
    }

    /**
     * 得到文件的绝对路径
     *
     * @param dirName 目录的名字
     * @return 返回含有系统路径名字
     */
    public String getFileAbsPath(String dirName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + dirName + File.separator;
        } else {
            return "/data/data/" + dirName;
        }
    }

    /**
     * @return 得到系统的决定路径
     */
    public String getSysAbsPath() {
        return getFileAbsPath("");
    }
}

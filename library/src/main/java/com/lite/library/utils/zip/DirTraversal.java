package com.lite.library.utils.zip;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * 文件夹遍历
 *
 * @author miaowei
 */
public class DirTraversal {

    //no recursion
    public static LinkedList<File> listLinkedFiles(String strPath) {
        File dir = new File(strPath);
        File file[] = dir.listFiles();
        LinkedList<File> list = null;
        if (dir.exists() && file != null && file.length > 0) {
            list = new LinkedList<>();
            for (int i = 0; i < file.length; i++) {
                list.add(file[i]);
            }
        }
        return list;
    }

    public static void deleteListFiles(String filesName) {
        LinkedList<File> files = listLinkedFiles(filesName);
        if (files != null && files.size() > 0) {
            for (File file : files) {
                file.delete();
            }
        }
    }
//    //recursion
//    public static ArrayList<File> listFiles(String strPath) {
//        return refreshFileList(strPath);
//    }
//
//    public static ArrayList<File> refreshFileList(String strPath) {
//        ArrayList<File> filelist = new ArrayList<File>();
//        File dir = new File(strPath);
//        File[] files = dir.listFiles();
//
//        if (files == null) return null;
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].isDirectory()) {
//                refreshFileList(files[i].getAbsolutePath());
//            } else {
//                if (files[i].getName().toLowerCase().endsWith("zip")) {
//                    filelist.add(files[i]);
//                }
//
//            }
//        }
//        return filelist;
//    }
//
//    public static ArrayList<File> arrayListFiles(String strPath) {
//
//        ArrayList<File> filelist = new ArrayList<File>();
//        File dir = new File(strPath);
//        File[] files = dir.listFiles();
//        for (int i = 0; i < files.length; i++) {
//
//            filelist.add(files[i].getAbsoluteFile());
//        }
//        return filelist;
//    }
    //-----4.0读取文件的报 open failed: ENOENT (No such file or directory)

    /**
     * 可先创建文件的路径
     *
     * @param filePath
     */
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 然后在创建文件名就不会在报该错误
     */
    public static File getFilePath(String filePath) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String fileName() {
        return "crash-" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
                .format(new Date()) + "-" + System.currentTimeMillis() + ".zip";
    }
}

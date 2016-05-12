package com.mlick.lite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .build();
//        ServerAPI service = retrofit.create(ServerAPI.class);
//
//        Call<List<String>> repos = service.listRepos("octocat");
//        savaFiles();
//        boolean is = SysUtils.selfPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (!is) {
////            Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
////            startActivity(intent);
////            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
////            Intent intent = new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);//permission
////            Uri uri = Uri.fromParts("package", getPackageName(), null);
////            intent.setData(uri);
////            startActivity(intent);
////            SysUtils.showHelperDialog(this,"存储");
//        }
    }


    public static String getString() {
        return MainActivity.class.getName();
    }
}

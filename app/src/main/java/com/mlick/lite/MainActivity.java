package com.mlick.lite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.hello_tv) TextView helloTv;
    @BindView(R.id.click_btn) Button clickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        helloTv.setText("sayHello");
        clickBtn.setText("show Hello");
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

    @OnClick({R.id.hello_tv, R.id.click_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hello_tv:
                Toast.makeText(this, "hello_tv", Toast.LENGTH_SHORT).show();
                break;
            case R.id.click_btn:
                Toast.makeText(this, "click_btn", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

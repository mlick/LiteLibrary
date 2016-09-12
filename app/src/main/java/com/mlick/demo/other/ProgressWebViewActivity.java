package com.mlick.demo.other;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lxx on 2016/9/9 15:44
 * 带有进度条的webview
 */
public class ProgressWebViewActivity extends BaseActivity {
    @BindView(R.id.url) TextView url;
    @BindView(R.id.myProgressBar) ProgressBar bar;
    @BindView(R.id.myWebView) WebView myWebView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_progress_webview;
    }

    @Override
    public void initViewData() {
        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (bar != null) {
                    if (newProgress == 100) {
                        bar.setVisibility(View.INVISIBLE);
                    } else {
                        if (View.INVISIBLE == bar.getVisibility()) {
                            bar.setVisibility(View.VISIBLE);
                        }
                        bar.setProgress(newProgress);
                    }
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url.getText().toString().trim());
    }

    @OnClick(R.id.myButton)
    public void onClick() {
        myWebView.reload();
    }
}
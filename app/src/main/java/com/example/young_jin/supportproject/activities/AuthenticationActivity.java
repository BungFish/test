package com.example.young_jin.supportproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.young_jin.supportproject.R;

public class AuthenticationActivity extends AppCompatActivity {

    public static final String CERT_TYPE_EXTRA = "certType";

    private static final String LOG_TAG = AuthenticationActivity.class.getSimpleName();
    private static final String ROOT_URL = "http://mobilehamdev.sknetworks.co.kr/web/cert/call_cert.jsp?";
    //    private static final String ROOT_URL = "http://mobileham.sknetworks.co.kr/web/cert/call_cert.jsp?";
    private static final String QUERY_CERT_TYPE = "CERT_TYPE";
    private static final String QUERY_APP_TYPE = "APP_TYPE";
    private static final String WEB_BRIDGE_OBJECT_NAME = "Native";

    private String mCertType = "HP";
    private WebAppInterface mWebAppInterface;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        if (getIntent() != null) {

            mCertType = getIntent().getStringExtra(CERT_TYPE_EXTRA);
        }

        setUpWebView();
    }

    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()) {

            mWebView.goBack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_out_left);
    }

    private void setUpWebView() {

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSaveFormData(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new ChromeClient());

        if (mCertType.equals(LoginActivity.AUTH_CERT_TYPE_IPIN)) {
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.getSettings().setUseWideViewPort(true);
        }


        if (Build.VERSION.SDK_INT >= 16) {
            mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }

        mWebAppInterface = new WebAppInterface();
        mWebView.addJavascriptInterface(mWebAppInterface, WEB_BRIDGE_OBJECT_NAME);

        Uri uri = Uri.parse(ROOT_URL).buildUpon()
                .appendQueryParameter(QUERY_CERT_TYPE, mCertType)
                .appendQueryParameter(QUERY_APP_TYPE, "HAM")
                .build();


        Log.i(LOG_TAG, uri.toString());

        mWebView.postUrl(uri.toString(), null);
    }

    private class WebAppInterface {

        @JavascriptInterface
        public void SubResult(String nameCheck, String jsonString) {

            Intent returnIntent = new Intent();
            returnIntent.putExtra(LoginActivity.NAME_CHECK_EXTRA, nameCheck);
            returnIntent.putExtra(LoginActivity.AUTH_RESULT_JSON_EXTRA, jsonString);
            setResult(RESULT_OK, returnIntent);
            finish();
            Log.i(LOG_TAG, nameCheck + "," + jsonString);

        }
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onCloseWindow(WebView window) {

            finish();
        }
    }
}
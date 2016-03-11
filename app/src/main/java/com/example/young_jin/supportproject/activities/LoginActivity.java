package com.example.young_jin.supportproject.activities;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.young_jin.supportproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public static final String AUTH_CERT_TYPE_HP = "HP";
    public static final String AUTH_CERT_TYPE_IPIN = "IPIN";
    public static final String NAME_CHECK_EXTRA = "nameCheck";
    public static final String AUTH_RESULT_JSON_EXTRA = "resultJson";

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private static final String WEB_BRIDGE_OBJECT_NAME = "Native";

    private static int AUTH_REQUEST_CODE = 0;

    private WebView mWebView;
    private WebAppInterface mWebAppInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpWebView();

    }

    private void setUpWebView() {

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSaveFormData(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        if (Build.VERSION.SDK_INT >= 16) {
            mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }

        mWebAppInterface = new WebAppInterface();
        mWebView.addJavascriptInterface(mWebAppInterface, WEB_BRIDGE_OBJECT_NAME);

        String folderPath = "file:android_asset/";
        String fileName = "app/index.html";
        String file = folderPath + fileName;

        mWebView.loadUrl(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == AUTH_REQUEST_CODE && resultCode == RESULT_OK) {

            String nameCheck = data.getStringExtra(NAME_CHECK_EXTRA);
            String responseJson = data.getStringExtra(AUTH_RESULT_JSON_EXTRA);

            mWebView.loadUrl("javascript:onFinishAuth('" + nameCheck + "','" + responseJson + "')");


        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()) {

            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }

    }

    private void showToastWithErrMsg(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    private class WebAppInterface {

        @JavascriptInterface
        public void openAuthWebView(String certType) {

            if (certType != null && (certType.equals(AUTH_CERT_TYPE_HP) || certType.equals(AUTH_CERT_TYPE_IPIN))) {

                Intent intent = new Intent(LoginActivity.this, AuthenticationActivity.class);
                intent.putExtra(AuthenticationActivity.CERT_TYPE_EXTRA, certType);
                startActivityForResult(intent, AUTH_REQUEST_CODE);
                overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            } else {

                showToastWithErrMsg("올바른 인증타입이 아닙니다.");
            }
        }

        @JavascriptInterface
        public void setUserInfo(String user) {

            try {
                JSONObject userJson = new JSONObject(user);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void setUserToken(String token) {
            Log.i(LOG_TAG, token);


        }

        @JavascriptInterface
        public void alertWithMsg(String msg) {
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.helloworld;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;


import com.example.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String tag = "KrxkMsg";
    private String innerText = "";
    private TextView textView;
    protected void helpSetText(String attachString) {
        if (textView == null) {
            Log.d(tag, "textView is null");
            return;
        }
        // 设置 TextView 文本
        Log.d(tag, "innerText: " + innerText);
        innerText += attachString + '\n';
        textView.setText(innerText);

        Log.d(tag, innerText);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "OnCreate here");
//        this.textView = findViewById(R.id.textView);

        helpSetText("OnCreate Before Layout");
        setContentView(R.layout.activity_main); // 此代码完成后才完成渲染，且分配内存，才能找到子控件

        // 只有在设置活动 View 后才能获取到子 textView
        this.textView = findViewById(R.id.textView);
        helpSetText("OnCreate After Layout");
    }

    @Override
    protected void onStart() {
        super.onStart();
        helpSetText("OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, innerText);
        helpSetText("OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        helpSetText("OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        helpSetText("OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        helpSetText("OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helpSetText("OnDestroy");
    }
}
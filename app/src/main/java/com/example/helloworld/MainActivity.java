package com.example.helloworld;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "KrxkMsg";
    private String innerText = "";
    private TextView textView;
    private boolean serviceStatus = false; // 表示起始为关闭
    protected void helpSetText(String attachString) {
        if (textView == null) {
            Log.d(TAG, "textView is null");
            return;
        }
        // 设置 TextView 文本
        Log.d(TAG, "innerText: " + innerText);
        innerText += attachString + '\n';
        textView.setText(innerText);

        Log.d(TAG, innerText);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate here");
//        this.textView = findViewById(R.id.textView);

        helpSetText("OnCreate Before Layout");
        setContentView(R.layout.activity_main); // 此代码完成后才完成渲染，且分配内存，才能找到子控件

        // 只有在设置活动 View 后才能获取到子 textView
        this.textView = findViewById(R.id.textView);
        helpSetText("OnCreate After Layout");

        Button startButton = findViewById(R.id.startButton);
        Log.d(TAG, "onCreate: startButton" + startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: startButton");
                if (serviceStatus) {
                    return;
                }
                startService(v);
                serviceStatus = true;
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        Log.d(TAG, "onCreate: stopButton" + stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: stopButton");
                if (!serviceStatus) {
                    return;
                }
                stopService(v);
                serviceStatus = false;
            }
        });

        Button broadcast = findViewById(R.id.broadcast);
        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcastIntent(v);
            }
        });

        Button addStu = findViewById(R.id.addStu);
        addStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddStu(v);
            }
        });

        Button delStu = findViewById(R.id.delStu);
        delStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelStu(v);
            }
        });

        Button queryStu = findViewById(R.id.queryStu);
        queryStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickQueryStudentNameByNo(v);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        helpSetText("OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, innerText);
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


    public void startService(View view) {
        Toast.makeText(MainActivity.this, "StartService", Toast.LENGTH_LONG).show();
        startService(new Intent(getBaseContext(), KrxkService.class));
    }

    public void stopService(View view) {
        Toast.makeText(MainActivity.this, "StopService",Toast.LENGTH_LONG).show();
        stopService(new Intent(getBaseContext(), KrxkService.class));
    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.krxk.Custom_Intent");
        // 设置被允许的接受者
        intent.setComponent(new ComponentName("com.example.helloworld",
                        "com.example.helloworld.MyReceiver"));
        sendBroadcast(intent);
    }

    public void onClickAddStu(View view) {
        ContentValues values = new ContentValues();
        String No = ((EditText)findViewById(R.id.stu_No)).getText().toString();
        String Name = ((EditText)findViewById(R.id.stu_Name)).getText().toString();

        values.put(StudentProvider.No, No);
        values.put(StudentProvider.NAME, Name);

        Uri uri = getContentResolver().insert(StudentProvider.ContentUri, values);

        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickQueryStudentNameByNo(View view) {

    }

    public void onClickDelStu(View view) {

    }
}
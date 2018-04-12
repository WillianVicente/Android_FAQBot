package com.chatapp.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private EditText messageEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CaptureMessageSend(View view) {
        messageEditText = findViewById(R.id.messageEditText);
        String message = messageEditText.getText().toString();
        Log.d("LOG", message);
    }
}

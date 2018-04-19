package com.chatapp.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.chatapp.chatapp.util.TimeNow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText messageEditText;
    private ListView listView;
    private ArrayList<Message> listMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageEditText = findViewById(R.id.messageEditText);
        listView = findViewById(R.id.listView);
    }

    public void CaptureMessageSend(View view) {
       Message msg = new Message("Usuario", messageEditText.getText().toString(), TimeNow.TimeIsNow());

       setMessageBox(msg);
       messageEditText.setText("");

        Message msg2 = new Message("Bot", "eae", TimeNow.TimeIsNow());
        setMessageBox(msg2);


    }

    public void setMessageBox(Message msg){
        listMessages.add(msg);

        MessageListAdapter<Message> adapter =
                 new MessageListAdapter<>(this, listMessages);

        listView.setAdapter(adapter);

    }
}

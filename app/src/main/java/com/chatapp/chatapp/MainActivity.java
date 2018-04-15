package com.chatapp.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText messageEditText;
    private ListView listView;
    private List<Message> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageEditText = findViewById(R.id.messageEditText);
        ListView listView = (ListView) findViewById(R.id.listView);
    }

    public void CaptureMessageSend(View view) {
        String message = messageEditText.getText().toString();
        Message msg = new Message("cliente", message);
        setMessageBox(msg);
    }

    public void setMessageBox(Message msg){
        ArrayList<Message> lista = new ArrayList<>();
        lista.add(msg);

        list = lista;

        MessageAdapter <Message> adapter =
                new MessageAdapter<>(this, list);

        listView.setAdapter(adapter);
    }
}

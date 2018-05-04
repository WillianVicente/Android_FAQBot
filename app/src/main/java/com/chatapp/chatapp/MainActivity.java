package com.chatapp.chatapp;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.chatapp.chatapp.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.chatapp.chatapp.util.Util.TimeIsNow;


public class MainActivity extends FragmentActivity {

    private EditText messageEditText;
    private ListView listView;
    private ArrayList<Message> listMessages = new ArrayList<>();
    private static String id;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageEditText = findViewById(R.id.messageEditText);
        listView = findViewById(R.id.listView);
        btn_send = findViewById(R.id.btn_send);

        IdDAO dao = new IdDAO(this);
        //Buscar o Id do usuario
        id = dao.recuperarId();
        if(id.isEmpty()){
            id = Util.RandomGuid();
            dao.InserirId(id);
        }
    }

    public void CaptureMessageSend(View view) {
        Message msg = new Message("Usuario", messageEditText.getText().toString(), Util.TimeIsNow());
        setMessageBox(msg);
        messageEditText.setText("");
        new ConsomeWS().execute("http://10.0.2.2:8080/FAQBot/api/sendmessage/"+ id + "&" + Util.RemoverAcentos(msg.getMessage()));
    }

    public void setMessageBox(Message msg){
        listMessages.add(msg);

        MessageListAdapter<Message> adapter =
                 new MessageListAdapter<>(this, listMessages);

        listView.setAdapter(adapter);
    }

    private class ConsomeWS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();
            try{
                Response response = client.newCall(request).execute();
                return response.body().string();
            }
            catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                int count = 0;
                JSONArray mensagens = new JSONArray(json);

                while(count >= 0){
                    try {
                         JSONObject mensagem =
                                mensagens.getJSONObject(count);

                        Message msg = new Message("Bot", mensagem.getString("resposta_mensagem"), TimeIsNow());
                        count += 1;
                        setMessageBox(msg);
                    }catch(Exception ex){
                        count = -1;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

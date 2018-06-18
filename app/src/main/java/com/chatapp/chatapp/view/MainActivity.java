package com.chatapp.chatapp.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.R;
import com.chatapp.chatapp.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.chatapp.chatapp.util.Util.TimeIsNow;

//Tela do chat de mensagens
public class MainActivity extends FragmentActivity {

    private static String idUsuario;

    private ListView listView;
    private EditText messageEditText;

    private String[] message;
    private ArrayList<Message> listMessages = new ArrayList<>();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    //Cria a tela de mensagens e recupera o ID do usuario da sobre a Intent.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent origemIntent = getIntent();
        idUsuario = origemIntent.getStringExtra(LoadingActivity.CHAVE);

        messageEditText = findViewById(R.id.messageEditText);
        listView = findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //opcional
        RecepcaoMensagem();
    }

    //Manda uma mensagem para a API com a intenção [BemVindo]
    //API retorna uma mensagem de boas vindas com o nome do usuario, caso não tenha cadastrado a API solicita um nome para cadastrar.
    public void RecepcaoMensagem(){
        message = new String[2];
        //message[0] = "http://10.0.2.2:8080/FAQBotAPI/api/message";
        message[0] = "http://10.0.3.2:8080/FAQBotAPI/api/message";
        message[1] = "{id:" + "\"" + idUsuario + "\"" + ", message:" + "\"[BemVindo]\"" + "}";
        new ConsomeWS().execute(message);
    }

    //Recebe a mensagem que o usuario digitou na tela e envia para a API
    public void CaptureMessageSend(View view) {
        Message msg = new Message("Usuario", messageEditText.getText().toString(), Util.TimeIsNow());

        if(!msg.getMessage().replace(" ", "").equals("") && !msg.getMessage().replace("\n", "").equals("")){
            setMessageBox(msg);
            msg.setMessage(msg.getMessage().replace("\n", " "));

            message = new String[2];
            //message[0] = "http://10.0.2.2:8080/FAQBotAPI/api/message";
            message[0] = "http://10.0.3.2:8080/FAQBotAPI/api/message";
            message[1] = "{id:" + "\"" + idUsuario + "\"" + ", message:" + "\"" + Util.RemoverAcentos(msg.getMessage()) + "\"" + "}";
            new ConsomeWS().execute(message);
        }
        messageEditText.setText("");
    }

    //Imprime na tela a mensagem do usuario ou do bot
    public void setMessageBox(Message msg){
        listMessages.add(msg);

        MessageListAdapter<Message> adapter =
                 new MessageListAdapter<>(this, listMessages);

        listView.setAdapter(adapter);
    }

    //Metodo que envia a mensagem do usuario via HTTP para a API e retorna a resposta
    private class ConsomeWS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... json) {
            OkHttpClient client = new OkHttpClient();

                RequestBody body = RequestBody.create(JSON, json[1]);
                Request request = new Request.Builder()
                        .url(json[0])
                        .post(body)
                        .build();
            try{
                Response response = client.newCall(request).execute();
                return response.body().string();
            }
            catch(IOException e){
                return "";
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
                Message msg = new Message("Bot", "O serviço está fora do ar no momento. Tente novamente mais tarde.", TimeIsNow());
                setMessageBox(msg);
                e.printStackTrace();
            } catch(Exception f){
                f.printStackTrace();
            }
        }
    }
}

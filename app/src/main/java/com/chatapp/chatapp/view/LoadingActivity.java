package com.chatapp.chatapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.chatapp.chatapp.R;
import com.chatapp.chatapp.dao.IdDAO;
import com.chatapp.chatapp.util.Util;

//Tela onde acontece a splash screen (Tela de inicilização onde aparece a imagem do FAQBOT)
public class LoadingActivity extends FragmentActivity {
    public final static String CHAVE = "LOGINID";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginToSQLite();
            }
        }, 2000);
    }

    //Recupera o Id do usuario, caso não exista ID cria um.
    private void loginToSQLite(){
        IdDAO dao = new IdDAO(this);
        String id = "";

        //Comando para recuperar o ID
        id = dao.RecuperarId();
        if(id.isEmpty()){
            //Comando para criar o ID
            id = Util.RandomGuid();
            dao.InserirId(id);
        }

        //Chama a tela onde envia as mensagens passando o ID do usuario como parametro (Tela MainActivity)
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CHAVE, id);
        startActivity(intent);
    }
}

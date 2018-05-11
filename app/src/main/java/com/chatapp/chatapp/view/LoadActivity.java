package com.chatapp.chatapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.chatapp.chatapp.R;
import com.chatapp.chatapp.dao.IdDAO;
import com.chatapp.chatapp.util.Util;

public class LoadActivity extends FragmentActivity {
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

    private void loginToSQLite(){
        IdDAO dao = new IdDAO(this);
        String id = "";

        id = dao.recuperarId();
        if(id.isEmpty()){
            id = Util.RandomGuid();
            dao.InserirId(id);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CHAVE, id);
        startActivity(intent);
    }
}

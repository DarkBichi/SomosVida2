package com.example.lejm1.donacionsangre;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.os.AsyncTask;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setBackgroundColor(Color.TRANSPARENT);
        progressBar.setProgress(0);
        auth = FirebaseAuth.getInstance();

        AsyncTaskCargaDatos ATCargaDatos = new AsyncTaskCargaDatos(this);
        ATCargaDatos.execute();

    }

    public class AsyncTaskCargaDatos extends AsyncTask<String, Integer, Boolean> {

        Context mContext;

        AsyncTaskCargaDatos(Context context) {
            mContext = context;
        }

        @Override
        protected Boolean doInBackground(String... params) {

            publishProgress(0);

            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                    publishProgress(i + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            FirebaseUser user = auth.getCurrentUser();
            if(user!=null){
                return true;
            }else{
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            progressBar.setProgress(value[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                mContext.startActivity(new Intent(mContext,MenuPrincipal.class));
                finish();
            }else{
                mContext.startActivity(new Intent(mContext, Bienvenida.class));
                finish();
            }

        }
    }// fin asynctask
}

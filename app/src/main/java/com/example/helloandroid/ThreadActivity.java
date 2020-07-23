package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class ThreadActivity extends AppCompatActivity {

    private Button threadButton;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        threadButton = findViewById(R.id.thread_button);
        threadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            threadButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            int count = 0;
            while (count < 10) {
                count += 1;
                publishProgress(count);
                SystemClock.sleep(1000);
            }
            myAsyncTask.cancel(true);
            return null;
        }

        protected void onPostExecute(String result) {
            threadButton.setText(result + 11);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            threadButton.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onCancelled() {
            threadButton.setEnabled(true);
        }

    }
}
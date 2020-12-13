package com.example.test;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 3 == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                    builder.setTitle("카운트").setMessage("1");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (count % 3 == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                    builder.setTitle("카운트").setMessage("2");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                    builder.setTitle("카운트").setMessage("3");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                count++;
            }
        });









    }
}
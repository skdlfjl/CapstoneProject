package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity4 extends AppCompatActivity {

    private TextView tv_studentID;
    private TextView tv_token;
    private TextView tv_show;

    private Button btn_finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);

        tv_studentID = findViewById(R.id.tv_studentID);
        tv_token = findViewById(R.id.tv_token);
        tv_show = findViewById(R.id.tv_show);

        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String studentID = intent.getStringExtra("studentID"); // 이건 학번 저장해둔거
        String token = intent.getStringExtra("token"); // 이건 토큰 저장해둔거
        String pick = intent.getStringExtra("pick"); // 이건 토큰 저장해둔거

        tv_studentID.setText(studentID);
        tv_token.setText(token);
        tv_show.setText(pick);

        btn_finish = (Button) findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
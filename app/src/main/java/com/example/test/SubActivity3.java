package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity3 extends AppCompatActivity {

    // 드디어 투표를 하는 페이지입니다!!!
    //
    private TextView tv_studentID;
    private TextView tv_token;

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        tv_studentID = findViewById(R.id.tv_studentID);
        tv_token = findViewById(R.id.tv_token);
        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String str2 = intent.getStringExtra("str2"); // 이건 학번 저장해둔거
        String str = intent.getStringExtra("str"); // 이건 토큰 저장해둔거
        tv_studentID.setText(str2);
        tv_token.setText(str);

        btn_next = (Button) findViewById(R.id.btn_next);
        // next 버튼 누르면 생기는 일 >> 일단 시작화면으로 돌아가게 해놨습니다~~
        // sub4를 만들어서 거기로 이동하게 해야합니다.
        // 이동할 때 next 버튼을 누르는데, 이때 후보자의 득표수가 +1 되야합니다
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
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

    // finish버튼을 눌렀을 때, Sub3에서 받은 데이터들을 이용해 DB가 업데이트 되어야 합니다1!!!
    // Ex)
    //- dict2
    //   -  "302327vyztx"
    //           "20171478" : 0
    //           "20171489" : 0
    //           "20181466" : 0

    // dict2[token][studentID] == 1
    // 해당 학생이 투표를 완료했으므로 값을 1로 업데이트 해줍니다. 삭제해줘도 됩니다.

    // EX)
    // - dict3
    //       - "302327vyztx"
    //                - name
    //                       - "유지민" : 0
    //                       - "이지희" : 0
    //                       - "기권" : 0
    //                - pledge
    //                       - "유지민" : "피자사줄게요"
    //                       - "이지희" : "콜라사줄게요"

    // dict3[token][name][pick] 값을 +1해준 상태로 업데이트 해줍니다.
    // 여기까지 하면 투표가 완료됩니다.
    // 이건 finish 버튼을 눌렀을 때 일어나야 하는 일입니다.


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

        tv_studentID.setText("투표 참여 학번: "+studentID);
        tv_token.setText("token: "+token);
        tv_show.setText("선택된 후보자: "+pick);



        // finish버튼을 눌렀을 때, DB가 업데이트 되도록 해야합니다. 여기까지 하면 투표가 완료됩니다.
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
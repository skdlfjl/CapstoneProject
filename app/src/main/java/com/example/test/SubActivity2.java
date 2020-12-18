//투표하는 창입니다
package com.example.test;
// 후보자 + 공약 보여주는 페이지 입니다. 후보자만 입력된 경우 이 페이지는 패스합니다!

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SubActivity2 extends AppCompatActivity {

    private TextView tv_studentID;
    private TextView tv_token;

    private Button btn_next;

    private static final float FONT_SIZE = 17;
    private LinearLayout linearLayout;


    // 여기는 단순히 후보와 공약만 보여주는 페이지 입니다 (투표 페이지 아님)

    String[] names = {"유지민", "이지희", "장보현", "이가현"};
    String[] pledge = {"당선되면 햄버거를 사주겠습니다", "저는 콜라 사드리겠습니다", "공약 없음", "뽑아주신다면 열심히 하겠습니다"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        tv_studentID = findViewById(R.id.tv_studentID);
        tv_token = findViewById(R.id.tv_token);
        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String studentID = intent.getStringExtra("studentID"); // 이건 학번 저장해둔거, 이 페이지에서 사용 안합니다 그냥 뒤로 날립니다
        String token = intent.getStringExtra("token"); // 이건 토큰 저장해둔거 이 페이지에서 사용합니다
        tv_studentID.setText("투표 참여 학번: "+studentID);
        tv_token.setText("token: "+token);

        textview();   //동적 텍스트뷰를 이용해서 후보자와 공약들을 화면에 출력해줍니다

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity2.this, SubActivity3.class);
                intent.putExtra("studentID", studentID);  // putExtra로 studentID(학번)데이터를 담는다
                intent.putExtra("token", token);    // token을 담는다
                startActivity(intent);

            }
        });




    }
    public void textview(){
        for(int i = 0; i < pledge.length; i++){
            linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
            TextView view1 = new TextView(this);
            view1.setId(i);
            //view1.setText(names[i]);
            view1.setText(names[i]+':'+pledge[i]);
            view1.setTextSize(FONT_SIZE);
            view1.setTextColor(Color.BLACK);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            view1.setLayoutParams(lp);

            linearLayout.addView(view1);
        }

    }
}

package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {


    private TextView tv_token;
    private EditText et_search;
    private Button btn_search;

    private String search;



    String[] student_id = {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1",};  //임시임
    //스크롤뷰 안써도 알아서 쭉 나오네유

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tv_token = findViewById(R.id.tv_token);
        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String token = intent.getStringExtra("token"); // 메인 액티비티에서 날려보낸 데이터값을 지정한 이름이 "token"이었으니까 여기도 "token"로 받는다
        tv_token.setText(token);

        // token변수에는 투표로 이동할 수 있는 token이 담겨있습니다.
        // token_dict의 search를 사용해서, edittext에 입력된 관리자용 token이 맞는지 확인해야합니다.
        // 대충 적어보면 search[token] == (edittext에 입력된 관리자용 token)
        // 이걸 만족하면 T, 만족하지 않으면 관리자용 토큰이 틀린거니까 토스트 메세지를 출력합니다.
        // T인경우에는
        et_search = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = et_search.getText().toString();
                //여기서 원래 if문을 써야합니다링
                //search[token] == (edittext에 입력된 관리자용 token)
                //만약 T인 경우라면 dict2에서 value가 0인 사람들만 쭉 뽑아서 보여주면 됩니다
                //일단 예시로 student_id를 사용합니다
                String text = "";
                for (int i = 0; i < student_id.length; i++) {
                    text += student_id[i] + '\n';
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setTitle("투표 미참여 학번 명단").setMessage(text);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }
}
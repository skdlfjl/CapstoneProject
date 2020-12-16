package com.example.test;
// 확인용 수정2
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    static int find(String[] arr, String s) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(s)) {
                break;
            }
        }
        return 1;
    }

    private EditText et_token;
    private Button btn_insert;
    private Button btn_search;
    private com.google.android.material.floatingactionbutton.FloatingActionButton btn_creat;
    //private Button btn_count;
    private String token;    // 입력된 토큰을 받는 변수입니다
    String[] tokens = {"1111","2222","3333","4444","5555"};   //이걸 데이터베이스에서 가져와야 합니다 (수정필요!!!!!!) token_dict의 tokens에서 가져옵니다
    int where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 여기는 정말 코드가 많이 추가되어야 합니다.
        // tokens_dict에서 일단 입력된 토큰(token변수에 저장했네요)을 key값으로 이용하게 됩니다.
        // tokens_dict의 tokens를 사용하게 되는데, tokens[token] 값이 0이면 학번쓰는 페이지 sub로 넘어가고, 1이면 투표 완료페이지로 가야합니다.
        // 물론 저장되지 않은 토큰이면 아래처럼 토스트메세지를 입력합니다
        btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을때 실행되는거!!
                et_token = findViewById(R.id.et_token);
                token = et_token.getText().toString();  // 넣은 토큰을 string형태로 가져와서 token(빈 변수)에 저장한다

                for(int i = 0; i < tokens.length; i++) {
                    if(tokens[i].equals(token)) {
                        where = 1;
                        break;
                    }
                    where = 0;
                }
                if(where==1) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("token", token);  // putExtra로 token(토큰 들어있음)데이터를 담는다
                    startActivity(intent);  // 실제로 액티비티 이동하는 구문, 담은 데이터를 서브 액티비티로 쏜다!!  >>  서브 액티비티로 가서 코드를 만들어주자
                }else{
                    Toast.makeText(getApplicationContext(),"저장되어있지 않은 토큰입니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_creat = findViewById(R.id.btn_creat);
        btn_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatActivity.class);
                startActivity(intent);
            }
        });





        // 위와 구현방법이 거의 동일합니다.
        // 사용되는 토큰을 저장해둔 tokens_dict의 tokens의 토큰이 0일 경우, 페이지가 넘어갑니다.
        // 1일경우 투표가 완료되었으므로 이미 투표가 완료되었다는 토스트 메세지를 출력합니다.
        // 물론 저장되지 않은 토큰이면 아래처럼 토스트메세지를 입력합니다

        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을때 실행되는거!!
                et_token = findViewById(R.id.et_token);
                token = et_token.getText().toString();  // 넣은 토큰을 string형태로 가져와서 token(빈 변수)에 저장한다

                for(int i = 0; i < tokens.length; i++) {
                    if(tokens[i].equals(token)) {
                        where = 1;
                        break;
                    }
                    where = 0;
                }
                if(where==1) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("token", token);  // putExtra로 token데이터를 담는다
                    startActivity(intent);  // 실제로 액티비티 이동하는 구문, 담은 데이터를 서브 액티비티로 쏜다!!  >>  서브 액티비티로 가서 코드를 만들어주자
                }else{
                    Toast.makeText(getApplicationContext(),"저장되어있지 않은 토큰입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



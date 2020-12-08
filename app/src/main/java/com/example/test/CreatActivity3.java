package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CreatActivity3 extends AppCompatActivity {

    private Button btn_revert;

    private TextView tv_list1;
    private TextView tv_list2;
    private int list1_size;
    private int list2_name_size;
    private int list2_pledge_size;

    private TextView tv_list3;
    private TextView tv_list4;

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat3);

        tv_list1 = findViewById(R.id.tv_list1);
        tv_list2 = findViewById(R.id.tv_list2);

        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        ArrayList list1 = (ArrayList) intent.getSerializableExtra("list1");
        ArrayList list2_name = (ArrayList) intent.getSerializableExtra("list2_name");
        ArrayList list2_pledge = (ArrayList) intent.getSerializableExtra("list2_pledge");

        //list1_size = list1.size();
        //for(int i=0; i<list1_size; i++){
        //    tv_list1.setText((String) list1.get(i));
        //}


        list1_size = list1.size();
        for(int i=0; i<list1_size; i++){
            //저장할때 걍 \n문자 빼버려서 죄다 쭈루룩 합쳐진상태로 찍히길래 그냥 textview에 찍어줄때만 \n 추가해서 찍어준거임~~~
            sb1.append(list1.get(i) + "\n");
            tv_list1.setText(sb1.toString());
        }

        //StringBuilder sb = new StringBuilder();
        list2_name_size = list2_name.size();
        if (list2_pledge == null){  // 만약 공약이 null값이면 sb에 이름만 저장
            for(int i=0; i<list2_name_size; i++){
                sb2.append(list2_name.get(i) + "\n");
                tv_list2.setText(sb2.toString());
            }
        } else {   // 공약이 null값이 아니면 sb에 이름과 공약 같이 이어서 저장 (텍스트뷰에 찍힘)
            list2_pledge_size = list2_pledge.size();
            for(int i=0; i<list2_name_size; i++){
                sb2.append(list2_name.get(i) + " : " + list2_pledge.get(i) + "\n");
                tv_list2.setText(sb2.toString());
            }
        }


        tv_list3 = findViewById(R.id.tv_list3);
        tv_list3.setText("" + "후보 이름 개수 : " + list2_name_size);
        //tv_list3.setText("" + list2_pledge_size);

        tv_list4 = findViewById(R.id.tv_list4);
        tv_list4.setText("" + "저장된 공약 개수 : " + list2_pledge_size);




        //이건 버튼 눌렀을 때 맨 처음화면으로 돌아가는거
        btn_revert = findViewById(R.id.btn_revert);
        btn_revert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
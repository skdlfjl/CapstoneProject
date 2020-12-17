package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreatActivity3 extends AppCompatActivity  {

    private Button btn_revert;

    private TextView tv_list1;
    private TextView tv_list2;
    private int list1_size;
    private int list2_name_size;
    private int list2_pledge_size;

    private TextView tv_token;      // 여기에서 토큰(투표하러 갈수있는 토큰)을 찍어주면 됩니다.
    private TextView tv_search;     // 여기에서 search토큰(투표 안한사람 확인하는 토큰)을 찍어주면 됩니다.

    private TextView tv_list3;
    private TextView tv_list4;

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();


    private Long key_value;
    private String str_token;
    private String str_key_value;
    private String str_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat3);

        tv_list1 = findViewById(R.id.tv_list1);
        tv_list2 = findViewById(R.id.tv_list2);

        tv_token = findViewById(R.id.tv_token);         // 여기에서 토큰(투표하러 갈수있는 토큰)을 찍어주면 됩니다.
//      tv_search = findViewById(R.id.tv_search);       // 여기에서 search토큰(투표 안한사람 확인하는 토큰)을 찍어주면 됩니다.
        //"key"의 value값 + 1 을 이용해 dict1에서 token들을 가져오면 됩니다.

        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        ArrayList list1 = (ArrayList) intent.getSerializableExtra("list1");
        ArrayList list2_name = (ArrayList) intent.getSerializableExtra("list2_name");
        ArrayList list2_pledge = (ArrayList) intent.getSerializableExtra("list2_pledge");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();//루트

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // key 의 value 값을 가지고옴
                str_key_value =  String.valueOf(snapshot.child("dict1").child("key").getValue());
                key_value = (Long) snapshot.child("dict1").child("key").getValue();
                // str_key_value 로 찾은  token 의 value 값
                str_token = String.valueOf(snapshot.child("dict1").child("tokens").child(str_key_value).getValue());
                // search의 값 찾기
                str_search = String.valueOf(snapshot.child("dict1").child("search").child(str_key_value).getValue());
                tv_token.setText(str_token); // str token 이 진짜 토큰 값
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


        list1_size = list1.size();
        for (int i = 0; i < list1_size; i++) {
            //저장할때 걍 \n문자 빼버려서 죄다 쭈루룩 합쳐진상태로 찍히길래 그냥 textview에 찍어줄때만 \n 추가해서 찍어준거임~~~
            sb1.append(list1.get(i) + "\n");
            tv_list1.setText(sb1.toString());
        }
        System.out.println(list1);
        System.out.println(list1_size);


        list2_name_size = list2_name.size();
        if (list2_pledge == null) {  // 만약 공약이 null값이면 sb에 이름만 저장
            for (int i = 0; i < list2_name_size; i++) {
                sb2.append(list2_name.get(i) + "\n");
                tv_list2.setText(sb2.toString());
            }
        } else {   // 공약이 null값이 아니면 sb에 이름과 공약 같이 이어서 저장 (텍스트뷰에 찍힘)
            list2_pledge_size = list2_pledge.size();
            for (int i = 0; i < list2_name_size; i++) {
                sb2.append(list2_name.get(i) + " : " + list2_pledge.get(i) + "\n");
                tv_list2.setText(sb2.toString());
            }
        }

        //화면에 찍히는 부분
        tv_list3 = findViewById(R.id.tv_list3);
        tv_list3.setText("" + "후보 이름 개수 : " + list2_name_size);
        //tv_list3.setText("" + list2_pledge_size);
        tv_list4 = findViewById(R.id.tv_list4);
        tv_list4.setText("" + "저장된 공약 개수 : " + list2_pledge_size);

        DatabaseReference dict1Ref = database.getReference("dict1");
        DatabaseReference dict2Ref  = database.getReference("dict2");
        DatabaseReference dict3Ref = database.getReference("dict3");
        DatabaseReference token_dictRef = database.getReference("token_dict");



        //이건 버튼 눌렀을 때 맨 처음화면으로 돌아가는거
        btn_revert = findViewById(R.id.btn_revert);
        btn_revert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (int i = 0; i<list1_size; i++) {
                    dict2Ref.child(str_token).child(list1.get(i).toString()).setValue(0);
                }
                for (int i = 0; i < list2_name_size; i++) {
                    dict3Ref.child(str_token).child("name").child(list2_name.get(i).toString()).setValue(0);
                    dict3Ref.child(str_token).child("name").child("기권").setValue(0);
                }
                for (int t = 0; t < list2_pledge_size; t++) {
                    dict3Ref.child(str_token).child("pledge").child(list2_name.get(t).toString()).setValue(list2_pledge.get(t));
                }

                token_dictRef.child("search").child(str_token).setValue(str_search);

                dict1Ref.child("key").setValue(key_value+1);



                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });

    }
}
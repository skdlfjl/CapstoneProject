package com.example.test;
//이 페이지는 토큰을 입력했을 때, 토큰에 따른 투표시트템이 등장해야합니다
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    static int find(String[] arr, String s) {      // find함수 한번 손 봐야합니다 .... DB랑 연동했을때 쓸거같진 않지만 혹시 모르니까요
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].indexOf(s) >= 0)
                return 1;
        }
        return -1;
    }

    private TextView tv_token;
    private EditText et_studentID;
    private Button btn_next;
    private String studentID;    // 입력된 학번을 받는 변수입니다
    int where;

    // dict2 = {"302327vyztx" : [["20171478", Null], ["20171489", Null], ["20181466", Null]]}
    String[] student_id = {"20171478","20181234","20192345","20205532","20213356"};
    // 이걸 MainActivity의 입력된 token을 이용해, DB의 dict2에서 가져와야 합니다!!!!!!!!!!!!!!!!!!!!!!!!!
    // 만약 입력받은 token이 "302327vyztx"라면, dict2["302327vyztx"]로 선거참여 명부를 가져올 수 있습니다
    // [["20171478", Null], ["20171489", Null], ["20181466", Null]] << 이런식으로요

    // 파이썬이라면 이렇게 구현했을겁니다
    //student_id = []
    //>>> for i in range(len(dict2["302327vyztx"])):
    //...     a = dict2["302327vyztx"][i][0]
    //...     student_id.append(a)
    // 이렇게하면 student 리스트에 "302327vyztx"토큰에 해당되는 선거참여학생 학번이 쌓이게 됩니다


    // dict3 = {"302327vyztx" : [("유지민", "당선되면 햄버거를 사주겠습니다"), ("이지희", "콜라 사드리겠습니다")]}
    // 1번경우
    String[] names = {"유지민", "이지희"};
    String[] pledge = {"당선되면 햄버거를 사주겠습니다", "콜라 사드리겠습니다"};
    // 2번경우
    //String[] names = {"유지민", "이지희"};
    //String[] pledge;

    // 1. 후보자와 공약이 합쳐져 튜플로 저장된상태
    // if문을 사용해야합니다. 어떻게 쓰느냐?
    // if(isinstance(dict[1][0], tuple) 뭐 이렇게 쓰면 되지 않을까요? 근데 이건 파이썬이라서..
    // 자바라면 좀 더 생각해봐야합니다. 튜플 말고 그냥 리스트 써도 되구요
    // https://nightohl.tistory.com/entry/element%EA%B0%80-list%EC%9D%B8%EC%A7%80-%ED%8C%90%EB%8B%A8%ED%95%98%EA%B8%B0
    // 말이 너무 길어졌네요.. 일단 저렇게 if문을 만족시킨다면 후보자와 공약이 둘 다 작성되었다는 뜻 입니다.
    // 파이썬이라면 이렇게 구현했을겁니다
    //names = []
    //pledge = []
    //>>> for i in range(len(dict3["302327vyztx"])):
    //...     n = dict3["302327vyztx"][i][0]
    //...     names.append(n)
    //...     p = dict3["302327vyztx"][i][1]
    //...     pledge.append(p)
    // 이렇게하면 각 변수에 후보자와 공약이 저장되겠지요?
    // 그리고 이걸 요 페이지에서 textview로 찍어줍니다

    // 2. 후보자만 입력된 상태
    // 후보자만 굳이 찍어서 보여줄것인가? 음 정말 굳이..네요 필요가 없을 것 같습니다.
    // 그렇다면 어떻게 해줘야할까요?
    // 지금까지 적은 내용을 SubActivity2가 아닌 SubActivity에서 해주면 됩니다.
    // 위에 사용한 if문이 T일경우 SubActivity2로 넘어와서 후보자랑 공약을 보여주면 되구요
    // F일 경우 바로 SubActivity3으로 넘어가서 투표를 진행하면 됩니다.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 매우 중요한 설명이니 꼭 읽으세요!!!!!!!!!!!!!!!!!!!!!!!
        tv_token = findViewById(R.id.tv_token);
        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String token = intent.getStringExtra("token"); // 메인 액티비티에서 날려보낸 데이터값을 지정한 이름이 "token"이었으니까 여기도 "token"로 받는다
        tv_token.setText(token);
        // 이거는 그냥 텍스트뷰에 토큰을 찍어서 보여주는 코드입니다(토큰이 존재할 경우)
        // 여기서 이 받아온 토큰을 통해 student_id = {"20171478","20181234","20192345","20205532","20213356"} << 이 값을 db에서 받아와야 합니다.


        et_studentID = findViewById(R.id.et_studentID);
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // next 버튼 클릭했을때 실행되는거!!
                studentID = et_studentID.getText().toString();  // 입력한 학번을 string 형태로 가져와서 studentID(빈 변수)에 저장한다
                where = find(student_id, studentID);

                if(where==1) {
                    //dict3을 이용하는 코드입니다. if(isinstance(dict[1][0], tuple)이 T일 경우에만 pledge가 생성됩니다.
                    if(pledge != null) {
                        //pledge가 존재할경우 SubActivity2(후보자, 공약 보여주는 페이지)로!
                        Intent intent = new Intent(SubActivity.this, SubActivity2.class);
                        intent.putExtra("studentID", studentID);  // putExtra로 studentID(입력한 학번)데이터를 담는다
                        intent.putExtra("token", token);    // token을 담는다
                        startActivity(intent);  // 실제로 액티비티 이동하는 구문, 담은 데이터를 서브 액티비티로 쏜다!!  >>  서브 액티비티로 가서 코드를 만들어주자
                    } else {
                        //pledge가 존재하지 않을경우 SubActivity3(투표하는 페이지)로!!!
                        Intent intent = new Intent(SubActivity.this, SubActivity3.class);
                        intent.putExtra("studentID", studentID);  // putExtra로 studentID(입력한 학번)데이터를 담는다
                        intent.putExtra("token", token);    // token을 담는다
                        startActivity(intent);  // 실제로 액티비티 이동하는 구문, 담은 데이터를 서브 액티비티로 쏜다!!  >>  서브 액티비티로 가서 코드를 만들어주자
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"선거 참여명부에 등록되지 않은 학번입니다.",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



}
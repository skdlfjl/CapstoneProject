//투표하는 창입니다
package com.example.test;
// 후보자 + 공약 보여주는 페이지 -> xml 스크롤뷰로 바꿔주기

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity2 extends AppCompatActivity {

    private TextView tv_studentID;
    private TextView tv_token;

    private Button btn_next;

    // 여기에선 DB의 2가지 dict가 사용됩니다
    // dict2 = {"302327vyztx" : [["20171478", Null], ["20171489", Null], ["20181466", Null]]}
    // dict3 = {"302327vyztx" : [("유지민", "당선되면 햄버거를 사주겠습니다"), ("이지희", "콜라 사드리겠습니다")]}

    // 일단 SubActivity에서 선거참여명부에 등록된(dict2)학생 학번이 하나 날라올것입니다. (studentID변수에 저장했습니다)
    // 이건 좀 이따가 보도록 하고 투표먼저 봅시다.

    // 일단 dict3을 이용해 투표시스템을 만들어야합니다.
    // 지금 액티비티를 분리할까 고민중입니다 (단순히 후보와 공약만을 보여주는 페이지와, 투표만 하는 페이지로)
    // 만약 분리한다면 여기는 단순히 후보와 공약만 보여주는 페이지가 되겠죠?
    // 그러면 딱히 dict2를 가져올 필요가 없겠네요, 그냥 학번만 받아서 또 다음 액티비티로 넘기면 됩니다.
    // 생각해보니 SubActivity에서 받은 token도 가져와야겠네요. token변수에 저장되어있습니다. (학번은 studentID)

    // Ex) 액티비티 분리
    // 여기에서는 특정 토큰에 해당되는 후보와 공약을 보여줘야합니다. 토큰이 "302327vyztx"라고 가정합시다.
    // DB에서 dict3을 가져와서 dict3["302327vyztx"]로 해당 토큰의 후보자와 공약을 가져올 수 있습니다.
    // 이때 dict3은 두가지 형태를 가지고있습니다.
    // 1. 후보자와 공약이 합쳐져 튜플로 저장된상태
    // 2. 후보자만 입력된 상태
    // 여기서 또 나눠서 정의를 해봐야겠네요; 하;;

    // 1. 후보자와 공약이 합쳐져 튜플로 저장된상태
    // if문을 사용해야합니다. 어떻게 쓰느냐?
    // if(isinstance(dict[1][0], tuple) 뭐 이렇게 쓰면 되지 않을까요? 근데 이건 파이썬이라서..
    // 자바라면 좀 더 생각해봐야합니다. 튜플 말고 그냥 리스트 써도 되구요
    // https://nightohl.tistory.com/entry/element%EA%B0%80-list%EC%9D%B8%EC%A7%80-%ED%8C%90%EB%8B%A8%ED%95%98%EA%B8%B0
    // 말이 너무 길어졌네요.. 일단 저렇게 if문을 만족시킨다면 후보자와 공약이 둘 다 작성되었다는 뜻 입니다.
    // 파이썬이라면 이렇게 구현했을겁니다
    //name = []
    //pledge = []
    //>>> for i in range(len(dict3["302327vyztx"])):
    //...     n = dict3["302327vyztx"][i][0]
    //...     name.append(n)
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
        setContentView(R.layout.activity_sub2);

        tv_studentID = findViewById(R.id.tv_studentID);
        tv_token = findViewById(R.id.tv_token);
        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String studentID = intent.getStringExtra("studentID"); // 이건 학번 저장해둔거, 이 페이지에서 사용 안합니다 그냥 뒤로 날립니다
        String token = intent.getStringExtra("token"); // 이건 토큰 저장해둔거 이 페이지에서 사용합니다
        //tv_studentID.setText(studentID);
        tv_token.setText(token);

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
}
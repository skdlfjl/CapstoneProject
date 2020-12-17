package com.example.test;
//선거명부 입력 후에 후보 명단과 공약을 입력 받습니다.
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class CreatActivity2 extends AppCompatActivity {

    private EditText et_name;
    private EditText et_pledge;
    private TextView tv_display;
    //내가 추가한 코드
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    //dd를 나중에 creatActivity3으로 보내줘야해서 미리 변수로 지정 public 이 나은지? 아니면 private 이 나은지?
    private Long key_value ;
    public ArrayList token_array ;
    public Integer len_token;

    static final String mFILENAME = "myContacts2.txt";

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat2);

        et_name = (EditText) findViewById(R.id.et_name);
        et_pledge = (EditText) findViewById(R.id.et_pledge);
        tv_display = (TextView) findViewById(R.id.tv_display);

        displayContacts();

        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        ArrayList list1 = (ArrayList) intent.getSerializableExtra("list1");

        btn_next = (Button) findViewById(R.id.btn_next);
        // next 버튼 누르면 생기는 일
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef = database.getReference("dict1");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        key_value= (Long) datasnapshot.child("key").getValue();
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if (list2_name.size() > 0) {   // 후보자 이름은 무조건 작성되어야합니다 (한명이상)
                    int j = 0;
                    for (int i = 0; i < list2_pledge.size(); i++) {
                        String a = (String) list2_pledge.get(i);
                        if (a.length() == 0) {
                            j += 1;
                            //continue;
                        }
                    }


                    if (j == list2_pledge.size()) {
                        Intent intent = new Intent(CreatActivity2.this, CreatActivity3.class);
                        intent.putExtra("list1", list1);
                        intent.putExtra("list2_name", list2_name);
                        // 내가 추가한 코드 : creatActivity3 으로 보내줌
                        intent.putExtra("token+1", key_value);
                        startActivity(intent);

                    } else if ( j == 0 ) {
                        Intent intent = new Intent(CreatActivity2.this, CreatActivity3.class);
                        intent.putExtra("list1", list1);
                        intent.putExtra("list2_name", list2_name);
                        intent.putExtra("list2_pledge", list2_pledge);
                        // 내가 추가한 코드 : creatActivity3 으로 보내줌
                        intent.putExtra("key_value", key_value);
                        startActivity(intent);



                    } else {
                        Toast.makeText(getApplicationContext(), "후보 공약이 일부 작성되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "후보자 이름이 작성되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }


    public void mOnInsertClick(View view) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try {
            fos = openFileOutput(mFILENAME, Context.MODE_APPEND);
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);

            String name = et_name.getText().toString();
            String pledge = et_pledge.getText().toString();

            dos.writeUTF(name);
            dos.writeUTF(pledge);

            dos.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (dos != null) dos.close();
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        displayContacts();

        et_name.setText("");
        et_pledge.setText("");
    }

    public void mOnDeleteClick(View view) {
        //지정된 파일 자체를 삭제할 수 있게하는 코드
        if (deleteFile(mFILENAME)) {
            tv_display.setText("delete success");
            list2_name.clear();
            list2_pledge.clear();
        }else
            tv_display.setText("delete failed");
    }



    ArrayList list2_name = new ArrayList();
    ArrayList list2_pledge = new ArrayList();
    private void displayContacts() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        try {
            fis = openFileInput(mFILENAME);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            String str = "";
            while (dis.available() > 0) {
                String name = dis.readUTF();
                String pledge = dis.readUTF();

                list2_name.add(name);
                list2_pledge.add(pledge);

                //String name_pledge = name + " | " + pledge;
                //list2.add(name_pledge);

                str += name + " : " + pledge + "\n";
            }
            tv_display.setText(str);

            //list2.add(str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) dis.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
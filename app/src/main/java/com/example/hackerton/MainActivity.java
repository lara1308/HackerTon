package com.example.hackerton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<DiaryItem> diaryItems = new ArrayList<>();
    FloatingActionButton addBtn;
    DiaryAdapter diaryAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkSelfPermission();

        gridView = findViewById(R.id.gridView);
        addBtn = findViewById(R.id.addButton);

        for (int i = 0; i < 33; i++) {
            if (i < 3) {
                diaryItems.add(new DiaryItem(0, "", 5, "", ""));
            } else if (i == 7) {
                diaryItems.add(new DiaryItem(i + 1, "첫번째 일기", 0, "content://media/external/images/media/106", "오늘은 분리수거를 열심히 했다!"));
            } else if (i == 10) {
                diaryItems.add(new DiaryItem(i + 1, "두번째 일기 ", 1, "content://media/external/images/media/104", "물을 아껴써야겠다."));
            } else if (i == 15) {
                diaryItems.add(new DiaryItem(i + 1, "세번째 일기", 2, "content://media/external/images/media/105", "음식물 쓰레기를 줄이기 위해 노력할 방법이 있을까?"));
            } else {
                diaryItems.add(new DiaryItem(i + 1, "", 5, "", ""));
            }
        }

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String temp = sharedPreferences.getString("category","");
        int category;

        if (temp.equals("Category1") || temp.equals("0")) {
            category = 0;
        } else if (temp.equals("Category2") || temp.equals("1")) {
            category = 1;
        } else if (temp.equals("Category3") || temp.equals("2")) {
            category = 2;
        }else if (temp.equals("Category4") || temp.equals("3")) {
            category = 3;
        }else if (temp.equals("Category5") || temp.equals("4")) {
            category = 4;
        } else {
            category = 5;
        }

        if (category != 5) {
            diaryItems.remove(5);
            DiaryItem diaryItem = new DiaryItem(5, sharedPreferences.getString("title",""), category, sharedPreferences.getString("image",""), sharedPreferences.getString("content",""));
            diaryItems.add(5, diaryItem);
        }

        diaryAdapter = new DiaryAdapter(diaryItems, MainActivity.this);
        gridView.setAdapter(diaryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaryItem diaryItem = diaryItems.get(position);

                if (diaryItem.getCategory() != 5) {
                    sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("title", diaryItem.getTitle());
                    editor.putString("category", Integer.toString(diaryItem.getCategory()));
                    editor.putString("content", diaryItem.getContent());
                    editor.putString("image", diaryItem.getImageUrl());
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), DiaryActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                }
            }
        }
    }

    public void checkSelfPermission() {
        String temp = "";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }


        if (TextUtils.isEmpty(temp) == false) {
            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
        }else {
            Toast.makeText(this, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
        }
    }
}
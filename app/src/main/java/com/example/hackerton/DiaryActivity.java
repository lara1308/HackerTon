package com.example.hackerton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

public class DiaryActivity extends AppCompatActivity {
    MaterialTextView diaryTitle;
    MaterialTextView diaryContent;
    ImageView diaryImage;
    Button diaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_diary);

        diaryTitle = findViewById(R.id.diaryTitle);
        diaryContent = findViewById(R.id.diaryContent);
        diaryImage = findViewById(R.id.diaryImage);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        diaryTitle.setText(sharedPreferences.getString("title",""));
        diaryContent.setText(sharedPreferences.getString("content",""));

        Uri uri = Uri.parse(sharedPreferences.getString("image",""));
        Bitmap bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        diaryImage.setImageBitmap(bitmap);

        diaryBtn = findViewById(R.id.diaryBtn);
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}

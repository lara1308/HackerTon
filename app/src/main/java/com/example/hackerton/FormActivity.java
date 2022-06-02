package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class FormActivity extends AppCompatActivity {

    TextInputEditText title;
    ChipGroup category;
    Chip checkedCategory;
    TextInputEditText content;
    ImageView image;
    Button button;
    Uri selectedImageUri;

    final int OPEN_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        title = findViewById(R.id.formTitle);
        category = findViewById(R.id.formCategory);
        content = findViewById(R.id.formContent);
        image = findViewById(R.id.formImage);
        button = findViewById(R.id.formBtn);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, OPEN_GALLERY);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedCategory = category.findViewById(category.getCheckedChipId());

                if (title.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "제목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if (content.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("title", title.getText().toString());
                    editor.putString("category", checkedCategory.getText().toString());
                    editor.putString("content", content.getText().toString());
                    editor.putString("image", selectedImageUri.toString());
                    editor.commit();

                    Toast.makeText(FormActivity.this, "일기가 저장되었습니다!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            image.setImageURI(selectedImageUri);
            image.setMaxHeight(100);
            image.setMaxWidth(100);
        }
    }
}
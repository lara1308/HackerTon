package com.example.hackerton;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {
    ArrayList<DiaryItem> diaryItems;
    Context context;
    LayoutInflater inflater;
    int[] categoryImages = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.zero};

    public DiaryAdapter(ArrayList<DiaryItem> diaryItems, Context context) {
        this.diaryItems = diaryItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return diaryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return diaryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {
            view = inflater.inflate(R.layout.calendar_layout, null);
        }

        MaterialTextView calendarNumber = view.findViewById(R.id.calendarNumber);
        ShapeableImageView calendarImage = view.findViewById(R.id.calendarImage);

        if (position < 3) {
            calendarNumber.setText("");
        } else {
            calendarNumber.setText(Integer.toString(position - 2));
        }

        calendarImage.setImageResource(categoryImages[diaryItems.get(position).getCategory()]);

        return view;
    }
}

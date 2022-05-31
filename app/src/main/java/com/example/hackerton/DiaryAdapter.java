package com.example.hackerton;

import android.content.Context;
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
    String[] categoryImages = {"category1.jpg", "category2.jpg", "category3.jpg", "category4.jpg", "category5.jpg"};

    public DiaryAdapter(ArrayList<DiaryItem> diaryItems, Context context) {
        this.diaryItems = diaryItems;
        this.context = context;
    }





    @Override
    public int getCount() {
        return diaryItems.size();
    }

    @Override
    public Object getItem(int i) {
        return diaryItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view==null){
            view = inflater.inflate(R.layout.calender,null);
        }

        MaterialTextView calenderText = view.findViewById(R.id.calenderText);
        ShapeableImageView calenderImg = view.findViewById(R.id.calenderImg);

        calenderText.setText(Integer.toString(i+1));
        calenderImg.setImageResource(Integer.parseInt(categoryImages[i]));
        return view;
    }
}

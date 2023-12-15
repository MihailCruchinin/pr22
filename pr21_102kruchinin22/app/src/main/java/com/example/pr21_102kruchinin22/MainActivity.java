package com.example.pr21_102kruchinin22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

class GridAdapter extends BaseAdapter
{
    private Context mContext;
    private Integer mCols, mRows;
    private ArrayList<String> arrPict; // массив картинок
    private String PictureCollection; // Префикс набора картинок
    private Resources mRes; // Ресурсы приложени



    public GridAdapter(Context context, int cols, int rows)
    {
        mContext = context;
        mCols = cols;
        mRows = rows;
        arrPict = new ArrayList<String>();
        // Пока определяем префикс так, позже он будет браться из настроек
        PictureCollection = "animal";
        // Получаем все ресурсы приложения
        mRes = mContext.getResources();

        // Метод заполняющий массив vecPict
        makePictArray ();

    }
    private void makePictArray () {
        // очищаем вектор
        arrPict.clear();
        // добавляем
        for (int i = 0; i < ((mCols * mRows) / 2); i++)
        {
            arrPict.add (PictureCollection + Integer.toString (i));
            arrPict.add (PictureCollection + Integer.toString (i));
        }
        // перемешиваем
        Collections.shuffle(arrPict);
    }

    @Override
    public int getCount() {
        return mCols * mRows;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView view; // выводиться у нас будет картинка

        if (convertView == null)
            view = new ImageView(mContext);
        else
            view = (ImageView)convertView;
        Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());
        view.setImageResource(drawableId);

        return view;
    }
}

public class MainActivity extends AppCompatActivity {
    private GridView mGrid;
    private GridAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrid = (GridView)findViewById(R.id.gvMain);
        mGrid.setNumColumns(6);
        mGrid.setEnabled(true);
        mAdapter = new GridAdapter(this, 6, 6);
        mGrid.setAdapter(mAdapter);
    }
}


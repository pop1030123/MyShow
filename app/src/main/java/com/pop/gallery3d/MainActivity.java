package com.pop.gallery3d;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.act_main)
public class MainActivity extends AppCompatActivity {


    public static final int COLUMN = 4;

    @ViewById(R.id.list)
    RecyclerView mListView;

    ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ImageAdapter(this);
    }

    @AfterViews
    void afterViews() {
        mListView.setLayoutManager(new GridLayoutManager(this, COLUMN));
        mListView.setAdapter(mAdapter);
    }

}

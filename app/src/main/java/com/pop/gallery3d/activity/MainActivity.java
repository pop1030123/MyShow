package com.pop.gallery3d.activity;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pop.gallery3d.R;
import com.pop.gallery3d.adapter.ImageAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.act_main)
public class MainActivity extends Activity {


    public static final int COLUMN = 4;
    public static Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI ;
    private String[] projection = {
            MediaStore.Images.Thumbnails._ID,
            MediaStore.Images.Thumbnails.DATA
    } ;
    private String selection = "" ;
    private String[] selectionArgs = {} ;
    private String order = "" ;

    @ViewById(R.id.list)
    RecyclerView mListView;

    ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @AfterViews
    void afterViews() {

        Cursor cursor = getContentResolver().query(uri ,projection ,selection ,selectionArgs ,order) ;

        mAdapter = new ImageAdapter(this,cursor);

        mListView.setLayoutManager(new GridLayoutManager(this, COLUMN));
        mListView.setAdapter(mAdapter);
    }

}

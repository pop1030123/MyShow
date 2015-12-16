package com.pop.gallery3d.activity;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.pop.gallery3d.R;
import com.pop.gallery3d.adapter.ImageAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.act_main)
public class MainActivity extends Activity {


    public static final int COLUMN = 4;
    public static Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI ;
//    public static Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI ;
    private String[] projection = {
        MediaStore.Images.Thumbnails._ID,
        MediaStore.Images.Thumbnails.DATA
    } ;
    private static final int INDEX_THUMB_ID = 0 ;
    private static final int INDEX_THUMB_DATA = 1 ;
    private String selection = MediaStore.Images.Thumbnails.DATA+"!=?" ;
    private String[] selectionArgs = {""} ;
    private String order = "" ;

    @ViewById(R.id.list)
    RecyclerView mListView;

    ImageAdapter mAdapter;
    private List<String> mImagesPath = new ArrayList<>();

    private Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ImageAdapter(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mAdapter.setImagesPath(mImagesPath);
                mAdapter.notifyDataSetChanged();
            }
        } ;
        loadImages();
    }

    @AfterViews
    void afterViews() {
        mListView.setLayoutManager(new GridLayoutManager(this, COLUMN));
        mListView.setAdapter(mAdapter);
    }

    @Background(id = "data")
    void loadImages(){
        Cursor cursor = getContentResolver().query(uri ,projection ,selection ,selectionArgs ,order) ;
        while(cursor.moveToNext()){
            String path = cursor.getString(INDEX_THUMB_DATA) ;
            if(!TextUtils.isEmpty(path) && new File(path).exists()){
                mImagesPath.add(path) ;
            }
        }
        cursor.close();
        handler.sendEmptyMessage(0) ;
    }
}

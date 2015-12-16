package com.pop.gallery3d.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pop.gallery3d.App;
import com.pop.gallery3d.activity.MainActivity;
import com.pop.gallery3d.R;

/**
 * Created by pengfu on 15/12/13.
 */
public class ImageAdapter extends BaseAbstractRecycleCursorAdapter<ImageAdapter.ViewHolder> {

    private Context mContext ;
    public ImageAdapter(Context context ,Cursor cursor){
        super(context ,cursor);
        mContext = context ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        holder.imageView.setImageURI(ContentUris.withAppendedId(MainActivity.uri, cursor.getLong(0)));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView imageView ;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView)itemView.findViewById(R.id.image) ;
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)imageView.getLayoutParams() ;
            lp.width = lp.height = App.SCREEN_WIDTH / MainActivity.COLUMN ;
        }
    }
}

package com.example.btyisu.galleryproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.btyisu.galleryproject.R;
import com.example.btyisu.galleryproject.data.Content;
import com.example.btyisu.galleryproject.statics.Const;

import java.util.ArrayList;

public class NetRecyclerAdapter extends RecyclerView.Adapter<NetRecyclerViewHolder> {
    private ArrayList<Content> contents = new ArrayList<>();
    private Context context;
    private int layoutId = 0;
    private RequestOptions options;

    /**
     * Live인경우 Thumbnail갱신 때문에 cache를 사용하지 않는다.
     */
    public NetRecyclerAdapter(Context context, int layoutId, Boolean cache){
        this.context = context;
        this.layoutId = layoutId; //LIVE와 VOD를 view를 따로 선언해주기 위해
        if(cache) {
            options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.thumb_default_list)
                    .error(R.drawable.thumb_default_list);
        }else {
            options = new RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.thumb_default_list)
                    .error(R.drawable.thumb_default_list);
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public NetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        NetRecyclerViewHolder vh = new NetRecyclerViewHolder(v, context, contents);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NetRecyclerViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = Const.Size.THUMBNAIL_IMAGE_SIZE;
        params.height = Const.Size.THUMBNAIL_IMAGE_SIZE;
        holder.imageView.setLayoutParams(params);

        //holder.imageView.setImageUrl(contents.get(position).getThumbnail(), MyVolley.getInstance(context).getImageLoader());
        //Volley의 NetworkImageView를 이용한 방법, image placeholder때문에 Glide사용

        String url = contents.get(position).getThumbnail();
        Glide.with(holder.imageView.getContext())
                .load(url)
                .apply(options)
                .into(holder.imageView);
    }

    public void dataAdd(int position, Content content){
        this.contents.add(position,content);
        notifyItemInserted(position);
    }
    public void dataDelete(int position){
        this.contents.remove(position);
    }

}


package com.example.btyisu.galleryproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.btyisu.galleryproject.statics.Const;

public class NetImageDialogFragment extends DialogFragment {
    private ImageView imageView;
    public TextView mTitleText;
    public TextView mUserNickText;
    public TextView mViewCntText;
    private StringBuilder mViewCntString = new StringBuilder("");
    private RequestOptions options = null;

    public NetImageDialogFragment(){}

    public static NetImageDialogFragment getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final NetImageDialogFragment INSTANCE = new NetImageDialogFragment();
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.thumb_default_list)
                .error(R.drawable.thumb_default_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.net_image_dialog_fragment, container, false);
        initView(view);

        Bundle extra = getArguments();
        Glide.with(getActivity())
                .load(extra.getString("url"))
                .apply(options)
                .into(imageView);

        mTitleText.setText(extra.getString("title"));
        mUserNickText.setText(extra.getString("userNick"));
        mViewCntString.append(extra.getString("viewCnt")).append("명 시청중");
        mViewCntText.setText(getContext().getString(R.string.string_live_viewer_count,extra.getString("viewCnt")));
        return view;
    }

    private void initView(View view) {
        imageView = (ImageView) view.findViewById(R.id.netDialogImageView);
        mTitleText = (TextView) view.findViewById(R.id.title_text);
        mUserNickText = (TextView) view.findViewById(R.id.user_nick_text);
        mViewCntText = (TextView) view.findViewById(R.id.view_cnt_text);

        ViewGroup.LayoutParams mImageParams = imageView.getLayoutParams();
        mImageParams.width = Const.Size.IMAGE_DIALOG_SIZE;
        mImageParams.height = Const.Size.IMAGE_DIALOG_SIZE;
        imageView.setLayoutParams(mImageParams);

        ViewGroup.LayoutParams mTitleParams = mTitleText.getLayoutParams();
        mTitleParams.width = Const.Size.IMAGE_DIALOG_SIZE;
        mTitleText.setLayoutParams(mTitleParams);
    }
}

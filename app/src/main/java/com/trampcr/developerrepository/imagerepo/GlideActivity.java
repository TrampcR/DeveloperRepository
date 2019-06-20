package com.trampcr.developerrepository.imagerepo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.trampcr.developerrepository.R;

public class GlideActivity extends AppCompatActivity {
    private String mIvUrl = "https://i03picsos.sogoucdn.com/2ca3d755d3d80eec";
    private ImageView mIvGlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide_view);

        initView();
        loadImage();
    }

    private void loadImage() {
        Glide.with(this).load(mIvUrl).into(mIvGlide);
    }

    private void initView() {
        mIvGlide = (ImageView) findViewById(R.id.iv_glide);
    }
}

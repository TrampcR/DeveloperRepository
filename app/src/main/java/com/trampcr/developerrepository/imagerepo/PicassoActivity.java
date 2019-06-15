package com.trampcr.developerrepository.imagerepo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trampcr.developerrepository.R;

public class PicassoActivity extends AppCompatActivity {
    private String mIvUrl = "https://i03picsos.sogoucdn.com/2ca3d755d3d80eec";
    private ImageView mIvPicasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picasso_view);

        initView();
        loadImage();
    }

    private void loadImage() {
        Picasso.with(this).load(mIvUrl).into(mIvPicasso);
    }

    private void initView() {
        mIvPicasso = (ImageView) findViewById(R.id.iv_picasso);
    }
}

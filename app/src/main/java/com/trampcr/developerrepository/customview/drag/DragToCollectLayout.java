package com.trampcr.developerrepository.customview.drag;

import android.content.ClipData;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trampcr.developerrepository.R;

public class DragToCollectLayout extends RelativeLayout {
    public static final String TAG = DragToCollectLayout.class.getSimpleName();

    private ImageView mAvatarView;
    private ImageView mLogoView;
    private LinearLayout mShowArea;

    private MyDragListener mMyDragListener = new MyDragListener();
    private OnLongClickListener mLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData clipData = ClipData.newPlainText("name", v.getContentDescription());
            return ViewCompat.startDragAndDrop(v, clipData, new DragShadowBuilder(v), null, 0);
        }
    };

    public DragToCollectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mAvatarView = findViewById(R.id.avatar);
        mLogoView = findViewById(R.id.logo);
        mShowArea = findViewById(R.id.show_area);

        mAvatarView.setOnLongClickListener(mLongClickListener);
        mLogoView.setOnLongClickListener(mLongClickListener);
        mShowArea.setOnDragListener(mMyDragListener);
    }

    private class MyDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    if (v instanceof LinearLayout) {
                        LinearLayout linearLayout = (LinearLayout) v;
                        TextView textView = new TextView(v.getContext());
                        textView.setTextSize(16);
                        ClipData clipData = event.getClipData();
                        ClipData.Item item = clipData.getItemAt(0);
                        Log.d(TAG, "onDrag: item = " + item);
                        textView.setText(item.getText());
                        linearLayout.addView(textView);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}

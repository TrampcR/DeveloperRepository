package com.trampcr.developerrepository.reflect;

import android.view.View;
import android.widget.Toast;

/**
 * Created by trampcr on 2019/3/4.
 */

public class HookedClickListenerProxy implements View.OnClickListener{
    private View.OnClickListener mOrigin;

    public HookedClickListenerProxy(View.OnClickListener origin) {
        mOrigin = origin;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "click hook listener", Toast.LENGTH_SHORT).show();
        if (mOrigin != null) {
            mOrigin.onClick(v);
        }
    }
}

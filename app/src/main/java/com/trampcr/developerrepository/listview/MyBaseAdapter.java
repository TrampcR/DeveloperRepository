package com.trampcr.developerrepository.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trampcr.developerrepository.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by trampcr on 2019/5/31.
 */

public class MyBaseAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<HashMap<String, Object>> mListItem = new ArrayList<>();

    public MyBaseAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        if (context == null) {
            return;
        }

        mListItem = listItem;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.base_adapter_item_view, null);
            viewHolder.mTextView1 = convertView.findViewById(R.id.tv_1_base_adapter);
            viewHolder.mTextView2 = convertView.findViewById(R.id.tv_2_base_adapter);
            viewHolder.mButton = convertView.findViewById(R.id.btn_base_adapter);
            viewHolder.mImageView = convertView.findViewById(R.id.iv_base_adapter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView1.setText((String) mListItem.get(position).get("itemTitle"));
        viewHolder.mTextView2.setText((String) mListItem.get(position).get("itemText"));
        viewHolder.mImageView.setImageResource((Integer) mListItem.get(position).get("itemImage"));
        viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "点击了第 " + position + " 个 Item", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        ImageView mImageView;
        Button mButton;
        TextView mTextView1;
        TextView mTextView2;
    }
}

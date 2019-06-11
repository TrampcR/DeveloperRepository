package com.trampcr.developerrepository.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.trampcr.developerrepository.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by trampcr on 2019/5/31.
 */

public class BaseAdapterActivity extends AppCompatActivity {
    public static final String TAG = BaseAdapterActivity.class.getSimpleName();

    private ListView mBaseAdapterListView;
    private MyBaseAdapter mMyBaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_adapter_view);

        mBaseAdapterListView = (ListView) findViewById(R.id.list_base_adapter);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("itemTitle", "第 " + i + " 行");
            map.put("itemText", "这是第 " + i + " 行");
            map.put("itemImage", R.mipmap.ic_launcher);
            listItem.add(map);
        }

        mMyBaseAdapter = new MyBaseAdapter(this, listItem);

        mBaseAdapterListView.setAdapter(mMyBaseAdapter);

        mBaseAdapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BaseAdapterActivity.this, "点击了第 " + position + " 行", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: ");
            }
        });
    }
}

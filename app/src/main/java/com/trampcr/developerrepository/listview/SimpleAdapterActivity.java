package com.trampcr.developerrepository.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.trampcr.developerrepository.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleAdapterActivity extends AppCompatActivity {
    private ListView mSimpleAdapterListView;

    private String[] name = new String[]{"蓝翔", "八维", "石河子"};
    private String[] address = new String[]{"山东", "北京", "新疆"};
    private int[] lowerest_wholesale = new int[]{11, 22, 33};
    private int[] price = new int[]{11, 22, 33};
    private int[] picture = new int[]{
            R.mipmap.ic_launcher,
            R.mipmap.girl,
            R.mipmap.ic_launcher};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_adapter_view);
        mSimpleAdapterListView = (ListView) findViewById(R.id.list_simple_adapter);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name[i]);
            map.put("address", address[i]);
            map.put("lowerest_wholesale", lowerest_wholesale[i]);
            map.put("price", price[i]);
            map.put("picture", picture[i]);
            listItem.add(map);
        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItem,
                R.layout.simple_adapter_item_view,
                new String[]{"name", "address", "lowerest_wholesale", "price", "picture"},
                new int[]{R.id.name, R.id.address, R.id.lowerest_wholesale, R.id.price, R.id
                        .picture});

        mSimpleAdapterListView.setAdapter(simpleAdapter);
    }
}

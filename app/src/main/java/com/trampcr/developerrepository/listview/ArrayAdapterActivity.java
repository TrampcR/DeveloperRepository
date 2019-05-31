package com.trampcr.developerrepository.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.trampcr.developerrepository.R;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_array_adapter_view);

        mListView = (ListView) findViewById(R.id.list_array_adapter);

        List<String> adapterData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            adapterData.add("listItem " + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, adapterData);

        mListView.setAdapter(arrayAdapter);
    }
}

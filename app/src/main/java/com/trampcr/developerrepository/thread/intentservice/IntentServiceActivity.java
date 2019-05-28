package com.trampcr.developerrepository.thread.intentservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trampcr.developerrepository.R;

import java.util.List;

public class IntentServiceActivity extends AppCompatActivity {

    public static final String TASK_1 = "task1";
    public static final String TASK_2 = "task2";
    public static final String TASK_NAME = "taskName";
    public static final String INTENT_SERVICE_ACTION = "com.trampcr.is.demo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intent_service_view);

        Intent intent1 = new Intent(INTENT_SERVICE_ACTION);
        Bundle bundle1 = new Bundle();
        bundle1.putString(TASK_NAME, TASK_1);
        intent1.putExtras(bundle1);
        Intent eintent1 = new Intent(getExplicitIntent(this, intent1));
        startService(eintent1);

        Intent intent2 = new Intent(INTENT_SERVICE_ACTION);
        Bundle bundle2 = new Bundle();
        bundle2.putString(TASK_NAME, TASK_2);
        intent2.putExtras(bundle2);
        Intent eintent2 = new Intent(getExplicitIntent(this, intent2));
        startService(eintent2);

        startService(eintent1);
    }

    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}

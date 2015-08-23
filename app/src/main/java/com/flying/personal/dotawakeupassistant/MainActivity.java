package com.flying.personal.dotawakeupassistant;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class MainActivity extends ActionBarActivity {
    private static Application appInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = getSDpath();
        Log.d(this.getClass().getName(), "Path = " + path);
        ProviderFactory.getInstance().getDataProvider().save(new String[]{path});
    }

    public String getAppPath() {
        return this.getFilesDir().getAbsolutePath() + "/data.json";
    }

    public String getSDpath() {
        try {
            return this.getExternalFilesDir(null).getAbsolutePath() + "/data.json";
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }

        return null;
    }
}

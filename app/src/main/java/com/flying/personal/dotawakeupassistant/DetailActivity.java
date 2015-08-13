package com.flying.personal.dotawakeupassistant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by wangxian on 8/13/2015.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        ((TextView) findViewById(R.id.tvHeroName)).setText(name);
    }
}

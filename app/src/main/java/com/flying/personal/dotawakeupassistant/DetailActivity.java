package com.flying.personal.dotawakeupassistant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.util.HanyuPinyinHelper;

import java.util.List;

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
        String tmp = "";
        List<String> list = HanyuPinyinHelper.getInstance().getHanziT9Index(name);

        for (String t : list) {
            tmp += t + " ";
        }

        ((TextView) findViewById(R.id.tvHeroName)).setText(tmp);

    }
}

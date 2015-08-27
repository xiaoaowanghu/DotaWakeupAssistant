package com.flying.personal.dotawakeupassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

import java.util.List;

public class MainActivity extends ActionBarActivity implements IOnSearch {
    private Hero.PositionType currentPositionType = null;
    private GridLayout mainHeroLayout;
    private LayoutInflater infalter;
    private View.OnClickListener imageClickListener;
    private List<Hero> currentHeroes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infalter = LayoutInflater.from(this);
        setContentView(R.layout.activity_main);
        LoadData();
        String path = getAppPath();
        Log.d(this.getClass().getName(), "Path = " + path);
//        ProviderFactory.getInstance().getDataProvider().save(new String[]{path});
    }

    private void LoadData() {
        mainHeroLayout = (GridLayout) this.findViewById(R.id.gridLayout);
        imageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = v.getTag().toString();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        };

        currentHeroes = ProviderFactory.getInstance().getDataProvider().getAllHeroes();
        ShowHeroes();
    }


    private void ShowHeroes() {
        List<Hero> heroes = currentHeroes;
        mainHeroLayout.removeAllViews();
        IDataProvider dataProvider = ProviderFactory.getInstance().getDataProvider();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;
        int colCount = 4;
        int marginDP = 5; //dp
        int marginPX = Utility.getInstance().dip2px(this, marginDP);
        int picWidthPX = (int) ((screenWidthPX - marginPX * (colCount + 1)) / colCount * 1.0);

        for (int i = 0; i < dataProvider.getTotalHeroCount(); i++) {
            final Hero h = heroes.get(i);
            LinearLayout searchItemRoot = (LinearLayout) this.infalter.inflate(R.layout.search_item, null);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(picWidthPX, picWidthPX);
            param.setMargins(0, 0, 0, 0);
            searchItemRoot.setLayoutParams(param);
            searchItemRoot.setOrientation(LinearLayout.VERTICAL);
            searchItemRoot.setPadding(0, 0, 0, 0);

//            ImageView ivHeroPic = (ImageView) searchItemRoot.getChildAt(0);
//            ivHeroPic.setMaxHeight(picWidthPX);
//            ivHeroPic.setMaxWidth(picWidthPX);
//            ivHeroPic.setTag(h.getName());
//            ivHeroPic.setImageBitmap(Utility.getInstance().createImageFromAsset(this, h.getPortraitPath()));
//            ivHeroPic.setOnClickListener(imageClickListener);

            RoundImageView ivHeroPic = (RoundImageView) searchItemRoot.getChildAt(0);
            ivHeroPic.getLayoutParams().width = picWidthPX;
            ivHeroPic.getLayoutParams().height = picWidthPX;
            ivHeroPic.setTag(h.getName());
            ivHeroPic.setFilePath(h.getPortraitPath());
            ivHeroPic.invalidate();
            ivHeroPic.setOnClickListener(imageClickListener);

            TextView tvHeroDisplayName = (TextView) searchItemRoot.getChildAt(1);
            tvHeroDisplayName.setText(h.getName());

            GridLayout.Spec rowSpec = GridLayout.spec(i / colCount);
            GridLayout.Spec columnSpec = GridLayout.spec(i % colCount);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setMargins(marginPX, marginPX, 0, 0);
            params.setGravity(Gravity.CENTER);
            mainHeroLayout.addView(searchItemRoot, params);
        }
    }

    @Override
    public void OnSearch(String text) {
        currentHeroes = ProviderFactory.getInstance().getDataProvider()
                .getMatchedHeroes(text, currentPositionType);

        ShowHeroes();
    }

    @Override
    public void OnPositionTypeChange(Hero.PositionType position) {
        currentPositionType = position;
        SearchEditTextFragment f = (SearchEditTextFragment) this.getFragmentManager().findFragmentById(R.id.fragment_search);
        f.ClearText();
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

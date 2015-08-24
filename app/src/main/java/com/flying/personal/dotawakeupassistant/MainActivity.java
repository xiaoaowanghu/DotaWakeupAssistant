package com.flying.personal.dotawakeupassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;

import java.util.List;

public class MainActivity extends ActionBarActivity implements IOnSearch {
    private Hero.PositionType currentPositionType = null;
    private GridLayout mainHeroLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadData();
        String path = getSDpath();
        Log.d(this.getClass().getName(), "Path = " + path);
        ProviderFactory.getInstance().getDataProvider().save(new String[]{path});
    }

    private void LoadData() {
        mainHeroLayout = (GridLayout) this.findViewById(R.id.gridLayout);
        List<Hero> heroes = ProviderFactory.getInstance().getDataProvider().getAllHeroes();
        ShowHeroes(heroes);
    }

    private void ShowHeroes(List<Hero> heroes) {
        IDataProvider dataProvider = ProviderFactory.getInstance().getDataProvider();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;
        int colCount = 5;
        int marginDP = 16;
        int marginPX = Utility.getInstance().dip2px(this, marginDP);
        int picWidthPX = (int) ((screenWidthPX - marginPX * (colCount + 1)) / colCount * 1.0 - 0.5f);

        for (int i = 0; i < dataProvider.getTotalHeroCount(); i++) {
            final Hero h = heroes.get(i);
            ImageView imageView = new ImageView(this);
            GridLayout.LayoutParams imgLayout = new GridLayout.LayoutParams();
            imgLayout.width = picWidthPX;
            imgLayout.height = GridLayout.LayoutParams.WRAP_CONTENT;
            imgLayout.setMargins(0, 0, 0, 0);
            imageView.setLayoutParams(imgLayout);
            imageView.setMaxWidth(picWidthPX);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(R.drawable.ic_launcher);
            imageView.setPadding(0, 0, 0, 0);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", h.getName());
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, DetailActivity.class);
                    startActivity(intent);
                }
            });

            GridLayout.Spec rowSpec = GridLayout.spec(i / colCount);
            GridLayout.Spec columnSpec = GridLayout.spec(i % colCount);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            params.setMargins(marginPX, marginPX, 0, 0);
            mainHeroLayout.addView(imageView, params);
        }
    }

    @Override
    public void OnSearch(String text) {
        List<Hero> heroes = ProviderFactory.getInstance().getDataProvider()
                .getMatchedHeroes(text, currentPositionType);

        ShowHeroes(heroes);
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

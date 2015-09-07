package com.flying.personal.dotawakeupassistant;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ProviderFactory.getInstance().initFactory(new String[]{getAppPath()});
        init();
    }


    private void init() {
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
                MainActivity.this.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        };

        currentHeroes = ProviderFactory.getInstance().getDataProvider().getAllHeroes();
        showHeroes(null);
        mainHeroLayout.requestFocus();
    }

    private void showHeroes(Map<Hero, String> matchedIndex) {
        List<Hero> heroes = currentHeroes;
        mainHeroLayout.removeAllViews();
        IDataProvider dataProvider = ProviderFactory.getInstance().getDataProvider();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;
        int colCount = 5;
        int marginDP = 5; //dp
        int marginPX = Utility.getInstance().dip2px(this, marginDP);
        int picWidthPX = (int) ((screenWidthPX - marginPX * (colCount + 1)) / colCount * 1.0);

        for (int i = 0; i < heroes.size(); i++) {
            final Hero h = heroes.get(i);
            LinearLayout searchItemRoot = (LinearLayout) this.infalter.inflate(R.layout.search_item, null);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(picWidthPX, picWidthPX);
            param.setMargins(0, 0, 0, 0);
            searchItemRoot.setLayoutParams(param);
            searchItemRoot.setOrientation(LinearLayout.VERTICAL);
            searchItemRoot.setPadding(0, 0, 0, 0);

            RoundImageView ivHeroPic = (RoundImageView) searchItemRoot.getChildAt(0);
            ivHeroPic.getLayoutParams().width = picWidthPX;
            ivHeroPic.getLayoutParams().height = picWidthPX;
            ivHeroPic.setTag(h.getName());
            ivHeroPic.setFilePath(getResources().getString(R.string.dir_hero_path) + "/" + h.getPortraitPath());
            ivHeroPic.invalidate();
            ivHeroPic.setOnClickListener(imageClickListener);

            TextView tvHeroDisplayName = (TextView) searchItemRoot.getChildAt(1);

            if (currentSearchString == null || currentSearchString.length() == 0) {
                tvHeroDisplayName.setText(h.getName());
            } else {
                String index = matchedIndex.get(h);

                if (index == null) {
                    tvHeroDisplayName.setText(h.getName());
                } else {
                    int startChangeColorIndex = index.indexOf(currentSearchString);
                    SpannableStringBuilder span = new SpannableStringBuilder(index);
                    span.setSpan(new ForegroundColorSpan(Color.RED), startChangeColorIndex,
                            startChangeColorIndex + currentSearchString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    tvHeroDisplayName.setText(span);
                }
            }

            GridLayout.Spec rowSpec = GridLayout.spec(i / colCount);
            GridLayout.Spec columnSpec = GridLayout.spec(i % colCount);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setMargins(marginPX, marginPX, 0, 0);
            params.setGravity(Gravity.CENTER);
            mainHeroLayout.addView(searchItemRoot, params);
        }
    }

    private String currentSearchString;

    @Override
    public void onSearch(String text) {
        HashMap<Hero, String> matchedIndex = new HashMap<Hero, String>(30);
        currentHeroes = ProviderFactory.getInstance().getDataProvider()
                .getMatchedHeroes(text, currentPositionType, matchedIndex);
        currentSearchString = text;
        showHeroes(matchedIndex);
    }

    @Override
    public void onPositionTypeChange(Hero.PositionType position) {
        //Clear search string
        SearchEditTextFragment f = (SearchEditTextFragment) this.getFragmentManager().findFragmentById(R.id.fragment_search);
        f.clearText();
        currentSearchString = null;
        currentPositionType = position;
        currentHeroes = ProviderFactory.getInstance().getDataProvider().getHeroesByPosition(position);
        showHeroes(null);
    }

    public String getAppPath() {
        return this.getFilesDir().getAbsolutePath() + "/data.json";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.miActionAbout) {
            View view = LayoutInflater.from(this).inflate(R.layout.about_dialog, null);// 自定义布局
            final AlertDialog dialog = new AlertDialog.Builder(this).create();

            TextView tv = (TextView) view.findViewById(R.id.tvVersion);
            String versionName = "N/A";
            PackageInfo info = null;

            try {
                info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
                versionName = info.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(this.getClass().getName(), Log.getStackTraceString(e));
            }
            tv.setText(getResources().getString(R.string.app_name) + "v" + versionName);

            TextView tv1 = (TextView) view.findViewById(R.id.tvInformation);
            tv1.setText(R.string.about_content);

            Button closeButton = (Button) view.findViewById(R.id.btnCloseAbout);
            closeButton.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
            dialog.getWindow().setContentView(view);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

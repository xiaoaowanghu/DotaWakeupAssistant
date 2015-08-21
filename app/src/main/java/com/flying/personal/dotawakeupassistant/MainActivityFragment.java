package com.flying.personal.dotawakeupassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.flying.personal.dotawakeupassistant.impl.DataProviderImplByMock;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.Utility;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;
import com.flying.personal.dotawakeupassistant.view.QuickSearchEditText;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private DataProviderImplByMock dataProvider;
    private GridLayout mainHeroLayout;

    public MainActivityFragment() {
        dataProvider = new DataProviderImplByMock();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentRoot = inflater.inflate(R.layout.fragment_main, container, false);
        LoadData(fragmentRoot);
        return fragmentRoot;
    }

    private void LoadData(View fragmentRoot) {
        QuickSearchEditText etSearch = (QuickSearchEditText) fragmentRoot.findViewById(R.id.search_editText);
        initSearchEditText(etSearch);

        GridLayout gridLayout = (GridLayout) fragmentRoot.findViewById(R.id.gridLayout);
        mainHeroLayout = gridLayout;
        List<Hero> heroes = dataProvider.getAllHeroes();
        ShowHeroes(heroes);
    }

    private void ShowHeroes(List<Hero> heroes) {
        DisplayMetrics dm = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;
        int colCount = 4;
        int marginDP = 16;
        int marginPX = Utility.getInstance().dip2px(this.getActivity(), marginDP);
        int picWidthPX = (int) ((screenWidthPX - marginPX * (colCount + 1)) / colCount * 1.0 - 0.5f);

        for (int i = 0; i < dataProvider.getTotalHeroCount(); i++) {
            final Hero h = heroes.get(i);
            ImageView imageView = new ImageView(this.getActivity());
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
            final Activity mainActivity = getActivity();

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", h.getName());
                    intent.putExtras(bundle);
                    intent.setClass(mainActivity, DetailActivity.class);
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

    private void initSearchEditText(QuickSearchEditText etSearch) {
        etSearch.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }

            @Override
            protected char[] getAcceptedChars() {
                char[] data = getResources().getString(R.string.only_can_input).toCharArray();
                return data;
            }
        });

        etSearch.setSearchListener(new IOnSearch() {
            @Override
            public void OnSearch(String text) {
                List<Hero> heroes;
                text = text.trim();
                if (text.length() == 0)
                    heroes = dataProvider.getAllHeroes();
                else
                    heroes = dataProvider.getMatchedHeroes(text);

                ShowHeroes(heroes);
            }
        });
    }
}

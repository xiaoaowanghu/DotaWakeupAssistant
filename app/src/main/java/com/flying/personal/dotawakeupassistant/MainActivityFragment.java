package com.flying.personal.dotawakeupassistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flying.personal.dotawakeupassistant.model.Hero;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private DataProviderImplByTest dataProvider;

    public MainActivityFragment() {
        dataProvider = new DataProviderImplByTest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentRoot = inflater.inflate(R.layout.fragment_main, container, false);
        LoadData(fragmentRoot);
        return fragmentRoot;
    }

    private void LoadData(View fragmentRoot) {
        GridLayout gridLayout = (GridLayout) fragmentRoot.findViewById(R.id.gridLayout);
        List<Hero> heros = dataProvider.getAllHeros();
        DisplayMetrics dm = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;    //得到宽度
        int colCount = 4;
        int margin = 50;
        int picWidthPX = (int) ((screenWidthPX - margin * colCount) / colCount * 1.0 - 0.5f);

        for (int i = 0; i < dataProvider.getTotalHeroCount(); i++) {
            ImageView imageView = new ImageView(this.getActivity());
            ViewGroup.LayoutParams img2Dimens = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(img2Dimens);
            imageView.setMaxWidth(picWidthPX);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setAdjustViewBounds(true);

            GridLayout.Spec rowSpec = GridLayout.spec(i / colCount);
            GridLayout.Spec columnSpec = GridLayout.spec(i % colCount);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.CENTER);
            params.setMargins(margin, margin, 0, 0);
            gridLayout.addView(imageView, params);
        }
    }
}

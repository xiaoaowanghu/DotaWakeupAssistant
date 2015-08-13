package com.flying.personal.dotawakeupassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.flying.personal.dotawakeupassistant.model.Hero;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private DataProviderImplByMock dataProvider;

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
        GridLayout gridLayout = (GridLayout) fragmentRoot.findViewById(R.id.gridLayout);
        DisplayMetrics dm = new DisplayMetrics();
        List<Hero> heroes = dataProvider.getAllHeroes();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;
        int colCount = 4;
        int marginDP = 16;
        int marginPX = UnitUtility.getInstance().dip2px(this.getActivity(), marginDP);
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
                    bundle.putString("name", "tinyphp");
                    intent.putExtras(bundle);
                    intent.putExtra("name", h.getName());
                    intent.setClass(mainActivity, DetailActivity.class);
                    startActivity(intent);
                }
            });

            GridLayout.Spec rowSpec = GridLayout.spec(i / colCount);
            GridLayout.Spec columnSpec = GridLayout.spec(i % colCount);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            params.setMargins(marginPX, marginPX, 0, 0);
            gridLayout.addView(imageView, params);
        }
    }
}

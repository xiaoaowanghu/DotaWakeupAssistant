package com.flying.personal.dotawakeupassistant;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
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
        Display display = this.getActivity().getWindowManager().getDefaultDisplay(); //Activity#getWindowManager()
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int picWidth = 72;
        int colCount = screenWidth / (picWidth + 10);
//        int rowCount = (int) Math.ceil(dataProvider.getTotalHeroCount() * 1.0 / colCount);
//        GridLayout.LayoutParams params = (GridLayout.LayoutParams) gridLayout.getLayoutParams();
//        params.columnSpec = GridLayout.spec(colCount);
//        params.rowSpec = GridLayout.spec(rowCount);

        for (int i = 0; i < dataProvider.getTotalHeroCount(); i++) {
            ImageView imageView =  new ImageView(this.getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(36, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(R.drawable.ic_launcher);

            GridLayout.Spec rowSpec = GridLayout.spec(i / colCount);
            GridLayout.Spec columnSpec = GridLayout.spec(i % colCount);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.CENTER);
            gridLayout.addView(imageView, params);
        }
    }
}

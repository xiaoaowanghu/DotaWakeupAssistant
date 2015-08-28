package com.flying.personal.dotawakeupassistant;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;

/**
 * Created by wangxian on 8/24/2015.
 */
public class BottomNavigationFragment extends Fragment {
    private IOnSearch searchListener;
    private View selectedBackground;
    private View.OnClickListener clickListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout fragmentRoot = (LinearLayout) inflater.inflate(R.layout.fragment_bottom_navbar, container, false);
        initListener(fragmentRoot);
        return fragmentRoot;
    }

    private void initListener(LinearLayout rootLayout) {
        int i = 0;
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedBackground != null)
                    selectedBackground.setBackgroundColor(Color.TRANSPARENT);

                View ll = v;
                ll.setBackgroundColor(getResources().getColor(R.color.bottom_selected_bg));
                selectedBackground = ll;

                if (searchListener != null) {
                    Log.d("flying.click", getResources().getResourceName(ll.getId()));

                    if (ll.getId() == R.id.l1AllPosition)
                        searchListener.onPositionTypeChange(null);
                    else if (ll.getId() == R.id.llBackPosition)
                        searchListener.onPositionTypeChange(Hero.PositionType.Back);
                    else if (ll.getId() == R.id.llFrontPosition)
                        searchListener.onPositionTypeChange(Hero.PositionType.Front);
                    else if (ll.getId() == R.id.llMiddlePosition)
                        searchListener.onPositionTypeChange(Hero.PositionType.Middle);
                    else
                        throw new IllegalArgumentException("The click trigger is not correct");
                }
            }
        };

        for (; i < rootLayout.getChildCount(); i++) {
            final LinearLayout ll = (LinearLayout) rootLayout.getChildAt(i);
            ll.setOnClickListener(clickListener);
            //选中第一个
            if (i == 0) {
                selectedBackground = ll;
                ll.setBackgroundColor(getResources().getColor(R.color.bottom_selected_bg));
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof IOnSearch) {
            searchListener = (IOnSearch) activity;
        } else {
            throw new IllegalArgumentException("The activity must implement IOnSearch");
        }
    }
}

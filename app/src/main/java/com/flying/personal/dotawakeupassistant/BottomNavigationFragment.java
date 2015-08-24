package com.flying.personal.dotawakeupassistant;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangxian on 8/24/2015.
 */
public class BottomNavigationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentRoot = inflater.inflate(R.layout.fragment_bottom_navbar, container, false);
        return fragmentRoot;
    }
}

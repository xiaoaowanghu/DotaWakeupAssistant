package com.flying.personal.dotawakeupassistant;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;
import com.flying.personal.dotawakeupassistant.view.QuickSearchEditText;

import java.util.List;

/**
 * Created by wangxian on 8/24/2015.
 */
public class SearchEditTextFragment extends Fragment {
    private IOnSearch listener;
    private QuickSearchEditText etSearch;
    private String currentSearchString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentRoot = inflater.inflate(R.layout.fragment_search_edittext, container, false);
        initViews(fragmentRoot);
        return fragmentRoot;
    }

    public void ClearText() {
        etSearch.ClearSearchText(true);
    }

    private void initViews(View root) {
        etSearch = (QuickSearchEditText) root.findViewById(R.id.search_editText);
        etSearch.setHint(R.string.search_hint);
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

                if (text.equalsIgnoreCase(currentSearchString))
                    return;

                if (listener != null)
                    listener.OnSearch(text);

                currentSearchString = text;
            }

            @Override
            public void OnPositionTypeChange(Hero.PositionType position) {
                //No use
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (IOnSearch) activity;
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
    }
}

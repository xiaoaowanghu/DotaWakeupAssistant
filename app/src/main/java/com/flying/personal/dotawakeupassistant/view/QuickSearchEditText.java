package com.flying.personal.dotawakeupassistant.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.flying.personal.dotawakeupassistant.R;

/**
 * Created by wangxian on 8/21/2015.
 */
public class QuickSearchEditText extends EditText {

    private long delayMilliseconds = 1000;  //delay time to search
    private IOnSearch searchListener;
    private Handler handler;
    private Runnable notifyToSearch;

    public QuickSearchEditText(Context context) {
        super(context);
        init();
    }

    public QuickSearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        delayMilliseconds = typedArray.getDimensionPixelSize(R.styleable.QuickSearchEditText_delayToSearch,
                1000);
        init();
    }

    private void init() {
        handler = new Handler();

        notifyToSearch = new Runnable() {
            @Override
            public void run() {
                if (searchListener != null)
                    searchListener.OnSearch(QuickSearchEditText.this.getText().toString());
            }
        };

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handler.removeCallbacks(notifyToSearch);
                handler.postDelayed(notifyToSearch, delayMilliseconds);
            }
        });
    }


    public void setSearchListener(IOnSearch listener) {
        this.searchListener = listener;
    }
}

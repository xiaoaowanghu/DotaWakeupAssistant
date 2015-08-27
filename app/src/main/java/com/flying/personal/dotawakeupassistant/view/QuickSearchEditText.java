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
 * Do not search while typing. Will delay some time to search
 * Created by wangxian on 8/21/2015.
 * All rights reserved.
 * Do not Copy/Edit/Use this class without permission.
 */
public class QuickSearchEditText extends EditText {

    private long delayMilliseconds = 1000;  //delay time to search
    private IOnSearch searchListener;
    private Handler handler;
    private Runnable notifyToSearch;
    private boolean isInputting;

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
        typedArray.recycle();
    }

    public void ClearSearchText(boolean trigerSearchEvent) {
        if (trigerSearchEvent) {
            setText("");
        } else {
            IOnSearch tmp = this.searchListener;
            this.searchListener = null;
            setText("");
            this.searchListener = tmp;
        }
    }

    private void init() {
        if (isInEditMode())
            return;

        isInputting = false;
        handler = new Handler();

        notifyToSearch = new Runnable() {
            @Override
            public void run() {
                if (isInputting)
                    return;

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
                //To be more safe to not to trigger it while inputting. The removeCallbacks doesn't work im time.
                isInputting = true;

                if (searchListener != null) {
                    handler.removeCallbacks(notifyToSearch);
                    handler.postDelayed(notifyToSearch, delayMilliseconds);
                }

                isInputting = false;
            }
        });
    }


    public void setSearchListener(IOnSearch listener) {
        this.searchListener = listener;
    }
}

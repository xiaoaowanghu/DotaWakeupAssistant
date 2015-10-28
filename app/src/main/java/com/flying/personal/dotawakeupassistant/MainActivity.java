package com.flying.personal.dotawakeupassistant;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.util.CommonUtility;
import com.flying.personal.dotawakeupassistant.view.IOnSearch;
import com.flying.personal.dotawakeupassistant.view.RoundImageView;

import org.apache.http.NameValuePair;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity implements IOnSearch {
    private Hero.PositionType currentPositionType = null;
    private GridLayout mainHeroLayout;
    private LayoutInflater infalter;
    private View.OnClickListener imageClickListener;
    private List<Hero> currentHeroes;
    private Updater updater;
    private Handler handler;
    private boolean firstLoad = true;
    private int colCount = 5;

    private String getUpdateRecordPath() {
        return getAppPath() + File.separator + "update";
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInMainThread();
        initInBackendThread();
    }

    private void reLoadData() {
        SearchEditTextFragment f = (SearchEditTextFragment) this.getFragmentManager().findFragmentById(R.id.fragment_search);
        currentSearchString = null;
        f.clearText();

        BottomNavigationFragment f2 = (BottomNavigationFragment) this.getFragmentManager().findFragmentById(R.id.fragment_nav);
        f2.selectWithOutTriggerEvent(0);

        currentHeroes = ProviderFactory.getInstance().getDataProvider().getAllHeroes();
        showHeroes(null);
    }

    private void initInBackendThread() {
        AsyncTask loader = new AsyncTask() {
            private int marginPX;

            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    ProviderFactory.getInstance().initFactory(new String[]{getAppPath()});
                    marginPX = CommonUtility.dip2px(MainActivity.this, 5);
                    currentHeroes = ProviderFactory.getInstance().getDataProvider().getAllHeroes();
                } catch (Exception e) {
                    Log.e("flying.AsyncTask.loader", Log.getStackTraceString(e));
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                for (int i = 0; i < currentHeroes.size(); i++) {
                    loadHeroes(currentHeroes.get(i), getHeroPicWidthPX(), null, marginPX, i / colCount, i % colCount);
                }

                ((ScrollView) mainHeroLayout.getParent()).requestLayout();
            }
        };

        loader.execute(new String[]{getAppPath()});

        //Update
//        updater = new Updater();
//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (MainActivity.this.isFinishing())
//                    return;
//
//                String updateRecordFilePath = getUpdateRecordPath();
//                File updateRecordFile = null;
//                FileInputStream fis = null;
//                BufferedReader br = null;
//                Date lastUpdateTime = null;
//                try {
//                    updateRecordFile = new File(updateRecordFilePath);
//                    if (updateRecordFile.exists()) {
//                        fis = new FileInputStream(updateRecordFile);
//                        br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
//                        String timestamp = br.readLine();
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        lastUpdateTime = formatter.parse(timestamp);
//                    }
//                } catch (ParseException parseEx) {
//                    Log.e(MainActivity.class.getName(), "The update time is invalid");
//
//                    if (updateRecordFile != null && updateRecordFile.exists())
//                        updateRecordFile.delete();
//
//                } catch (Exception e) {
//                    Log.e(MainActivity.class.getName(), Log.getStackTraceString(e));
//                } finally {
//                    try {
//                        if (br != null)
//                            br.close();
//
//                        if (fis != null)
//                            fis.close();
//                    } catch (IOException e1) {
//                        Log.e(MainActivity.class.getName(), Log.getStackTraceString(e1));
//                    }
//                }
//
//                if (lastUpdateTime != null
//                        && System.currentTimeMillis() - lastUpdateTime.getTime() < 24 * 3600 * 1000) {
//                    return;
//                }
//
//                List<NameValuePair> params = new ArrayList<>(5);
//                Date currentDate = new Date();
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                NameValuePair n1 = new PairData("current_version", ProviderFactory.getInstance().getDataProvider().getDataVersion());
//                NameValuePair n2 = new PairData("timestamp", formatter.format(currentDate));
//                NameValuePair n3 = new PairData("token", generateToken(currentDate));
//                params.add(n1);
//                params.add(n2);
//                params.add(n3);
//                updater.doUpdate(MainActivity.this, ProviderFactory.getInstance().getDataProvider().getUpdateURL(), params,
//                        new Updater.ICallBack() {
//                            @Override
//                            public void NotifyResult(int code, String information) {
//                                if (code == 0) {
//                                    CommonUtility.showNormalDialog(MainActivity.this, "更新", "更新成功，页面将重新加载");
//                                    ProviderFactory.getInstance().getDataProvider().initInMainThread(new String[]{getAppPath() + File.separator + "data.json"});
//                                    reLoadData();
//                                }
//                            }
//
//                            @Override
//                            public void NotifyProgress(int percentValue) {
//                                //Do Nothing;
//                            }
//                        });
//            }
//        }, 10000);

    }

    private void initInMainThread() {
        infalter = LayoutInflater.from(this);
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
    }

    private void showHeroes(Map<Hero, String> matchedIndex) {
        List<Hero> heroes = currentHeroes;
        mainHeroLayout.removeAllViews();
        IDataProvider dataProvider = ProviderFactory.getInstance().getDataProvider();
        int marginPX = CommonUtility.dip2px(this, 5);

        for (int i = 0; i < heroes.size(); i++) {
            final Hero h = heroes.get(i);
            String matchedText = null;

            if (currentSearchString != null && currentSearchString.length() > 0 && matchedIndex != null) {
                matchedText = matchedIndex.get(h);
            }

            loadHeroes(h, getHeroPicWidthPX(), matchedText, marginPX, i / colCount, i % colCount);
        }
    }

    private int heroPicWidth = -1;

    private int getHeroPicWidthPX() {
        if (heroPicWidth > 0)
            return heroPicWidth;

        int marginDP = 5; //dp
        int marginPX = CommonUtility.dip2px(this, marginDP);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPX = dm.widthPixels;
        heroPicWidth = (int) ((screenWidthPX - marginPX * (colCount + 1)) / colCount * 1.0);
        return heroPicWidth;
    }

    private void loadHeroes(Hero h, int picWidthPX, String matchedText, int marginPX, int rowIndex, int colIndex) {
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

        if (h.isBuiltInData())
            ivHeroPic.setLoadSource(RoundImageView.LoadSource.Asset);
        else
            ivHeroPic.setLoadSource(RoundImageView.LoadSource.AppDataDir);

        ivHeroPic.setFilePath(CommonUtility.getActuallResourcePath(this, h, h.getPortraitPath()));
        ivHeroPic.invalidate();
        ivHeroPic.setOnClickListener(imageClickListener);

        TextView tvHeroDisplayName = (TextView) searchItemRoot.getChildAt(1);

        if (currentSearchString == null || currentSearchString.length() == 0 || matchedText == null) {
            tvHeroDisplayName.setText(h.getName());
        } else {
            int startChangeColorIndex = matchedText.indexOf(currentSearchString);
            SpannableStringBuilder span = new SpannableStringBuilder(matchedText);
            span.setSpan(new ForegroundColorSpan(Color.RED), startChangeColorIndex,
                    startChangeColorIndex + currentSearchString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            tvHeroDisplayName.setText(span);
        }

        GridLayout.Spec rowSpec = GridLayout.spec(rowIndex);
        GridLayout.Spec columnSpec = GridLayout.spec(colIndex);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        params.setMargins(marginPX, marginPX, 0, 0);
        params.setGravity(Gravity.CENTER);
        mainHeroLayout.addView(searchItemRoot, params);
    }

    private String currentSearchString;

    @Override
    public void onSearch(String text) {
        if (this.isFinishing())
            return;

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
        return this.getFilesDir().getAbsolutePath();
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
            tv.setText(getResources().getString(R.string.app_name) + "v" + versionName + " 数据版本" + ProviderFactory.getInstance().getDataProvider().getDataVersion());

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
        } else if (id == R.id.miUpdate) {

            final ProgressDialog pbarDialog = new ProgressDialog(this);
            pbarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pbarDialog.setMessage("Updating...");
            pbarDialog.setCancelable(false);
            pbarDialog.setMax(100);
            pbarDialog.show();

            Updater.ICallBack callBack = new Updater.ICallBack() {
                @Override
                public void NotifyResult(int code, String information) {
                    pbarDialog.dismiss();

                    if (code == 0) {
                        ProviderFactory.getInstance().getDataProvider().init(new String[]{getAppPath() + File.separator + "data.json"});
                        reLoadData();
                    } else {
                        CommonUtility.showNormalDialog(MainActivity.this, "更新失败", information + "\n" + "错误码:" + code);
                    }
                }

                @Override
                public void NotifyProgress(int percentValue) {
                    pbarDialog.setProgress(percentValue);
                }
            };

            if (updater != null && updater.getStatus() == AsyncTask.Status.RUNNING) {
                updater.setCallBack(callBack);
            } else {
                updater = new Updater();
                List<NameValuePair> params = new ArrayList<>(5);
                Date currentDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                NameValuePair n1 = new PairData("current_version", ProviderFactory.getInstance().getDataProvider().getDataVersion());
                NameValuePair n2 = new PairData("timestamp", formatter.format(currentDate));
                NameValuePair n3 = new PairData("token", generateToken(currentDate));
                params.add(n1);
                params.add(n2);
                params.add(n3);
                updater.doUpdate(MainActivity.this, ProviderFactory.getInstance().getDataProvider().getUpdateURL(), params, callBack);
            }

            return true;
        } else if (id == R.id.miSerializeData) {
            ProviderFactory.getInstance().getDataProvider().save(null);
        } else if (id == R.id.miRestData) {
            File f = new File(getAppPath() + File.separator + "data.json");

            try {
                if (f.exists())
                    f.delete();
            } catch (Exception e) {
                Log.e(this.getClass().getName(), Log.getStackTraceString(e));
            }

            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class PairData implements NameValuePair {
        private String name;
        private String value;

        public PairData(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getValue() {
            return value;
        }

    }

    private String generateToken(Date date) {
        long base = 13556531555l;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(base / c.get(Calendar.MINUTE));
    }
}

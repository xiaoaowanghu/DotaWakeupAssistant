package com.flying.personal.dotawakeupassistant;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.flying.personal.dotawakeupassistant.util.FileUtility;

import org.apache.http.NameValuePair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxian on 9/14/2015.
 */
public class Updater extends AsyncTask {
    private class ResultData {
        public int returnCode;
        public String information;
    }

    public interface ICallBack {
        void NotifyResult(int code, String information);

        void NotifyProgress(int percentValue);
    }

    public final static int NoUpdate = -100;
    private int timeoutValue = 30 * 1000;
    private Context context;
    private String urlString;
    private List<NameValuePair> postParams;
    private ICallBack callBack;

    public void doUpdate(Context context, String urlString,
                         List<NameValuePair> postParams, ICallBack callBack) {
        if (this.getStatus() == Status.RUNNING) {
            if (callBack != null)
                callBack.NotifyResult(-10, "Task is running");

            return;
        }

        this.context = context;
        this.urlString = urlString;
        this.postParams = postParams;
        this.callBack = callBack;
        this.execute();
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        ResultData result = new ResultData();
        String tmpDirPath = context.getFilesDir().getAbsolutePath() + "/tmp";
        String downloadFileName = "tmpDataFile";
        String downloadFileFullPath = tmpDirPath + File.separator + downloadFileName;
        String updateRecordFilePath = context.getFilesDir().getAbsolutePath() + File.separator + "update";
        int progress = 0;
        try {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo == null || !mNetworkInfo.isAvailable()) {
                    result.returnCode = -5;
                    result.information = "No Network connection";
                    return result;
                }
            }
            progress = 5;
            publishProgress(progress);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeoutValue);
            conn.setReadTimeout(timeoutValue);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            byte[] dataBytes = null;

            if (postParams != null) {
                StringBuilder sb = new StringBuilder(1024);
                for (int i = 0; i < postParams.size(); i++) {
                    NameValuePair p = postParams.get(i);

                    if (i != 0) {
                        sb.append("&");
                    }

                    sb.append(p.getName())
                            .append("=")
                            .append(URLEncoder.encode(p.getValue(), "UTF-8"));
                }

                dataBytes = sb.toString().getBytes("UTF-8");
                conn.setRequestProperty("Content-Length", String.valueOf(dataBytes.length));
            }

            if (dataBytes != null) {
                OutputStream os = conn.getOutputStream();
                os.write(dataBytes);
                os.flush();
            }

            File tmpDir = new File(tmpDirPath);
            FileUtility.deleteAllFiles(tmpDir);
            tmpDir.mkdir();

            progress = 10;
            publishProgress(progress);

            if (conn.getResponseCode() == 200) {
                InputStream downloadIS = conn.getInputStream();
                FileOutputStream downloadFOS = new FileOutputStream(downloadFileFullPath);
                int len = 0;
                byte buffer[] = new byte[4096];

                while ((len = downloadIS.read(buffer)) != -1) {
                    downloadFOS.write(buffer, 0, len);

                    if (progress < 75) {
                        progress += 5;
                        publishProgress(progress);
                    }
                }

                downloadIS.close();
                downloadFOS.close();

                progress = 80;
                publishProgress(progress);
                //analyse file
                File f = new File(downloadFileFullPath);

                if (f.exists() && f.length() > 6) {
                    String extractDir = tmpDirPath + File.separator + "extract__";
                    FileUtility.unZip(downloadFileFullPath, extractDir);
                    FileUtility.copyFolder(extractDir, context.getFilesDir().getAbsolutePath());
                    result.returnCode = 0;
                    progress = 95;
                    publishProgress(progress);
                    //save update time
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        FileOutputStream updateRecordFOS = new FileOutputStream(updateRecordFilePath);
                        updateRecordFOS.write(formatter.format(new Date()).getBytes("UTF-8"));
                        updateRecordFOS.flush();
                        updateRecordFOS.close();
                    } catch (IOException e) {
                        Log.e(Updater.this.getClass().getName(), Log.getStackTraceString(e));
                    }
                } else {
                    result.returnCode = NoUpdate;
                }
            } else {
                result.returnCode = conn.getResponseCode();
                result.information = "Connection Error";
            }
        } catch (Exception e) {
            Log.e(Updater.this.getClass().getName(), Log.getStackTraceString(e));

            result.returnCode = -2;
            result.information = e.getMessage();
        } finally {
            File tmpDir = new File(tmpDirPath);
            FileUtility.deleteAllFiles(tmpDir);
            progress = 100;
            publishProgress(progress);
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (callBack != null) {
            ResultData r = (ResultData) o;
            callBack.NotifyResult(r.returnCode, r.information);
        }
    }

    /**
     * Runs on the UI thread after {@link #publishProgress} is invoked.
     * The specified values are the values passed to {@link #publishProgress}.
     *
     * @param values The values indicating progress.
     * @see #publishProgress
     * @see #doInBackground
     */
    @Override
    protected void onProgressUpdate(Object[] values) {
        if (callBack != null) {
            int value = (int) values[0];
            callBack.NotifyProgress(value);
        }
    }
}

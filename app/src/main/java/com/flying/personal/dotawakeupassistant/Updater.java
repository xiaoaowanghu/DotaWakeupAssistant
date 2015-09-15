package com.flying.personal.dotawakeupassistant;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.flying.personal.dotawakeupassistant.util.FileUtility;

import org.apache.http.NameValuePair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

    @Override
    protected Object doInBackground(Object[] params) {
        ResultData result = new ResultData();
        try {
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

            String tmpDirPath = context.getFilesDir().getAbsolutePath() + "/tmp";
            File tmpDir = new File(tmpDirPath);
            FileUtility.deleteAllFiles(tmpDir);
            tmpDir.mkdir();

            String downloadFileName = "tmpDataFile";
            String downloadFileFullPath = tmpDirPath + File.separator + downloadFileName;

            if (conn.getResponseCode() == 200) {
                InputStream downloadIS = conn.getInputStream();
                FileOutputStream downloadFOS = new FileOutputStream(downloadFileFullPath);
                int len = 0;
                byte buffer[] = new byte[4096];

                while ((len = downloadIS.read(buffer)) != -1) {
                    downloadFOS.write(buffer, 0, len);
                }

                downloadIS.close();
                downloadFOS.close();
                //analyse file
                File f = new File(downloadFileFullPath);

                if (f.exists() && f.length() > 2) {
                    String extractDir = tmpDirPath + File.separator + "extract__";
                    FileUtility.unZip(downloadFileFullPath, extractDir);
                    FileUtility.copyFolder(extractDir, context.getFilesDir().getAbsolutePath());
                    result.returnCode = 0;
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
        }

        return result;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param o The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Object o) {
        if (callBack != null) {
            ResultData r = (ResultData) o;
            callBack.NotifyResult(r.returnCode, r.information);
        }
    }
}

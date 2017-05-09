package util;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by OKMAN on 2017/5/8.
 */

public class HttpUtil {
    public static void getPost(final String url,final String parm,final HttpListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader br = null;
                StringBuffer sb = new StringBuffer("");
                try {
                    urlConnection = (HttpURLConnection) new URL(url).openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("Content-Type","application/json");
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(6000);
                    urlConnection.setReadTimeout(6000);
                    urlConnection.setUseCaches(false);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
                    bw.write(parm);
                    bw.flush();
                    bw.close();
                    int state = urlConnection.getResponseCode();
                    if (state == 200) {
                        br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                        String str = "";
                        while ((str=br.readLine())!=null){
                            sb.append(str);
                        }
                        Log.e("HttpUtil", sb.toString());
                        listener.onSuccess(sb.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFail(e);
                }finally {
                    if (br != null) {
                        try {
                            br.close();
                            br = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                        urlConnection = null;
                    }
                }
            }
        }).start();
    };
    public static void getPost(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader br = null;
                StringBuffer sb = new StringBuffer("");
                try {
                    urlConnection = (HttpURLConnection) new URL(url).openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("Content-Type","application/json");
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(6000);
                    urlConnection.setReadTimeout(6000);
                    urlConnection.setUseCaches(false);
                    int state = urlConnection.getResponseCode();
                    if (state == 200) {
                        br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                        String str = "";
                        while ((str=br.readLine())!=null){
                            sb.append(str);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (br != null) {
                        try {
                            br.close();
                            br = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                        urlConnection = null;
                    }
                }
            }
        }).start();
    };
}

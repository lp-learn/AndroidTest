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
    public static String getPost(String url,String parm){

        StringBuffer sb = new StringBuffer("");
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(6000);
            urlConnection.setReadTimeout(6000);
            urlConnection.setUseCaches(false);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            bw.write(parm);
            bw.close();
            int state = urlConnection.getResponseCode();
            if (state == 200) {
                BufferedReader br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                String str = "";
                while ((str=br.readLine())!=null){
                    sb.append(str);
                }
                Log.e("test",sb.toString());
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    };
    public static String getPost(final String url){
        final StringBuffer sb = new StringBuffer("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("Content-Type","application/json");
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(6000);
                    urlConnection.setReadTimeout(6000);
                    urlConnection.setUseCaches(false);
                    int state = urlConnection.getResponseCode();
                    if (state == 200) {
                        BufferedReader br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                        String str = "";
                        while ((str=br.readLine())!=""){
                            sb.append(str);
                        }
                        br.close();
                        Log.e("test",str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return sb.toString();
    };
}

package com.bignews.bignews.GongnengOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPUtil {

    public  static void sendHttputilRequest(final String address,final HTTPCallBackListener listener){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    HttpURLConnection httpURLConnection = null;

                    try {
                        URL url=new URL(address);
                        httpURLConnection= (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setReadTimeout(10000);
                        InputStream in=httpURLConnection.getInputStream();
                        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                        StringBuilder sb=new StringBuilder();
                        String line="";
                        while((line=reader.readLine())!=null){
                            sb.append(line);
                        }
                        if (listener!=null){
                            listener.onFinish(sb.toString());
                        }

                    } catch (IOException e) {
                        if (listener!=null){
                            listener.onError(e);
                        }
                    }finally {
                        if(httpURLConnection!=null){
                            httpURLConnection.disconnect();
                        }
                    }


                }
            }).start();

    }

}
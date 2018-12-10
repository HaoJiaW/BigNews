package com.bignews.bignews.Gongnengthree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bignews.bignews.GongnengOne.OKHTTPUtil;
import com.bignews.bignews.R;
import com.bumptech.glide.Glide;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ImageTest extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagetest_main);

        imageView= (ImageView) findViewById(R.id.imagetest_image);
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(ImageTest.this);
        String imageData= sp.getString("image",null);
        if (imageData!=null){
            Glide.with(ImageTest.this).load(imageData).into(imageView);

        }else {
            loadData();

        }


    }


    public void loadData() {

        OKHTTPUtil.sendOkHttpRequest("https://y.gtimg.cn/music/photo_new/T002R300x300M000002RyzDh1e4Z1b.jpg",
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String imagedata = "https://y.gtimg.cn/music/photo_new/T002R300x300M000002RyzDh1e4Z1b.jpg";
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ImageTest.this)
                                .edit();
                        editor.putString("image", imagedata);
                        editor.apply();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(ImageTest.this).load(imagedata).into(imageView);
                            }
                        });
                    }
                });

    }
}
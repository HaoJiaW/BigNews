package com.bignews.bignews;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {

    MediaPlayer player;

    private String singerInfo,musicNameInfo,imageInfo,musicUrl,mpF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
        Intent intent=getIntent();
        singerInfo =intent.getStringExtra("singer");
        musicNameInfo=intent.getStringExtra("musicName");
        imageInfo=intent.getStringExtra("imageUrl");
        musicUrl=intent.getStringExtra("musicUrl");
        mpF=intent.getStringExtra("mpF");
        initView();
    }
    public void initView(){
        ImageView imageView= (ImageView) findViewById(R.id.player_image);
        Glide.with(PlayerActivity.this).load(imageInfo).into(imageView);
        TextView name= (TextView) findViewById(R.id.player_name);
        TextView singer= (TextView) findViewById(R.id.player_singer);
        name.setText(musicNameInfo);
        singer.setText(singerInfo);

        initMediaPlayer();
    }
    public void initMediaPlayer(){
        player=new MediaPlayer();
        Uri uri=Uri.parse(mpF);
        try {
            player.setDataSource(PlayerActivity.this,uri);
            player.prepare();
            setListener();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setListener(){
        ImageButton play= (ImageButton) findViewById(R.id.bt_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!player.isPlaying()){
                    player.start();
                }
            }
        });
        ImageButton pause= (ImageButton) findViewById(R.id.bt_pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()){
                    player.pause();
                }
            }
        });
    }
}
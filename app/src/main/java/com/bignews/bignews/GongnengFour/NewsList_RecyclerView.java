package com.bignews.bignews.GongnengFour;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.bignews.bignews.GongnengOne.OKHTTPUtil;
import com.bignews.bignews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsList_RecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<NewsBean> mlist=new ArrayList<>();
    private String address="http://v.juhe.cn/toutiao/index?type=top&key=e0262fe03af3745734f5bd7309981515";
    private String url,title,imageurl;
    private NewsBean newsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newstitle_recyclerview_main);
        //设置ToolBar
        Toolbar toolbar= (Toolbar) findViewById(R.id.news_re_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置下拉刷新
        final SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) findViewById(R.id.news_re_refresh);
        refreshLayout.setColorSchemeColors(R.color.light_blue);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();


                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                requestData();
                                refreshLayout.setRefreshing(false);

                            }
                        });
                    }
                }).start();


            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.news_recyclerview);


        requestData();





    }

    public void requestData(){
        OKHTTPUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(responseData);
                    JSONObject jsonObject1=jsonObject.getJSONObject("result");
                    JSONArray jsonArray=jsonObject1.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject2=jsonArray.getJSONObject(i);
                        title=jsonObject2.getString("title");
                        url=jsonObject2.getString("url");
                        imageurl=jsonObject2.getString("thumbnail_pic_s");
                        url=jsonObject2.getString("url");
                        newsBean=new NewsBean();
                        newsBean.setImageUrl(imageurl);
                        newsBean.setTitle(title);
                        newsBean.setUrl(url);
                        mlist.add(newsBean);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setLayoutManager(new LinearLayoutManager(NewsList_RecyclerView.this));
                                recyclerView.setAdapter(new ReAdapter(mlist,NewsList_RecyclerView.this));
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
package com.bignews.bignews.GongnengTwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignews.bignews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewTitleList extends AppCompatActivity {

    String title;
    ListView listView;
    ArrayList<String> mlist=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtitlelist_main);

        loadData();
        initView();
    }

    public void initView(){
        listView= (ListView) findViewById(R.id.newtitlelist_listview);



       /* for (int i=0;i<100;i++){

            mlist.add("数据"+i);
        }*/

    }

    public  void loadData(){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url("http://v.juhe.cn/toutiao/index?type=top&key=e0262fe03af3745734f5bd7309981515")
                        .build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseData(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    public void parseData(String canshu){
        try {
            JSONObject jsonObject=new JSONObject(canshu);
            JSONObject jsonObject1=jsonObject.getJSONObject("result");
            JSONArray jsonArray=jsonObject1.getJSONArray("data");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                title=jsonObject2.getString("title");
                  mlist.add(title);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new ArrayAdapter<>(NewTitleList.this,android.R.layout.simple_list_item_1
                                ,mlist);

                        listView.setAdapter(adapter);

                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
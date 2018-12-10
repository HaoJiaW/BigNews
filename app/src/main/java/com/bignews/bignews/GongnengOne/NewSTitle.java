package com.bignews.bignews.GongnengOne;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignews.bignews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewSTitle extends AppCompatActivity{

    private String url="http://v.juhe.cn/toutiao/index?type=top&key=e0262fe03af3745734f5bd7309981515";
    private ListView listView;

    private ArrayList<String> mlist=new ArrayList<>();
    private ArrayList<String> murl=new ArrayList<>();
    private ArrayAdapter adapter;
    String title;
    private String urlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_layout_test);

       listView= (ListView) findViewById(R.id.newstitle_lv);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(NewSTitle.this,ContentClass.class);
                intent.putExtra("url",murl.get(position));
                startActivity(intent);

            }
        });


        OKHTTPUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        title = jsonObject2.getString("title");
                        urlData = jsonObject2.getString("url");
                        mlist.add(title);
                        murl.add(urlData);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adapter = new ArrayAdapter(NewSTitle.this, android.R.layout.simple_list_item_1, mlist);
                                listView.setAdapter(adapter);

                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}



package com.bignews.bignews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignews.bignews.GongnengFour.NewsList_RecyclerView;
import com.bignews.bignews.GongnengOne.NewSTitle;
import com.bignews.bignews.GongnengOne.OKHTTPUtil;
import com.bignews.bignews.GongnengTwo.NewTitleList;
import com.bignews.bignews.Gongnengthree.ImageTest;
import com.bignews.bignews.MainGongneng.MainAdapter;
import com.bignews.bignews.MainGongneng.MusicInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private  DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private String address1,address2,address;
    private EditText editText;
    private Button button;
    private String musicName,singer,imageUrl,musicUrl,mpFour;
    private MusicInfoBean musicInfoBean;
    private List<MusicInfoBean> mlist=new ArrayList<>();
    MainAdapter adapter;
    HandleMessage handleMessage;
    int count=1;
    Toast mtoast;
    String fileOptions,mpF;
    List<String> urlList=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        handleMessage=new HandleMessage(MainActivity.this,"");

    }
    public void initView(){
        //定义ToolBar
        Toolbar toolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        //定义DrawerLayout的出场方式
       drawerLayout= (DrawerLayout) findViewById(R.id.main_drawerLayout);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_mini);
        }


        //定义RecyclerView
        recyclerView= (RecyclerView) findViewById(R.id.main_RecyclerView);
        editText= (EditText) findViewById(R.id.main_et);
        address1="http://api01.idataapi.cn:8000/music/qqmusic?apikey=LQt6EJuRDH1v1rfAwzA" +
                "BnHFRiZdML4btwk4NThieFE5L106JgxsSuxDbiRnkLhML&kw=";

        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String chushiContent=sp.getString("content", null);
        if (chushiContent!=null) {
            editText.setText(chushiContent);
        }

        button= (Button) findViewById(R.id.main_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                address2 = editText.getText().toString();
                address = address1 + address2;

                if (address2 != null && !"".equals(address2)) {
                    handleMessage.handler.sendEmptyMessage(1);
                    mlist.clear();
                    requestData();
                    //存储搜索记录
                    SharedPreferences.Editor spE = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                    spE.putString("content", address2);
                    spE.apply();

                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("搜索内容不能为空!");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    }).show();
                }
            }
        });





        //定义导航视图(NavigationView)的菜单监听
          NavigationView navigationView= (NavigationView) findViewById(R.id.main_nav);
        //初始化选中的选项(暂时不需要)
       // navigationView.setCheckedItem(R.id.gongneng1);
        //设置对应菜单项的监听,实现对应功能
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.gongneng1:
                       // Toast.makeText(MainActivity.this,"点击了功能1",Toast.LENGTH_SHORT).show();
                        handleMessage.message="该功能暂未开放";
                        handleMessage.handler.sendEmptyMessage(3);
                        /*Intent intent=new Intent(MainActivity.this,NewSTitle.class);
                        startActivity(intent);
                        finish();*/
                        break;
                    case R.id.gongneng2:
                        handleMessage.message="该功能暂未开放";
                        handleMessage.handler.sendEmptyMessage(3);
                       /* Intent intent1=new Intent(MainActivity.this,NewTitleList.class);
                        startActivity(intent1);
                        finish();*/
                        break;
                    case R.id.gongneng3:
                        handleMessage.message="该功能暂未开放";
                        handleMessage.handler.sendEmptyMessage(3);
                        /*Intent intent2=new Intent(MainActivity.this,ImageTest.class);
                        startActivity(intent2);*/

                        break;
                    case R.id.gongneng4:
                        Intent intent3=new Intent(MainActivity.this, NewsList_RecyclerView.class);
                        startActivity(intent3);
                        break;
                    case R.id.gongneng5:
                        handleMessage.message="该功能暂未开放";
                        handleMessage.handler.sendEmptyMessage(3);
//                        Toast.makeText(MainActivity.this,"点击了功能5",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_back:

                        count++;
                        if (count==3){
                            finish();
                            count=1;
                        }else {
                            /*Toast.makeText(MainActivity.this,"再按一次退出程序!",Toast.LENGTH_SHORT).show();*/
                            mtoast=Toast.makeText(MainActivity.this,"再次点击退出程序!",Toast.LENGTH_SHORT);
                            mtoast.show();
                        }



                    default:
                        break;
                }
                return true;
            }
        });


    }


    public void requestData(){
        OKHTTPUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                if(e instanceof SocketTimeoutException){
                    handleMessage.handler.sendEmptyMessage(2);
                    handleMessage.message="连接超时";
                    handleMessage.handler.sendEmptyMessage(3);
                }
                if(e instanceof ConnectException){
                    handleMessage.handler.sendEmptyMessage(2);
                    handleMessage.message="连接异常";
                    handleMessage.handler.sendEmptyMessage(3);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(responseData);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        musicName=jsonObject1.getString("title");
                        imageUrl=jsonObject1.getString("coverUrl");
                        musicUrl=jsonObject1.getString("url");
                        Log.i("音乐名",musicName);
                        JSONArray jsonArray1=jsonObject1.getJSONArray("singers");
                        JSONObject jsonObject2=jsonArray1.getJSONObject(0);
                        String UN_singer=jsonObject2.getString("name");
                        singer=new org.json.JSONTokener(UN_singer).nextValue().toString();
                        JSONArray jsonArray2=jsonObject1.getJSONArray("fileOptions");
                        for (int j=0;j<jsonArray2.length();j++){
                            JSONObject jsonObject3=jsonArray2.getJSONObject(j);
                            mpF=jsonObject3.getString("url");
                            if (mpF!=null){
                                mpFour=mpF;
                            }
                        }


                        musicInfoBean=new MusicInfoBean(singer,musicName,imageUrl,musicUrl,mpFour);
                        mlist.add(musicInfoBean);

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            handleMessage.handler.sendEmptyMessage(2);
                            adapter=new MainAdapter(mlist,MainActivity.this);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                count++;
                if (count==3){
                    finish();
                }else {
                mtoast=Toast.makeText(MainActivity.this,"再次点击退出程序!",Toast.LENGTH_SHORT);
                mtoast.show();}

        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mtoast.cancel();
    }
}

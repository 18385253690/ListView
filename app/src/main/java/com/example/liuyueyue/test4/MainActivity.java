package com.example.liuyueyue.test4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //以下是ListView的两种方式页面实现
        listView = (ListView) findViewById(R.id.listView);
        /*1.新建一个数组适配器
        * （ArrayAdapter上下文，当前ListView加载每一个列表项所相对应的布局文件,数据源）
        * 2.适配器加载数据源*/
        String[] arr_data = {"慕课网1","慕课网2","慕课网3","慕课网4"};
        //ArrayAdapter;数组适配器方法
        arr_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_data);
        /*3.视图（ListView）加载适配器，第一种方式，没有图片*/
        //listView.setAdapter(arr_adapter);


        /*SimpleAdapter简单适配器方法
        * 1.context:上下文
        * 2.data:数据源（List<? extends Map<String,?>>data）一个Map所组成的ListView列表；
        * 每一个Map都会去对应ListView列表中的一行
        * 每一个Map（键—值对）中键必须包含所在form中所制定的键
        * 3.resource:；列表项的布局文件ID
        * 4.from：Map中的键名
        * 5.to
        * 绑定数据视图中的Id,与form成对应关系*/
        dataList = new ArrayList<Map<String,Object>>();
        simp_adapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"pic","text"},new int []{R.id.pic,R.id.text});
       /*视图（ListView）加载适配器，第二种方式实现，有图片*/
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }
    private List<Map<String,Object>> getData(){
        for(int i = 0;i<20;i++){
            Map<String,Object>map = new HashMap<String,Object>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","慕课网"+i);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("Main","用户在手指离开屏幕之前由于惯性继续向前滑动");
                Map<String,Object>map = new HashMap<String,Object>();
                map.put("pic",R.mipmap.ic_launcher);
                map.put("text","增加项");
                dataList.add(map);
                simp_adapter.notifyDataSetChanged();

                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main","视图停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main","手指没有离开屏幕，视图正在滑动");
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = listView.getItemAtPosition(position)+" ";
        Toast.makeText(this,"position="+position+";text="+text,Toast.LENGTH_SHORT).show();

    }
    //以上是ListView的两种方式页面实现




}

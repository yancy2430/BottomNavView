package com.tdeado.bottomnavview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tdeado.bottomnav.MenuItem;
import com.tdeado.bottomnav.BottomMenuView;
import com.tdeado.bottomnav.IconMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomMenuView bmv_list;
    private IconMenuView imv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmv_list = findViewById(R.id.bmv_list);
        bmv_list.setBottomItem(getData());
        bmv_list.setBottomItemOnClickListener(new BottomMenuView.BottomItemOnClickListener() {
            @Override
            public void bottomItemOnClick(View view, int i, MenuItem item) {
                Toast.makeText(getApplicationContext(),"点击了第"+i+"个",Toast.LENGTH_SHORT).show();
            }
        });
        bmv_list.setShowIndex(0);


        imv_list = findViewById(R.id.imv_list);
        imv_list.setIconItem(getIconData());
        imv_list.setIconItemOnClickListener(new IconMenuView.IconItemOnClickListener() {
            @Override
            public void iconItemOnClick(View view, int i, MenuItem item) {
                Toast.makeText(getApplicationContext(),"点击了第"+i+"个",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public List<MenuItem> getData(){
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("首页",R.mipmap.icon_function_tab));
        items.add(new MenuItem("信息",R.mipmap.icon_home_tab));
        items.add(new MenuItem("应用",R.mipmap.icon_my_tab));
        items.add(new MenuItem("我的",R.mipmap.icon_home_tab));
        return items;
    }

    public List<MenuItem> getIconData(){
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("门户中心",R.mipmap.a));
        items.add(new MenuItem("热门排行",R.mipmap.b));
        items.add(new MenuItem("更多应用",R.mipmap.c));
        items.add(new MenuItem("个人中心",R.mipmap.d));
        return items;
    }


}

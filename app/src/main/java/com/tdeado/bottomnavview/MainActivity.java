package com.tdeado.bottomnavview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tdeado.bottomnav.BottomItem;
import com.tdeado.bottomnav.BottomMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomMenuView bmv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmv_list = (BottomMenuView) findViewById(R.id.bmv_list);
        bmv_list.setBottomItem(getData());
        bmv_list.setBottomItemOnClickListener(new BottomMenuView.BottomItemOnClickListener() {
            @Override
            public void bottomItemOnClick(View view, int i, BottomItem item) {
                Toast.makeText(getApplicationContext(),"点击了第"+i+"个",Toast.LENGTH_SHORT).show();
            }
        });
        bmv_list.setShowIndex(0);
    }
    public List<BottomItem> getData(){
        List<BottomItem> items = new ArrayList<>();
        items.add(new BottomItem("首页",R.mipmap.icon_function_tab));
        items.add(new BottomItem("信息",R.mipmap.icon_home_tab));
        items.add(new BottomItem("应用",R.mipmap.icon_my_tab));
        items.add(new BottomItem("我的",R.mipmap.icon_home_tab));
        return items;
    }
}

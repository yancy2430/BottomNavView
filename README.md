# BottomNavView
自定义底部导航栏

##演示
![](https://github.com/yancy2430/BottomNavView/blob/master/demo.gif)  

##Gradle:

    compile 'com.tdeado:bottomnav:1.0.0'

##Maven

    <dependency>
      <groupId>com.tdeado</groupId>
      <artifactId>bottomnav</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
    </dependency>
    
    
    
##示例<br>
在XML文件中添加如下代码

    <com.tdeado.bottomnav.BottomMenuView
            android:id="@+id/bottom_group"
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:paddingTop="2dp"
            android:layout_height="50dp">
    </com.tdeado.bottomnav.BottomMenuView>
    
<br>

##设置数据

    BottomMenuView bottom_group = (BottomMenuView) findViewById(R.id.bottom_group);
    bottom_group.setBottomItem(List<BottomItem> bottomItems)
    
##设置监听器

            bottom_group.setBottomItemOnClickListener(new BottomMenuView.BottomItemOnClickListener() {
                @Override
                public void bottomItemOnClick(View view,int i, BottomItem item) {
                    switch (i){
                        case 0:
                            fragmentUtil.showFragment("home");
                            break;
                        case 1:
                            fragmentUtil.showFragment("function");
                            break;
                        case 2:
                            fragmentUtil.showFragment("my");
                            break;
                    }
                }
            });
    bottom_group.setShowIndex(0);
<br>
##方法说明<br>

    setTextSize(float textSize) 设置文字大小
    setImgPadding(int imgPadding)设置图片内边距
    setImgColor(int imgColor) 设置图标选择颜色
    setImgDefaultColor(int imgDefaultColor) 设置图标默认颜色
    setBottomItem(List<BottomItem> bottomItems) 设置数据

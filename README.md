# Fragment的使用

* [生命周期](#生命周期)
* [使用](#使用)
    * [静态使用fragment](#静态使用fragment)
    * [动态使用fragment](#动态使用fragment)
    * [fragment相关类](#fragment相关类)
    * [传递参数](#传递参数)
* [相关地址](#相关地址)


## 生命周期
created    onAttach() onCreate() onCreateView() onActivityCreated()  
started    onStart()  
resumed    onResume()  
paused     onPause()  
stopped    onStop()  
destroyed  onDestroyView() onDestroy() onDetach()

onAttach() 当fragment和activity发生关联的时候调用  
onCreateView() 创建该fragment的视图  
onActivityCreated() 当activity的onCreate方法返回时调用     
onDestroyView() 和onCreateView对应，当该fragment视图移除时调用   
onDetach() 和onAttach()对应，当fragment和activity关联被取消时调用


## 使用

### 静态使用fragment
这是使用Fragment最简单的一种方式，把Fragment当成普通的控件，直接写在Activity的布局文件中

1. 继承Fragment，重写onCreateView决定Fragment的布局
2. 在Activity中声明此Fragment，就当和普通的View一样

例如activity中添加标题fragment和内容fragment
titleFragment
```
public class StaticTitleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static_title, container, false);
    }
}
```
titleFragment对应的xml
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="标题"
        android:layout_centerInParent="true"/>

</RelativeLayout>
```
contentFragment
```
public class StaticContentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static_content, container, false);
    }
}
```
contentFragment对应的xml
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="内容"
        android:layout_centerInParent="true"/>
</RelativeLayout>
```
activity
```
public class StaticFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_fragment);
    }
}
```
activity对应的xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/fragment_title"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:name="com.dawn.basefragment.StaticTitleFragment"/>

    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="#000"/>

    <fragment
        android:id="@+id/fragment_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:name="com.dawn.basefragment.StaticContentFragment"/>

</LinearLayout>
```
activity的xml中需要注意的是：fragment控件是小写，并且必须添加id让此控件唯一。通过android:name添加对应的fragment


### 动态使用fragment
这个需要再activity中对fragment进行添加，删除，更新
activity
```
public class HomeFragmentActivity extends AppCompatActivity implements View.OnClickListener{
    Fragment fragment01;
    Fragment fragment02;
    Fragment fragment03;
    Fragment fragment04;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);
        fragment01 = new ContentFragment01();
        setFragment(R.id.fragment_content, fragment01);
        findViewById(R.id.tv_01).setOnClickListener(this);
        findViewById(R.id.tv_02).setOnClickListener(this);
        findViewById(R.id.tv_03).setOnClickListener(this);
        findViewById(R.id.tv_04).setOnClickListener(this);
    }
    private void setFragment(@IdRes int idRes, Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(idRes, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_01:
                if(fragment01 == null)
                    fragment01 = new ContentFragment01();
                setFragment(R.id.fragment_content, fragment01);
                break;
            case R.id.tv_02:
                if(fragment02 == null)
                    fragment02 = new ContentFragment02();
                setFragment(R.id.fragment_content, fragment02);
                break;
            case R.id.tv_03:
                if(fragment03 == null)
                    fragment03 = new ContentFragment03();
                setFragment(R.id.fragment_content, fragment03);
                break;
            case R.id.tv_04:
                if(fragment04 == null)
                    fragment04 = new ContentFragment04();
                setFragment(R.id.fragment_content, fragment04);
                break;

        }
    }
}
```
activity对应的xml
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent">
    <include
        android:id="@+id/layout_bottom_bar"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        layout="@layout/view_bottom_bar"
        android:layout_alignParentBottom="true"/>

    <FrameLayout
        android:id="@+id/fragment_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@id/fragment_content"/>

</RelativeLayout>
```
需要注意的是空间中调用的是FrameLayout，是大写的。


### fragment相关类
Fragment常用的三个类
android.app.Fragment  
android.app.FragmentManager  
android.app.FragmentTransaction  

* 获取FragmentManager的方式  
getFragmentManager() // v4中，getSupportFragmentManager
* 获取FragmentTransaction方式  
FragmentTransaction transaction = fm.beginTransaction();//开启一个事务

transaction.add() 往Activity中添加一个Fragment  
transaction.remove() 从Activity中移除一个Fragment，如果被移除的Fragment没有添加到回退栈（回退栈后面会详细说），这个Fragment实例将会被销毁。  
transaction.replace() 使用另一个Fragment替换当前的，实际上就是remove()然后add()的合体  
transaction.hide() 隐藏当前的Fragment，仅仅是设为不可见，并不会销毁  
transaction.show() 显示之前隐藏的Fragment  
transaction.detach() 会将view从UI中移除,和remove()不同,此时fragment的状态依然由FragmentManager维护。  
transaction.attach() 重建view视图，附加到UI上并显示。


### 传递参数
通过setArguments方法传递bundle  
通过getArguments来接收bundle


## 相关地址

[http://blog.csdn.net/lmj623565791/article/details/37970961](#http://blog.csdn.net/lmj623565791/article/details/37970961)

[http://blog.csdn.net/lmj623565791/article/details/37992017](#http://blog.csdn.net/lmj623565791/article/details/37992017)

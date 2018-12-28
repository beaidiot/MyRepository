package com.wit.dylan.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wit.dylan.entity.Users;
import com.wit.dylan.fragment.BookCheckFragment;
import com.wit.dylan.fragment.BorrowInquiryFragment;
import com.wit.dylan.fragment.InfoFragment;
import com.wit.dylan.fragment.MainContentFragment;
import com.wit.dylan.fragment.ModifyPassWordFragment;
import com.wit.dylan.fragment.PersonDataFragment;
import com.wit.dylan.fragment.SelfRoomSeatOrder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MainContentFragment mainContentFragment;
    private PersonDataFragment personDataFragment;
    private BookCheckFragment bookCheckFragment;
    private ModifyPassWordFragment modifyPassWordFragment;
    private SelfRoomSeatOrder selfRoomSeatOrderFragment;
    private BorrowInquiryFragment borrowInquiryFragment;
    private InfoFragment infoFragment;


    private Toolbar toolbar;
    private TextView text_name;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Users users = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);

        toolbar = (Toolbar) findViewById(R.id.SelfRoom_toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View heanderLayout = navigationView.inflateHeaderView(R.layout.app_nav_header_main);
        navigationView.setNavigationItemSelectedListener(this);

        TextView text_name = (TextView) heanderLayout.findViewById(R.id.text_username);

        //加载初始显示界面
        setDefaultFragment();

        //获取实体User
        Intent intent = getIntent();
        users = (Users) intent.getSerializableExtra("user");

        //加载导航栏显示信息
        text_name.setText(users.getUserName());

    }

    //设置初试显示的fragment；
    public void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mainContentFragment = new MainContentFragment();
        //设置首页；
        transaction.replace(R.id.id_fragment, mainContentFragment);
        transaction.commit();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", users);
        switch (item.getItemId()) {
            //个人信息界面
            case R.id.nav_personData:
                if (personDataFragment == null) {
                    personDataFragment = new PersonDataFragment();
                }
                if (!personDataFragment.isVisible()) {
                    personDataFragment.setArguments(bundle);
                    transaction.replace(R.id.id_fragment, personDataFragment);
                }
                break;
            //书籍查询界面
            case R.id.nav_bookCheck:
                if (bookCheckFragment == null) {
                    bookCheckFragment = new BookCheckFragment();
                }
                if (!bookCheckFragment.isVisible())
                    transaction.replace(R.id.id_fragment, bookCheckFragment);
                break;
            //借阅查询界面
            case R.id.nav_lendCheck:
                if (borrowInquiryFragment == null) {
                    borrowInquiryFragment = new BorrowInquiryFragment();
                    borrowInquiryFragment.setArguments(bundle);
                }
                    transaction.replace(R.id.id_fragment, borrowInquiryFragment);
                break;
            //自习室位置预约界面
            case R.id.nav_selfRoomSeatbook:
                if (selfRoomSeatOrderFragment == null) {
                    selfRoomSeatOrderFragment = new SelfRoomSeatOrder();
                }
                if (!selfRoomSeatOrderFragment.isVisible()) {
                    transaction.replace(R.id.id_fragment, selfRoomSeatOrderFragment);
                }
                break;
            //修改密码界面
            case R.id.nav_ModifyPwd:
                if (modifyPassWordFragment == null) {
                    modifyPassWordFragment = new ModifyPassWordFragment();
                }
                if (!modifyPassWordFragment.isVisible()) {
                    modifyPassWordFragment.setArguments(bundle);
                    transaction.replace(R.id.id_fragment, modifyPassWordFragment);
                }
                break;
            //关于
            case R.id.nav_about:
                if (infoFragment == null) {
                    infoFragment = new InfoFragment();
                }
                transaction.replace(R.id.id_fragment, infoFragment);

                break;
            //注销
            case R.id.nav_exit:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;

            default:
                break;
        }

        toolbar.setTitle(item.getTitle());
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //设置返回键不可使用；
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setTitle("主页");
    }
}

package com.qgstudio.anywork.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.common.DialogManagerActivity;
import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.dialog.BaseDialog;
import com.qgstudio.anywork.enter.EnterActivity;
import com.qgstudio.anywork.feedback.FeedbackActivity;
import com.qgstudio.anywork.networkcenter.WorkBuilder;
import com.qgstudio.anywork.notice.NoticeActivity;
import com.qgstudio.anywork.notice.data.Notice;
import com.qgstudio.anywork.notice.data.OnlineCount;
import com.qgstudio.anywork.user.UserActivity;
import com.qgstudio.anywork.user.UserApi;
import com.qgstudio.anywork.utils.GlideUtil;
import com.qgstudio.anywork.utils.SessionMaintainUtil;
import com.qgstudio.anywork.utils.ToastUtil;
import com.qgstudio.anywork.websocket.ThreadMode;
import com.qgstudio.anywork.websocket.WS;
import com.qgstudio.anywork.websocket.WebSocketHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends DialogManagerActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "HomeActivity";
    public static final String ACTION = TAG + "$Receiver";//广播action
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private FragmentManager mFragmentManager;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerBroadcast();
        WebSocketHolder.getDefault().register(this);
        String baseUrl = RetrofitClient.RETROFIT_CLIENT.getRetrofit().baseUrl().toString();
        // WebSocketHolder.getDefault().connect(baseUrl+"websocket/"+App.getInstance().getUser().getUserId());
        WebSocketHolder.getDefault().connect("ws://121.40.165.18:8800");
    }

    @Override
    protected void onDestroy() {
        unregisterBroadcast();
        super.onDestroy();
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        mReceiver = new Receiver();
        registerReceiver(mReceiver, intentFilter);
    }

    private void unregisterBroadcast() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    private void initView() {
        ButterKnife.bind(this);
        //设置底部导航栏的颜色
        bottomNavigationView.setItemIconTintList(null);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.frame, HomeFragment.newInstance())
                .commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_searching: {
                SearchingActivity.start(this);
                return true;
            }
            default: {
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_join: {//我的班级
                break;
            }
            case R.id.nav_inform: {//个人信息
                Intent intent = new Intent(this, UserActivity.class);
//                startActivityForResult(intent, 0);
                break;
            }
            case R.id.nav_feedback: {//意见反馈
//                // TODO: 2017/8/16 意见反馈
//                ToastUtil.showToast("此功能暂未开放！");
                Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_exit: {//退出登入
                BaseDialog.Builder builder = new BaseDialog.Builder(this);
                BaseDialog baseDialog = builder.cancelTouchout(false)
                        .title("提示")
                        .content("确定要退出当前账号吗？")
                        .setNegativeListener("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //暂停定时任务
                                SessionMaintainUtil.stop();
                                //跳转切换帐号
                                logout();
                                EnterActivity.start(HomeActivity.this, EnterActivity.FLAG_SWITCH_USER);
                                finish();
                            }
                        })
                        .setPositiveListener("取消", null)
                        .build();
                baseDialog.show();
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        BaseDialog baseDialog = builder.cancelTouchout(false)
                .title("提示")
                .content("确定要退出当前账号吗？")
                .setNegativeListener("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logout();
                        HomeActivity.super.onBackPressed();
                    }
                })
                .setPositiveListener("取消", null)
                .build();
        baseDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if (resultCode == RESULT_OK) {

                }
                break;
            }
            default: {
            }
        }
    }

    private void logout() {
        UserApi userApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(UserApi.class);
        userApi.logout().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseResult responseResult) {

                    }
                });
    }

    @WS(threadMode = ThreadMode.MAIN)
    public void onlineCount(OnlineCount onlineCount) {
        ToastUtil.showToast("在线人数：" + onlineCount.onlineCount);
    }

    @WS(threadMode = ThreadMode.MAIN)
    public void onNoticeGet(Notice notice) {
        //收到公告推送，显示提醒
        Log.e("HomeActivity", "收到notice");

    }


    /**
     * 监听SearchingActivity页面的加入或退出通知
     * 并及时更新自己的页面
     */
    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            OrganizationFragment ofm = (OrganizationFragment) mFragmentManager.getFragments().get(0);
            ofm.loadData();
        }
    }

}

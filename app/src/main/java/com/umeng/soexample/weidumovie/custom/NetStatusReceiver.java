package com.umeng.soexample.weidumovie.custom;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import com.umeng.soexample.weidumovie.LoginAndZhuce.NoNetworkActivity;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.ACTIVITY_SERVICE;

public class NetStatusReceiver extends BroadcastReceiver {
    public static final int NETSTATUS_INAVAILABLE = 0;
    public static final int NETSTATUS_WIFI = 1;
    public static final int NETSTATUS_MOBILE = 2;
    public static int netStatus = 0;
    public static boolean updateSuccess = false;
    private INetStatusListener mINetStatusListener;
    private static boolean isFirst = false;
    private static int LAST_TYPE;

    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo allNetInfo = cm.getActiveNetworkInfo();

        if (allNetInfo == null) {
            if (mobileNetInfo != null && (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting())) {
                netStatus = NETSTATUS_MOBILE;
            } else if (wifiNetInfo != null && wifiNetInfo.isConnected() || wifiNetInfo.isConnectedOrConnecting()) {
                netStatus = NETSTATUS_WIFI;
            } else {
                netStatus = NETSTATUS_INAVAILABLE;
            }
        } else {
            if (allNetInfo.isConnected() || allNetInfo.isConnectedOrConnecting()) {
                if (mobileNetInfo.isConnected() || mobileNetInfo.isConnectedOrConnecting()) {
                    netStatus = NETSTATUS_MOBILE;
                } else {
                    netStatus = NETSTATUS_WIFI;
                }
            } else {
                netStatus = NETSTATUS_INAVAILABLE;
            }
        }
        if (mINetStatusListener != null) {
            mINetStatusListener.getNetState(netStatus);
        }
        if (isFirst) {
            if (netStatus == NETSTATUS_INAVAILABLE && LAST_TYPE != NETSTATUS_INAVAILABLE) {
                Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(context, NoNetworkActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
                LAST_TYPE = 0;
            } else if (netStatus == NETSTATUS_MOBILE && LAST_TYPE != NETSTATUS_MOBILE) {
                Toast.makeText(context, "网络处于移动网络", Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                msg.obj = context;
                handler.sendMessageDelayed(msg,1000);
                LAST_TYPE = 2;
            } else if (netStatus == NETSTATUS_WIFI && LAST_TYPE != NETSTATUS_WIFI) {
                Toast.makeText(context, "网络处于Wifi网络", Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                msg.obj = context;
                handler.sendMessageDelayed(msg,1000);
                LAST_TYPE = 1;
            }
        } else {
            isFirst = true;
            LAST_TYPE = netStatus;
        }

    }

    /**
     * 返回当前的应用是否处于前台显示状态
     *
     * @param packageName
     * @return
     */
    private boolean isTopActivity(String packageName, Context context) {
        //_context是一个保存的上下文
        ActivityManager __am = (ActivityManager) context.getApplicationContext().getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> taskInfo = __am.getRunningTasks(10);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        String nowClassName = componentInfo.getClassName();
        componentInfo.getPackageName();
        if (nowClassName.contains(packageName)) {
            Log.i(TAG, "isTopActivity: 0");
            return true;
        } else {
            Log.i(TAG, "isTopActivity: 1");
            return false;
        }
    }

    public interface INetStatusListener {
        void getNetState(int state);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Context context = (Context) msg.obj;
            if (isTopActivity("NoNetworkActivity", context)) {
                NoNetworkActivity.instance.finish();
            }
        }
    };
}

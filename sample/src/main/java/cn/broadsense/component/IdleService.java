package cn.broadsense.component;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * packageName：cn.broadsense.component
 * ProjectName：PublicComponent
 * Description：
 * Author：zhouxian
 * Date：2016/11/4 16:56
 */

public class IdleService extends Service {
    private ScreenReceiver mScreenReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    /**
     * 注册screen状态广播接收器
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mScreenReceiver, filter);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mScreenReceiver = new ScreenReceiver();
        registerReceiver();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 接收亮屏息屏事件广播
     */
    private class ScreenReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) { //设备被唤醒
                Log.i("zzz","ACTION_SCREEN_ON 亮屏  ");

            } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) { //设备进入休眠
                Log.i("zzz","ACTION_SCREEN_OFF 熄屏  ");

            } else if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) { //用户解锁屏幕
                Log.i("zzz","ACTION_USER_PRESENT 屏幕解锁  ");

            }
        }
    }


}

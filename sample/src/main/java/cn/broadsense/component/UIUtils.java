package cn.broadsense.component;


import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

/**
 * packageName：com.broadsense.newpine.set.utils
 * ProjectName：5_Set
 * Description：
 * Author：zhouxian
 * Date：2016/10/19 19:04
 */

public class UIUtils {


    /**
     * 进入休眠
     * Requires the {@link android.Manifest.permission#DEVICE_POWER} permission.
     */
    public static void goToSleep(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        try {
            pm.goToSleep(SystemClock.uptimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("zx", "error " + e);
        }
    }

    /**
     * 当前屏幕是否休眠
     *
     * @param context
     * @return Returns true if the device is in an interactive state.
     */
    public static boolean isScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.isInteractive();
    }

    /**
     * Forces the device to wake up from sleep.
     * @param context
     */
    public static void lightScreen(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        pm.wakeUp(SystemClock.uptimeMillis());
    }


}

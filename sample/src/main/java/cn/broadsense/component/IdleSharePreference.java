package cn.broadsense.component;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * packageName：com.broadsense.bootstrap_8.util
 * ProjectName：Bootstrap
 * Description：
 * Author：zhouxian
 * Date：2016/11/4 9:57
 */

public class IdleSharePreference {
    private static IdleSharePreference instance;
    private SharedPreferences mPreferences;

    private static final String NAME = "idle";
    private static final String REMEMBER_KEY = "remember";

    private IdleSharePreference(Context context) {
        mPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static IdleSharePreference getInstance(Context context) {
        if (instance == null) {
            instance = new IdleSharePreference(context);
        }
        return instance;
    }

    /**
     * @param checked 下次是否提醒
     */
    public void saveRememberState(boolean checked) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(REMEMBER_KEY, checked);
        editor.apply();
    }

    public boolean getRememberState() {
        return mPreferences.getBoolean(REMEMBER_KEY, true);
    }



}

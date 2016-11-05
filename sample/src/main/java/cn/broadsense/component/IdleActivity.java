package cn.broadsense.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;

import butterknife.Bind;


/**
 * packageName：com.broadsense.bootstrap_8.view
 * ProjectName：Bootstrap
 * Description：待机模式Activity
 * 打开改Activity需要传值判断是否已经关注过公众号:
 * <p>
 * Intent intent = new Intent(context,IdleActivity.class);
 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 * context.startActivity(intent);
 * <p>
 * Author：zhouxian
 * Date：2016/11/2 17:35
 */

public class IdleActivity extends BaseActivity {
    private LayoutInflater mInflater;
    private boolean isOpenGuard = true;

    @Bind(R.id.content_view)
    FrameLayout mContentView;

    private CheckBox mCheckBox;
    private Button iKnowBtn; //我知道
    private Button noOpenBtn;  //不用了
    private Button openBtn;  //去开启
    private IdleSharePreference mPreference;

    public static void startIdleActivity(Context context) {
        Intent intent = new Intent(context, IdleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        setContentView(R.layout.activity_idle);
    }

    @Override
    protected void initData() {
        mPreference = IdleSharePreference.getInstance(this);
        mInflater = LayoutInflater.from(this);
//        isOpenGuard = GuardPreference.getInstance().getGuardState();
        if (isOpenGuard) {
            addGuardOpenView();
        } else {
            addGuardCloseView();
        }
    }

    /**
     * 停车监控打开后显示的页面
     */
    private void addGuardOpenView() {
        View view = mInflater.inflate(R.layout.content_open_gurad_view, null);
        iKnowBtn = (Button) view.findViewById(R.id.i_know_btn);
        iKnowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContentView.addView(view);
    }

    /**
     * 倒计时 15s
     */
    private TimeCount time = new TimeCount(15000, 1000);//构造CountDownTimer对象

    class TimeCount extends CountDownTimer {
        TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            goToSleep();
            finish();
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            if (noOpenBtn != null) {
                noOpenBtn.setText("不用了（" + millisUntilFinished / 1000 + "秒）");
            }
            if (iKnowBtn != null) {
                iKnowBtn.setText("我知道了（" + millisUntilFinished / 1000 + "秒）");
            }
        }
    }


    /**
     * 停车监控关闭后显示的页面
     */
    private void addGuardCloseView() {
        View view = mInflater.inflate(R.layout.content_close_guard_view, null);
        mCheckBox = (CheckBox) view.findViewById(R.id.check_box);
        noOpenBtn = (Button) view.findViewById(R.id.no_open_btn);
        openBtn = (Button) view.findViewById(R.id.open_btn);
        openBtn.setText("去开启");
        mCheckBox.setChecked(mPreference.getRememberState());
        view.findViewById(R.id.check_box_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckBox.toggle();
                mPreference.saveRememberState(mCheckBox.isChecked());
            }
        });
        noOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGuardView();
                finish();
            }
        });
        mContentView.addView(view);
    }

    /**
     * 跳转到停车监控页面
     */
    private void goToGuardView() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.broadsense.newpine.set"
                , "com.broadsense.newpine.set.activity.GuardActivity");
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        time.start();
        startService(new Intent(this, IdleService.class));
    }

    private void goToSleep() {
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        try {
            pm.goToSleep(SystemClock.uptimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("zx", "error " + e);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        time.cancel();
        super.onDestroy();
    }

}

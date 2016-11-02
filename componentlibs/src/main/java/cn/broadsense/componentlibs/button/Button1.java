package cn.broadsense.componentlibs.button;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import cn.broadsense.componentlibs.R;

/**
 * packageName：cn.broadsense.componentlibs.button
 * ProjectName：Component
 * Description：
 * Author：zhouxian
 * Date：2016/10/15 14:17
 */

public class Button1 extends Button {

    public Button1(Context context) {
        this(context, null, 0);
    }

    public Button1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Button1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.style.button_1);
        init();
    }

    public void init() {
        //设置文字默认居中
        setGravity(Gravity.CENTER);
        //设置Touch事件
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                //按下改变样式
                setBackground(event.getAction());
                //此处设置为false，防止Click事件被屏蔽
                return false;
            }
        });
        setBackgroundResource(R.drawable.btn_branch_normal);
        setTextColor(Color.parseColor("#787a80"));
    }

    private void setBackground(int state) {
        if (state == MotionEvent.ACTION_DOWN) {
            //按下
            Log.i("zx","ACTION_DOWN");
            setBackgroundResource(R.drawable.btn_branch_pressed);
        } else if (state == MotionEvent.ACTION_UP) {
            //抬起
            Log.i("zx","ACTION_UP");
            setBackgroundResource(R.drawable.btn_branch_normal);
        }
    }


}

package cn.broadsense.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * packageName：com.broadsense.newpine.set.base
 * ProjectName：5_Set
 * Description：
 * Author：zhouxian
 * Date：2016/10/18 9:40
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        initContentView(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected abstract void initContentView(Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

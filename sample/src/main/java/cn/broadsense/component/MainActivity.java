package cn.broadsense.component;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import cn.broadsense.componentlibs.dialog.MyDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button button = (Button) findViewById(R.id.edog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName componentName = new ComponentName("com.baidu.BaiduMap","com.baidu.baidunavis.ui.BNCruiserActivity");
                intent.setComponent(componentName);
                Bundle localBundle = new Bundle();
                localBundle.putInt("cruiser_view_mode", 0);
                intent.putExtras(localBundle);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.title_dialog) {
            MyDialog.Builder builder = new MyDialog.Builder(this);
            builder.setTitle("标题栏")
                    .setBodyText("信息信息")
                    .setLeftBtnClickListener(new MyDialog.OnDialogBtnClickListener() {
                        @Override
                        public void onClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setRightBtnClickListener(new MyDialog.OnDialogBtnClickListener() {
                        @Override
                        public void onClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
            return true;
        }
        if (id == R.id.no_title_dialog) {
            MyDialog.Builder builder = new MyDialog.Builder(this);
            builder.setBodyText("信息信息")
                    .setLeftBtnClickListener(new MyDialog.OnDialogBtnClickListener() {
                        @Override
                        public void onClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setRightBtnClickListener(new MyDialog.OnDialogBtnClickListener() {
                        @Override
                        public void onClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .createNoTitle()
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

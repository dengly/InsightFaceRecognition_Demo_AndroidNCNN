package com.chenty.testncnn;

/**
 * @ClassName:     MainActivity
 * @Description:   MainActiviry to start all other things
 *
 * @author         chenty
 * @version        V1.0
 * @Date           2019.08.16
 */

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();

        RunTimePermissionUtils.onCamera(MainActivity.this, new PermissionCallback(){
            @Override
            public void onPermissionSuccess() {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CameraNcnnFragment.newInstance())
                        .commit();
            }

            @Override
            public void onPermissionFailure() {
                checkPermission();
            }
        });

        checkPermission();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(RunTimePermissionUtils.checkPermissionGranted(MainActivity.this, new String[]{Manifest.permission.CAMERA})){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CameraNcnnFragment.newInstance())
                        .commit();
            }else{
                RunTimePermissionUtils.requestPermission(this, "权限请求", RunTimePermissionUtils.PERMISSION_REQUEST_CODE, new String[]{Manifest.permission.CAMERA} );
            }
        }
    }
}

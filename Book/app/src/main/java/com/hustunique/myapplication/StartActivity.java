package com.hustunique.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import com.facebook.drawee.view.SimpleDraweeView;

import data.UserPref;


/**
 * Created by taozhiheng on 15-9-26.
 */
public class StartActivity extends AppCompatActivity {

    private final static String TAG = "life cycle-start";

    private boolean mEnable;

    private TimeTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "startActivity onCreate");

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }
        UserPref.init(this);
        if(UserPref.getFirstUse())
        {
            Intent intent=new Intent(this,GuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);
//        ImageView view = (ImageView) View.inflate(this, , null);

//        Display display  = getWindowManager().getDefaultDisplay();       // 屏幕宽（像素，如：480px）
//        Point point = new Point();
//        display.getSize(point);
//        DisplayMetrics dm =getResources().getDisplayMetrics();
//        int w_screen = dm.widthPixels;
//        int h_screen = dm.heightPixels;
//
//        Log.d(TAG, "start decode bitmap");
//        Bitmap bitmap = BitmapUtil
//                .decodeSampledBitmapFromResource(getResources(), R.drawable.ic_start,
//                        DisplayUtil.px2dip(this, w_screen), DisplayUtil.px2dip(this, h_screen));

        SimpleDraweeView view = (SimpleDraweeView) findViewById(R.id.start_icon);
        view.setImageURI(Uri.parse("res://drawable/" + R.drawable.ic_start));
//        view.setImageBitmap(bitmap);


        mEnable = false;
        mTask = new TimeTask();
        mTask.execute();
        //渐变展示启动屏
//        AlphaAnimation aa = new AlphaAnimation(1.0f,1.0f);
//        aa.setDuration(500);
//        aa.setFillAfter(true);
//        //view.startAnimation(aa);
//        aa.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationEnd(Animation arg0) {
//                redirectTo();
//                mEnable = true;
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }

//        });

    }

    class TimeTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e)
            {
                mEnable = true;
            }
            mEnable = true;
            redirectTo();
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTask != null && !mTask.isCancelled()) {
            mTask.cancel(true);
            mTask = null;
        }
    }

    /**
     * 跳转到main
     */
    private void redirectTo(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("start", true);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return !mEnable||super.onKeyDown(keyCode, event);
    }
}

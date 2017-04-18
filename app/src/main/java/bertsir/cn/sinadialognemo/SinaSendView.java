package bertsir.cn.sinadialognemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Bert on 2017/4/11.
 */
public class SinaSendView extends Dialog {
    private ImageButton ib_dialog_sina_close;
    private LinearLayout ll_dialog_sina_write;
    private LinearLayout ll_dialog_sina_time;
    private LinearLayout ll_dialog_sina_map;
    private LinearLayout ll_dialog_sina_menu;
    private ImageView iv_dialog_sina_bg,iv_dialog_sina_des;
    private Context mContext;
    private Boolean hideDes;
    private Bitmap screenShot;
    private Bitmap bitmap;
    private ByteArrayOutputStream baos;
    private byte[] bytes;

    public SinaSendView(Context context) {
        super(context);
        this.mContext = context;
    }

    public SinaSendView(Context context, int themeResId, Boolean hideDes) {
        super(context, themeResId);
        this.mContext = context;
        this.hideDes = hideDes;
    }

    protected SinaSendView(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.layout_sina_send_dialog);
        ib_dialog_sina_close = (ImageButton) findViewById(R.id.ib_dialog_sina_close);
        ll_dialog_sina_write = (LinearLayout) findViewById(R.id.ll_dialog_sina_write);
        ll_dialog_sina_time = (LinearLayout) findViewById(R.id.ll_dialog_sina_time);
        ll_dialog_sina_map = (LinearLayout) findViewById(R.id.ll_dialog_sina_map);
        ll_dialog_sina_menu = (LinearLayout) findViewById(R.id.ll_dialog_sina_menu);
        iv_dialog_sina_bg = (ImageView) findViewById(R.id.iv_dialog_sina_bg);
        iv_dialog_sina_des = (ImageView) findViewById(R.id.iv_dialog_sina_des);
        initView();
    }

    private void initView() {
        setBrulBg();
        ll_dialog_sina_menu.setVisibility(View.VISIBLE);
        AnimationUtil.startAnimationSetAt2Bottom(ll_dialog_sina_menu);
        ib_dialog_sina_close.setVisibility(View.VISIBLE);
        ib_dialog_sina_close.setAnimation(AnimationUtil.rotateSelfRight());
        ib_dialog_sina_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationUtil.startAnimationSetAt2Top(ll_dialog_sina_menu);
                ll_dialog_sina_menu.setVisibility(View.GONE);
                ib_dialog_sina_close.setAnimation(AnimationUtil.rotateSelfLeft());
                ib_dialog_sina_close.setVisibility(View.GONE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                },500);
            }
        });
        if(hideDes){
            iv_dialog_sina_des.setVisibility(View.GONE);
        }
    }

    /**
     * 设置模糊背景
     */
    private void setBrulBg(){
        screenShot = CommonUtils.getInstance().getScreenShot((Activity) mContext);
        bitmap = CommonUtils.getInstance().zoomImg(screenShot, 0.2f);
        baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, baos);
        bytes = baos.toByteArray();
        Glide.with(mContext)
                .load(bytes)
                .asBitmap()
                .transform(new BlurTransformation(CommonUtils.getInstance().getContext(), 25))
                .into(iv_dialog_sina_bg);
    }


    public void setClick(final SinaSendDialog mSinaSendDialog){
        this.show();

        ll_dialog_sina_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSinaSendDialog.onNormalClick();
                dismiss();
            }
        });

        ll_dialog_sina_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSinaSendDialog.onMapClick();
                dismiss();
            }
        });

        ll_dialog_sina_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSinaSendDialog.onTimeClick();
                dismiss();
            }
        });

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(screenShot != null && !screenShot.isRecycled()){
            screenShot.recycle();
            screenShot = null;
        }
        if(bitmap != null && !bitmap.isRecycled()){
            bitmap.recycle();
            bitmap = null;
        }
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = null;
        System.gc();
    }

}

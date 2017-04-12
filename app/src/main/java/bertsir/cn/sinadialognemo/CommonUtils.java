package bertsir.cn.sinadialognemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Bert on 2017/3/1.
 */
public class CommonUtils {

    private static CommonUtils instance;


    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }

    public Context getContext() {
        return MyApplication.getContext();
    }

    /**
     *获得屏幕宽高
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     *  获得屏幕宽高
     */
    public int getScreenHeight() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 截取当前屏幕
     * @param activity
     * @return
     */
    public Bitmap getScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                0, getScreenWidth(), getScreenHeight());
        view.destroyDrawingCache();
        return bmp;
    }

    /**
     * 改变bitmap宽高
     * @param bm
     * @param f
     * @return
     */
    public Bitmap zoomImg(Bitmap bm, float f){

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = f;
        float scaleHeight = f;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

}

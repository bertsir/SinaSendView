package bertsir.cn.sinadialognemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Bert on 2017/4/12.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}

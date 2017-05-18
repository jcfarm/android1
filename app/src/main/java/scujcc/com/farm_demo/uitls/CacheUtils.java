package scujcc.com.farm_demo.uitls;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * 作者：尚硅谷-杨光福 on 2016/7/22 11:54
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：缓存工具类
 */
public class CacheUtils {
    private static final String TAG = CacheUtils.class.getSimpleName();

    /**
     * 保持数据
     * @param context
     * @param key
     * @param values
     */
    public static  void putString(Context context,String key,String values){
        Log.e(TAG,"================缓存===========");
        SharedPreferences sharedPreferences = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,values).commit();
    }

    /**
     * 得到缓存的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,"");
    }




}

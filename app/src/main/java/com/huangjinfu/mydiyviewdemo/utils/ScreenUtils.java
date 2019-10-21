package com.huangjinfu.mydiyviewdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

/**
 * 屏幕相关工具类
 * @author Alfred_Huang
 *
 */
public class ScreenUtils {
	
	private static ScreenUtils mInstance = null;
	
	private synchronized static ScreenUtils getInstance(){
		if(mInstance == null){
			mInstance = new ScreenUtils();
		}
		return mInstance;
	}
	
	/**
     * 获取屏幕的宽度和高度
     * 
     * @param context
     * @return screenDimen[0] = width, screenDimen[1] = height
     */
    public static int[] getScreenDimenArray(Context context){
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	int width = wm.getDefaultDisplay().getWidth();
    	int height = wm.getDefaultDisplay().getHeight();
    	int[] screenDimen = new int[]{width, height};
		return screenDimen;
    }
    
	/**
	 * 获取屏幕的宽度和高度
	 * @param context
	 * @return Point.x = = width, Point.y = height
	 */
    public static Point getScreenWidthAndHeight(Context context){
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	int width = wm.getDefaultDisplay().getWidth();
    	int height = wm.getDefaultDisplay().getHeight();
    	Point size = new Point(width, height);
		return size;
    }

	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Point p = new Point();
		wm.getDefaultDisplay().getSize(p);
		return p.x;
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Point p = new Point();
		wm.getDefaultDisplay().getSize(p);
		return p.y;
	}

	/**
	 * 获取手机顶部状态栏的高度
	 * 
	 * @param activity
	 * @return int statusBarHeight
	 */
	public static int getStatusBarHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		return statusBarHeight;
	}
	
	/**
	 * 在声明周期获取手机顶部状态栏的高度
	 * 
	 * @param mContext
	 * @return int statusBarHeight
	 */
	public static int getStatusBarHeightInLifecycle(Context mContext) {

		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = mContext.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			Log.e("EXCEPTION", "get status bar height fail");
			e1.printStackTrace();
		}
		System.out.println("x = " + x + "，sbar = " + sbar);
		return sbar;

	}

	/**
	 * 获取手机虚拟导航栏是否显示
	 * 
	 * @param context
	 * @return boolean isNavigationBarShowing-[true, false]
	 */
	public static boolean getNavigationBarVisible(Context context) {
		Resources resources = context.getResources();
		boolean isNavigationBarShowing = false;
		// 通过资源名称获得资源id ("资源名称", "资源属性", "包名"), 未找到该资源则return 0
		int rid = resources.getIdentifier("config_showNavigationBar", "bool", "android");
		if (rid > 0) {
			//获取导航栏是否显示true or false
			isNavigationBarShowing = resources.getBoolean(rid);
		}
		return isNavigationBarShowing;
	}
	
	/**
	 * 获取手机虚拟导航栏的高度
	 * 
	 * @param context
	 * @return int navigationBarHeight
	 */
	public static int getNavigationBarHeight(Context context){
		Resources resources = context.getResources();
		int navigationBarHeight = 0;
		// 通过资源名称获得资源id ("资源名称", "资源属性", "包名"), 未找到该资源则return 0
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			//获取高度
			navigationBarHeight = resources.getDimensionPixelSize(resourceId);
		}
		return navigationBarHeight;
	}

	/**
	 * 获得某组件的高度
	 * 
	 * @param view
	 * @return int widgetHeight
	 */
	public static int getWidgetHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return view.getMeasuredHeight();
	}

	public static void addViewTreeObserver(final View view, final OnViewTressObserverListener onViewTressObserverListener) {
		Log.w("a", "addViewTreeObserver");
		if (view == null) {
			return;
		}
		final ViewTreeObserver observer = view.getViewTreeObserver();
		// 给观察者添加布局监听器
		observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				final int version = Build.VERSION.SDK_INT;
				/*
				 * 移除监听器，避免重复调用
				 */
				// 判断sdk版本，removeGlobalOnLayoutListener在API 16以后不再使用
				// 判断ViewTreeObserver 是否alive
				if(observer.isAlive()) {
					if (version >= Build.VERSION_CODES.JELLY_BEAN) {
						observer.removeOnGlobalLayoutListener(this);
					} else {
						observer.removeGlobalOnLayoutListener(this);
					}
					if(onViewTressObserverListener != null) {
						onViewTressObserverListener.onViewObserve(view);
					}
				}
			}
		});
	}

	public interface OnViewTressObserverListener {
		void onViewObserve(View view);
	}

	/**
	 * 获得某组件的宽度
	 * 
	 * @param view
	 * @return int widgetWidth
	 */
	public static int getWidgetWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return view.getMeasuredWidth();
	}

	/**
	 * 获取某组件的坐标数组
	 * 
	 * @param widget
	 * @return location[0] = X-Coordinate, location[1] = Y-Coordinate
	 */
	public static int[] getWidgetPosArray(View widget) {
		int[] location = new int[2];
		widget.getLocationOnScreen(location);
		return location;
	}
	
	/**
	 * 根据手机的分辨率从sp单位转成为px
	 * 
	 * @param context
	 * @param spValue
	 * @return int pxValue
	 */
	public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (spValue * fontScale + 0.5f); 
    }
	
	/**
	 * 根据手机的分辨率从dp单位转成为px
	 * 
	 * @param context
	 * @param dpValue
	 * @return int pxValue
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * 根据手机的分辨率从px单位转成为dp
	 * 
	 * @param context
	 * @param pxValue
	 * @return int dipValue
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/**
     * 压缩图片
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 节约内存
        /* 降低图片从ARGB888到RGB565 */
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        /* 不进行图片抖动处理 */
        options.inDither = false;
        /* 设置让解码器以最佳方式解码 */
        options.inPreferredConfig = null;
        /* 下面两个字段需要组合使用 */
        options.inPurgeable = true;
        options.inInputShareable = true;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        int quality = 90;
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 100) {
            // 重置baos
            baos.reset();
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            // 每次都减少10
            quality -= 10;
            Log.e("compress", "quality = " + quality + ", byte size = " + baos.toByteArray().length / 1024 + "k");
            if (quality < 10) {
                break;
            }
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, options);
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }
    
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

}

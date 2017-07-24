package yyk.decoratinghouses.util;

import android.content.Context;

/**
 * dp 与px  互转的工具类
 * @author yangdan
 */
public class DensityUtil {
	/**
	 * dip转换像素px
	 */
	public static int dip2px(Context context, float dpValue) {
		try {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dpValue * scale + 0.5f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) dpValue;
	}

	public static float sp2pxF(Context context, float dpValue) {
		try {
			final float scale = context.getResources().getDisplayMetrics().density;
			return dpValue * scale + 0.5f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dpValue;
	}

	/**
	 * 像素px转换为dip
	 */
	public static int px2dip(Context context, float pxValue) {
		try {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (pxValue / scale + 0.5f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) pxValue;
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static float px2spF(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return pxValue / fontScale + 0.5f;
	}
}

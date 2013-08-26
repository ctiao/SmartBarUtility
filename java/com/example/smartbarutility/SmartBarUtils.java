package in.mobilelin.smartbar;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SmartBarUtils {

	public static final int SMART_BAR_HEIGH = 96;

	/**
	 * 调用 ActionBar.setTabsShowAtBottom(boolean) 方法。 如果
	 * android:uiOptions="splitActionBarWhenNarrow"，则可设置ActionBar Tabs显示在底栏。
	 * 
	 * 示例： public class MyActivity extends Activity implements
	 * ActionBar.TabListener { protected void onCreate(Bundle
	 * savedInstanceState) { super.onCreate(savedInstanceState); ...
	 * 
	 * final ActionBar bar = getActionBar();
	 * bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	 * SmartBarUtils.setActionBarTabsShowAtBottom(bar, true);
	 * 
	 * bar.addTab(bar.newTab().setText(&quot;tab1&quot;).setTabListener(this));
	 * ... } }
	 * 
	 */
	public static void setActionBarTabsShowAtBottom(ActionBar actionbar,
			boolean showAtBottom) {
		try {
			Method method = Class.forName("android.app.ActionBar").getMethod(
					"setTabsShowAtBottom", new Class[] { boolean.class });
			try {
				method.invoke(actionbar, showAtBottom);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用 ActionBar.setActionBarViewCollapsable(boolean) 方法。
	 * 设置ActionBar顶栏无显示内容时是否隐藏。
	 * 
	 * 示例：
	 * 
	 * public class MyActivity extends Activity {
	 * 
	 * protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); ...
	 * 
	 * final ActionBar bar = getActionBar();
	 * 
	 * // 调用setActionBarViewCollapsable，并设置ActionBar没有显示内容，则ActionBar顶栏不显示
	 * SmartBarUtils.setActionBarViewCollapsable(bar, true);
	 * bar.setDisplayOptions(0); } }
	 */
	public static void setActionBarViewCollapsable(ActionBar actionbar,
			boolean collapsable) {
		try {
			Method method = Class.forName("android.app.ActionBar").getMethod(
					"setActionBarViewCollapsable",
					new Class[] { boolean.class });
			try {
				method.invoke(actionbar, collapsable);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用 ActionBar.setActionModeHeaderHidden(boolean) 方法。 设置ActionMode顶栏是否隐藏。
	 * 
	 * public class MyActivity extends Activity {
	 * 
	 * protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); ...
	 * 
	 * final ActionBar bar = getActionBar();
	 * 
	 * // ActionBar转为ActionMode时，不显示ActionMode顶栏
	 * SmartBarUtils.setActionModeHeaderHidden(bar, true); } }
	 */
	public static void setActionModeHeaderHidden(ActionBar actionbar,
			boolean hidden) {
		try {
			Method method = Class.forName("android.app.ActionBar").getMethod(
					"setActionModeHeaderHidden", new Class[] { boolean.class });
			try {
				method.invoke(actionbar, hidden);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 原隐藏SmartBar的方法
	 * 此方法已从Flyme2.4.1开始失效 示例：
	 * 
	 * final ActionBar actionBar = getActionBar(); SmartBarUtils.hide(this);
	 * 
	 */
	@Deprecated
	public static final void hide(FragmentActivity activity) {
		ActionBar actionBar = activity.getActionBar();
		if (actionBar == null) {
			return;
		}
		Class<? extends ActionBar> ActionBarClass = actionBar.getClass();
		Method setTabsShowAtBottom;
		try {
			setTabsShowAtBottom = ActionBarClass.getMethod(
					"setTabsShowAtBottom", Boolean.TYPE);
			setTabsShowAtBottom.invoke(activity.getActionBar(), true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以下三个方法原作者为c跳跳(http://weibo.com/u/1698085875),
	 * 由Shawn(http://weibo.com/linshen2011)在其基础上改进了一种判断SmartBar是否存在的方法,
	 * 注意该方法反射的接口只存在于2013年6月之后魅族的flyme固件中
	 */

	/**
	 * 方法一:uc等在使用的方法(新旧版flyme均有效)，
	 * 此方法需要配合requestWindowFeature(Window.FEATURE_NO_TITLE
	 * )使用,缺点是程序无法使用系统actionbar
	 * 
	 * @param decorView
	 *            window.getDecorView
	 */
	public static void hide(View decorView) {
		if (!hasSmartBar())
			return;

		try {
			@SuppressWarnings("rawtypes")
			Class[] arrayOfClass = new Class[1];
			arrayOfClass[0] = Integer.TYPE;
			Method localMethod = View.class.getMethod("setSystemUiVisibility",
					arrayOfClass);
			Field localField = View.class
					.getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION");
			Object[] arrayOfObject = new Object[1];
			try {
				arrayOfObject[0] = localField.get(null);
			} catch (Exception e) {

			}
			localMethod.invoke(decorView, arrayOfObject);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 方法二：此方法需要配合requestWindowFeature(Window.FEATURE_NO_TITLE)使用
	 * ，缺点是程序无法使用系统actionbar
	 * 
	 * @param context
	 * @param window
	 */
	public static void hide(Context context, Window window) {
		hide(context, window, 0);
	}

	private static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 方法三：需要使用顶部actionbar的应用请使用此方法
	 * 
	 * @param context
	 * @param window
	 * @param smartBarHeight
	 *            set SmartBarUtils.SMART_BAR_HEIGHT_PIXEL
	 */
	public static void hide(Context context, Window window, int smartBarHeight) {
		if (!hasSmartBar()) {
			return;
		}
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return;
		}

		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		int statusBarHeight = getStatusBarHeight(context);

		window.getDecorView()
				.setPadding(0, statusBarHeight, 0, -smartBarHeight);
	}

	/**
	 * 新型号可用反射调用Build.hasSmartBar()来判断有无SmartBar
	 * @return
	 */
	public static boolean hasSmartBar() {
		try {
			Method method = Class.forName("android.os.Build").getMethod(
					"hasSmartBar");
			return ((Boolean) method.invoke(null)).booleanValue();
		} catch (Exception e) {
		}

		if (Build.DEVICE.equals("mx2")) {
			return true;
		} else if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
			return false;
		}
		return false;
	}

}

package com.wangjf.myutils;

/*
with           : 设置 snackbar 依赖 view
setMessage     : 设置消息
setMessageColor: 设置消息颜色
setBgColor     : 设置背景色
setBgResource  : 设置背景资源
setDuration    : 设置显示时长
setAction      : 设置行为
setBottomMargin: 设置底边距
show           : 显示 snackbar
showSuccess    : 显示预设成功的 snackbar
showWarning    : 显示预设警告的 snackbar
showError      : 显示预设错误的 snackbar
dismiss        : 消失 snackbar
getView        : 获取 snackbar 视图
addView        : 添加 snackbar 视图

public class SnackbarActivity extends BaseBackActivity {

    View snackBarRootView;

    public static void start(Context context) {
        Intent starter = new Intent(context, SnackbarActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_snackbar;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        getToolBar().setTitle(getString(R.string.demo_snackbar));

        snackBarRootView = findViewById(android.R.id.content);
        findViewById(R.id.btn_short_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_short_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_long_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_long_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_indefinite_snackbar).setOnClickListener(this);
        findViewById(R.id.btn_indefinite_snackbar_with_action).setOnClickListener(this);
        findViewById(R.id.btn_add_view).setOnClickListener(this);
        findViewById(R.id.btn_add_view_with_action).setOnClickListener(this);
        findViewById(R.id.btn_show_success).setOnClickListener(this);
        findViewById(R.id.btn_show_warning).setOnClickListener(this);
        findViewById(R.id.btn_show_error).setOnClickListener(this);
        findViewById(R.id.btn_dismiss_snackbar).setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_short_snackbar:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_short))
                        .setMessageColor(Color.WHITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .show();
                break;

            case R.id.btn_short_snackbar_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_short))
                        .setMessageColor(Color.WHITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .setAction(getString(R.string.snackbar_click), Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort(getString(R.string.snackbar_click));
                            }
                        })
                        .show();
                break;

            case R.id.btn_long_snackbar:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_long))
                        .setMessageColor(Color.WHITE)
                        .setDuration(SnackbarUtils.LENGTH_LONG)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .show();
                break;

            case R.id.btn_long_snackbar_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_long))
                        .setMessageColor(Color.WHITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .setDuration(SnackbarUtils.LENGTH_LONG)
                        .setAction(getString(R.string.snackbar_click), Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort(getString(R.string.snackbar_click));
                            }
                        })
                        .show();
                break;

            case R.id.btn_indefinite_snackbar:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_indefinite))
                        .setMessageColor(Color.WHITE)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .show();
                break;

            case R.id.btn_indefinite_snackbar_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_indefinite))
                        .setMessageColor(Color.WHITE)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .setBgResource(R.drawable.shape_top_round_rect)
                        .setAction(getString(R.string.snackbar_click), Color.YELLOW, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort(getString(R.string.snackbar_click));
                            }
                        })
                        .show();
                break;

            case R.id.btn_add_view:
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                SnackbarUtils.with(snackBarRootView)
                        .setBgColor(Color.TRANSPARENT)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .show();
                SnackbarUtils.addView(R.layout.snackbar_custom, params);
                break;

            case R.id.btn_add_view_with_action:
                SnackbarUtils.with(snackBarRootView)
                        .setBgColor(Color.TRANSPARENT)
                        .setDuration(SnackbarUtils.LENGTH_INDEFINITE)
                        .show();
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                SnackbarUtils.addView(R.layout.snackbar_custom, params);
                View snackbarView = SnackbarUtils.getView();
                if (snackbarView != null) {
                    TextView tvSnackbarCustom = snackbarView.findViewById(R.id.tv_snackbar_custom);
                    tvSnackbarCustom.setText("点我可消失");
                    snackbarView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SnackbarUtils.dismiss();
                        }
                    });
                }
                break;

            case R.id.btn_show_success:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_success))
                        .showSuccess();
                break;

            case R.id.btn_show_warning:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_warning))
                        .showWarning();
                break;

            case R.id.btn_show_error:
                SnackbarUtils.with(snackBarRootView)
                        .setMessage(getMsg(R.string.snackbar_error))
                        .showError();
                break;

            case R.id.btn_dismiss_snackbar:
                SnackbarUtils.dismiss();
                break;
        }
    }

    private SpannableStringBuilder getMsg(@StringRes int resId) {
        return new SpanUtils()
                .appendImage(R.mipmap.ic_launcher, SpanUtils.ALIGN_CENTER)
                .appendSpace(32)
                .append(getString(resId)).setFontSize(24, true)
                .create();
    }
}




*/

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/16
 *     desc  : Snackbar 相关工具类
 * </pre>
 */
public final class SnackbarUtils {

    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_SHORT      = -1;
    public static final int LENGTH_LONG       = 0;

    @IntDef({LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    private static final int COLOR_SUCCESS = 0xFF2BB600;
    private static final int COLOR_WARNING = 0xFFFFC100;
    private static final int COLOR_ERROR   = 0xFFFF0000;
    private static final int COLOR_MESSAGE = 0xFFFFFFFF;

    private static WeakReference<Snackbar> sReference;

    private View                 parent;
    private CharSequence         message;
    private int                  messageColor;
    private int                  bgColor;
    private int                  bgResource;
    private int                  duration;
    private CharSequence         actionText;
    private int                  actionTextColor;
    private View.OnClickListener actionListener;
    private int                  bottomMargin;

    private SnackbarUtils(final View parent) {
        setDefault();
        this.parent = parent;
    }

    private void setDefault() {
        message = "";
        messageColor = COLOR_DEFAULT;
        bgColor = COLOR_DEFAULT;
        bgResource = -1;
        duration = LENGTH_SHORT;
        actionText = "";
        actionTextColor = COLOR_DEFAULT;
        bottomMargin = 0;
    }

    /**
     * 设置 snackbar 依赖 view
     *
     * @param parent 依赖 view
     * @return {@link SnackbarUtils}
     */
    public static SnackbarUtils with(@NonNull final View parent) {
        return new SnackbarUtils(parent);
    }

    /**
     * 设置消息
     *
     * @param msg 消息
     * @return {@link SnackbarUtils}
     */
    public SnackbarUtils setMessage(@NonNull final CharSequence msg) {
        this.message = msg;
        return this;
    }

    /**
     * 设置消息颜色
     *
     * @param color 颜色
     * @return {@link SnackbarUtils}
     */
    public SnackbarUtils setMessageColor(@ColorInt final int color) {
        this.messageColor = color;
        return this;
    }

    /**
     * 设置背景色
     *
     * @param color 背景色
     * @return {@link SnackbarUtils}
     */
    public SnackbarUtils setBgColor(@ColorInt final int color) {
        this.bgColor = color;
        return this;
    }

    /**
     * 设置背景资源
     *
     * @param bgResource 背景资源
     * @return {@link SnackbarUtils}
     */
    public SnackbarUtils setBgResource(@DrawableRes final int bgResource) {
        this.bgResource = bgResource;
        return this;
    }

    /**
     * 设置显示时长
     *
     * @param duration 时长
     *                 <ul>
     *                 <li>{@link Duration#LENGTH_INDEFINITE}永久</li>
     *                 <li>{@link Duration#LENGTH_SHORT}短时</li>
     *                 <li>{@link Duration#LENGTH_LONG}长时</li>
     *                 </ul>
     * @return {@link SnackbarUtils}
     */
    public SnackbarUtils setDuration(@Duration final int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 设置行为
     *
     * @param text     文本
     * @param listener 事件
     * @return {@link SnackbarUtils}
     */
    public SnackbarUtils setAction(@NonNull final CharSequence text,
                                   @NonNull final View.OnClickListener listener) {
        return setAction(text, COLOR_DEFAULT, listener);
    }

    /**
     * 设置行为
     *
     * @param text     文本
     * @param color    文本颜色
     * @param listener 事件
     * @return {@link SnackbarUtils}
     */

    public SnackbarUtils setAction(@NonNull final CharSequence text,
                                   @ColorInt final int color,
                                   @NonNull final View.OnClickListener listener) {
        this.actionText = text;
        this.actionTextColor = color;
        this.actionListener = listener;
        return this;
    }

    /**
     * 设置底边距
     *
     * @param bottomMargin 底边距
     */
    public SnackbarUtils setBottomMargin(@IntRange(from = 1) final int bottomMargin) {
        this.bottomMargin = bottomMargin;
        return this;
    }

    /**
     * 显示 snackbar
     */
    public void show() {
        final View view = parent;
        if (view == null) return;
        if (messageColor != COLOR_DEFAULT) {
            SpannableString spannableString = new SpannableString(message);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(messageColor);
            spannableString.setSpan(
                    colorSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            sReference = new WeakReference<>(Snackbar.make(view, spannableString, duration));
        } else {
            sReference = new WeakReference<>(Snackbar.make(view, message, duration));
        }
        final Snackbar snackbar = sReference.get();
        final View snackbarView = snackbar.getView();
        if (bgResource != -1) {
            snackbarView.setBackgroundResource(bgResource);
        } else if (bgColor != COLOR_DEFAULT) {
            snackbarView.setBackgroundColor(bgColor);
        }
        if (bottomMargin != 0) {
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams) snackbarView.getLayoutParams();
            params.bottomMargin = bottomMargin;
        }
        if (actionText.length() > 0 && actionListener != null) {
            if (actionTextColor != COLOR_DEFAULT) {
                snackbar.setActionTextColor(actionTextColor);
            }
            snackbar.setAction(actionText, actionListener);
        }
        snackbar.show();
    }

    /**
     * 显示预设成功的 snackbar
     */
    public void showSuccess() {
        bgColor = COLOR_SUCCESS;
        messageColor = COLOR_MESSAGE;
        actionTextColor = COLOR_MESSAGE;
        show();
    }

    /**
     * 显示预设警告的 snackbar
     */
    public void showWarning() {
        bgColor = COLOR_WARNING;
        messageColor = COLOR_MESSAGE;
        actionTextColor = COLOR_MESSAGE;
        show();
    }

    /**
     * 显示预设错误的 snackbar
     */
    public void showError() {
        bgColor = COLOR_ERROR;
        messageColor = COLOR_MESSAGE;
        actionTextColor = COLOR_MESSAGE;
        show();
    }

    /**
     * 消失 snackbar
     */
    public static void dismiss() {
        if (sReference != null && sReference.get() != null) {
            sReference.get().dismiss();
            sReference = null;
        }
    }

    /**
     * 获取 snackbar 视图
     *
     * @return snackbar 视图
     */
    public static View getView() {
        Snackbar snackbar = sReference.get();
        if (snackbar == null) return null;
        return snackbar.getView();
    }

    /**
     * 添加 snackbar 视图
     * <p>在{@link #show()}之后调用</p>
     *
     * @param layoutId 布局文件
     * @param params   布局参数
     */
    public static void addView(@LayoutRes final int layoutId,
                               @NonNull final ViewGroup.LayoutParams params) {
        final View view = getView();
        if (view != null) {
            view.setPadding(0, 0, 0, 0);
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) view;
            View child = LayoutInflater.from(view.getContext()).inflate(layoutId, null);
            layout.addView(child, -1, params);
        }
    }

    /**
     * 添加 snackbar 视图
     * <p>在{@link #show()}之后调用</p>
     *
     * @param child  要添加的 view
     * @param params 布局参数
     */
    public static void addView(@NonNull final View child,
                               @NonNull final ViewGroup.LayoutParams params) {
        final View view = getView();
        if (view != null) {
            view.setPadding(0, 0, 0, 0);
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) view;
            layout.addView(child, params);
        }
    }
}
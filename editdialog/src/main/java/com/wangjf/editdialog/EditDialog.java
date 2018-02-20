package com.wangjf.editdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.wangjf.editdialog.R;


/**
 * @author gyw 
 * @version 1.0
 * @time: 2015-10-12 下午3:06:52 
 * @fun:
 */
public class EditDialog extends Dialog implements View.OnClickListener {

	private View mView;
	private Context mContext;

	private LinearLayout mBgLl;
	//private TextView mTitleTv;
	private EditText mMsgEt;
	private Button mNegBtn;
	private Button mPosBtn;

	public EditDialog(Context context) {
		this(context, 0, null, "");
	}

	//默认显示data
	public EditDialog(Context context, String data) {
		this(context, 0, null, data);
	}

	public EditDialog(Context context, int theme, View contentView, String data) {
		super(context, theme == 0 ? R.style.MyDialogStyle : theme);

		this.mView = contentView;
		this.mContext = context;

		if (mView == null) {
			mView = View.inflate(mContext, R.layout.view_enter_edit, null);
		}

		init();
		initView();
		initData(data);
		initListener();

	}

	private void init() {
		this.setContentView(mView);
	}

	private void initView() {
		mBgLl = (LinearLayout) mView.findViewById(R.id.lLayout_bg);
		//mTitleTv = (TextView) mView.findViewById(R.id.txt_title);
		mMsgEt = (EditText) mView.findViewById(R.id.et_msg);
		mNegBtn = (Button) mView.findViewById(R.id.btn_neg);
		mPosBtn = (Button) mView.findViewById(R.id.btn_pos);
	}

	private void initData(String data) {
		//设置背景是屏幕的0.85
		mBgLl.setLayoutParams(new FrameLayout.LayoutParams((int) (getMobileWidth(mContext) * 0.85), LayoutParams.WRAP_CONTENT));
		//设置默认为10000
		mMsgEt.setText(data);
	}

	private void initListener() {
		mNegBtn.setOnClickListener(this);
		mPosBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_neg) {    //取消,
			if (onPosNegClickListener != null) {
				String mEtValue = mMsgEt.getText().toString().trim();
				if (!mEtValue.isEmpty()) {
					onPosNegClickListener.negCliclListener(mEtValue);
				}
			}

			this.dismiss();
		}

		if(v.getId() == R.id.btn_pos) {//确认
			if (onPosNegClickListener != null) {
				String mEtValue = mMsgEt.getText().toString().trim();
				if (!mEtValue.isEmpty()) {
//					if (mEtValue.length() > 8 || mEtValue.length() < 4 || Integer.parseInt(mEtValue) <= 0) {
//						//TODO 这里处理条件
//					}
					onPosNegClickListener.posClickListener(mEtValue);
				}
			}
			this.dismiss();
		}
	}
	
	private OnPosNegClickListener onPosNegClickListener;
	
	public void setOnPosNegClickListener (OnPosNegClickListener onPosNegClickListener) {
		this.onPosNegClickListener = onPosNegClickListener;
	}
	
	public interface OnPosNegClickListener {
		void posClickListener(String value);
		void negCliclListener(String value);
	}



	/**
	 * 工具类
	 * @param context
	 * @return int
	 *
	 */
	public static int getMobileWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels; // 得到宽度
		return width;
	}
}

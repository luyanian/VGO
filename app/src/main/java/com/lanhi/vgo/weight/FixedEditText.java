package com.lanhi.vgo.weight;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.lanhi.vgo.R;

public class FixedEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener {

    private String fixText;
    private boolean isDelete = false;//是否是可删除
    private boolean isFix = false;//表示是否需要显示固定文字
    @ColorInt
    private int fixColor;//固定文本颜色

    public FixedEditText(Context context) {
        super(context);
        init();
    }

    public FixedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        this.addTextChangedListener(textWatcher);
        this.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        this.setLongClickable(false);
        this.setOnFocusChangeListener(this);

        setFixText("$",R.color.colorPrimary);
    }

    TextWatcher textWatcher = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String str = editable.toString();
            if(!str.startsWith("$")){
                setFixText("$",R.color.colorPrimary);
            }
            int tempLength = temp.length();
            int fixLength = fixText.length();
            isDelete = tempLength > fixLength;
        }
    };

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        //限制光标的位置，使得最初光标总是从固定文本之后开始的
        if (isFix) {
            selStart = selEnd = getText().length();
            this.setSelection(selStart, selEnd);
        }
        super.onSelectionChanged(selStart, selEnd);
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == 67) {//删除键
//            //当标记为不能删除的时候拦截操作
//            if (!isDelete) {
//                return !isDelete;
//            }
//        }
//        return super.dispatchKeyEvent(event);
//    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus && getText().toString().length() <= fixText.length()) {
            // 在获取焦点的时候，去设置头部文本；(默认进入页面不获取焦点，这样可以显示默认文本)
            if (fixColor != 0) {
                setEdtTxtColor(fixText);
            } else {
                this.setText(fixText);
            }
        }
    }

    /**
     * 输入固定文本
     * @param fixText：固定文本前缀
     * @param fixColor：固定文本的颜色
     */
    public void setFixText(String fixText, @ColorRes int fixColor) {
        this.fixColor = getResources().getColor(fixColor);
        this.fixText = TextUtils.isEmpty(fixText) ? "" : fixText;
        if (fixText.length() > 0) {
            isFix = true;
        }
    }

    /**
     * 设置固定文本的颜色
     * @param fixText
     */
    private void setEdtTxtColor(String fixText) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        ForegroundColorSpan fixTextSpan = new ForegroundColorSpan(fixColor);
        builder.append(fixText);
        builder.setSpan(fixTextSpan, 0, fixText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        this.setText(builder);
        this.setSelection(fixText.length());
    }

    /**
     * 设置Hint文本
     * @param fixText：固定头部文本
     * @param hintText：输入提示文字
     */
    public void setEdtHint(String fixText, String hintText) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        ForegroundColorSpan fixTextSpan = new ForegroundColorSpan(fixColor);
        builder.append(fixText);
        builder.setSpan(fixTextSpan, 0, fixText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.append(hintText);
        this.setHint(builder);
    }

    /**
     * 判断除了固定头，是否有其他输入文本
     * @return
     */
    public boolean isEdtContentEmpty() {
        return getText().toString().trim().length() <= fixText.length();
    }

    /**
     * 获取输入文本内容
     * @param symbol：输入的符号
     * @return
     */
    public String getContentText(String symbol) {
        String content = getText().toString().trim();
        int index = content.indexOf(symbol, 1);
        return content.substring(index + 1).trim();
    }
}
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

public class FixedEditText extends android.support.v7.widget.AppCompatEditText{

    private String fixText="$";

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
            if(fixText.equals(str)){
                setText("");
            }else if(str.length()>0){
                if(!str.startsWith(fixText)){
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    ForegroundColorSpan fixTextSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
                    builder.append(fixText);
                    builder.setSpan(fixTextSpan, 0, fixText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    builder.append(str);
                    setText(builder);
                    setSelection(builder.length());
                }
            }
        }
    };
}
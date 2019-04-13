package view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.school.R;

import java.util.Locale;

public class SearchEditText extends EditText {

    private static boolean DEBUG = false;

    private Drawable mSearchDrawable;
    private Drawable mDeleteDrawable;

    public SearchEditText(Context context) {
        this(context, null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //left top right bottom
        mSearchDrawable = getCompoundDrawables()[0];
        if (mSearchDrawable == null){
            mSearchDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_search);
        }
        int mIntrinsicWidth = mSearchDrawable.getIntrinsicWidth();
        int mIntrinsicHeight = mSearchDrawable.getIntrinsicHeight();
        int width = (int) (mIntrinsicWidth * 0.1f);
        int height = (int) (mIntrinsicHeight * 0.1f);
        mSearchDrawable.setBounds(0, 0, width, height);

        if (DEBUG){
            Locale locale = Locale.getDefault();
            String info = String.format(locale,"[(%d, %d), (%d, %d)]", mIntrinsicWidth, mIntrinsicHeight, width, height);
            Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();  //(96, 96), (776, 76)
        }

        mDeleteDrawable = getCompoundDrawables()[2];
        if (mDeleteDrawable == null){
            mDeleteDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_delete);
        }
        mIntrinsicWidth = mDeleteDrawable.getIntrinsicWidth();
        mIntrinsicHeight = mDeleteDrawable.getIntrinsicHeight();
        width = (int) (mIntrinsicWidth * 0.1f);
        height = (int) (mIntrinsicHeight * 0.1f);
        mDeleteDrawable.setBounds(0, 0, width, height);

        setDeleteDrawable(false);
        addTextChangedListener(new MiddleTextWatcher(){
            public void onTextChanged(CharSequence s, int start, int before, int count){
                setDeleteDrawable(s.length() > 0);
            }
        });
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                boolean visible = b && (getText().length() > 0);
                setDeleteDrawable(visible);
            }
        });
    }

    private void setDeleteDrawable(boolean visible){
        Drawable right = visible ? mDeleteDrawable : null;
        setCompoundDrawables(mSearchDrawable, null, right,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        if (mDeleteDrawable != null && e.getAction() == MotionEvent.ACTION_UP){
            int left = getWidth() - getPaddingRight() - mDeleteDrawable.getIntrinsicWidth();
            int right = getWidth() - getPaddingRight();
            if (DEBUG){
                Toast.makeText(getContext(), "left, right =" + left + "," + right, Toast.LENGTH_SHORT).show();
            }
            if (e.getX() >= left && e.getX() <= right){
                this.setText("");
            }
        }

        return super.onTouchEvent(e);
    }

    public static class MiddleTextWatcher implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}

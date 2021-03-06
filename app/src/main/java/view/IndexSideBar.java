package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.school.R;

import java.util.Arrays;
import java.util.List;

import utils.DisplayUtils;

public class IndexSideBar extends View {

    private static final boolean DEBUG = false;

    private static String[] mLetterIndexArray = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    };

    private List<String> mLetterIndexList;
    private int curLetterIndex = -1;    // current letter index
    private Paint mPaint;
    private Rect mTextBounds;

    private int mViewWidth;     // IndexSideBar width
    private int mViewHeight;    // IndexSideBar height

    public IndexSideBar(Context context) {
        this(context, null);
    }

    public IndexSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mLetterIndexList = Arrays.asList(mLetterIndexArray);    // String[] -> List
        mTextBounds = new Rect();
        mPaint = new Paint();       // Paint.ANTI_ALIAS_FLAG
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.SANS_SERIF);
        mPaint.setTextSize(DisplayUtils.sp2px(getContext(), 12));  // 12sp // 12dp

        setBackgroundColor(Color.alpha(0));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        mViewWidth = w;
        mViewHeight = h;
        if (false) {
            Toast.makeText(getContext(),"IndexSideBar: onSizeChanged()\n" + w + ", " + h, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        int size = mLetterIndexList.size();
        float cellHeight = mViewHeight * 1.0f / size;

        for (int index = 0; index < size; index++){
            mPaint.setColor(Color.BLACK);
            if (index == curLetterIndex){
                mPaint.setColor(Color.WHITE);
            }
            String letter = mLetterIndexList.get(index);

            float xPos = (mViewWidth - mPaint.measureText(letter)) / 2;

            mPaint.getTextBounds(letter, 0, letter.length(), mTextBounds);
            int textHeight = mTextBounds.height();
            float yPos = cellHeight /2 + textHeight / 2 + cellHeight * index;

            canvas.drawText(letter, xPos, yPos, mPaint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e){
        float y = e.getY();
        int size = mLetterIndexList.size();
        int oldLetterindex = curLetterIndex;
        int tmpLetterIndex = (int) (y / mViewHeight * size);

        if (e.getAction() == MotionEvent.ACTION_UP){
            setBackgroundColor(Color.alpha(0));
            curLetterIndex = -1;
            invalidate();
            if (listener != null){
                listener.onTouchedLetterListener();
            }
        }else{
            setBackgroundColor(R.drawable.bg_index_side_bar);
            if (tmpLetterIndex != oldLetterindex){
                if (tmpLetterIndex >= 0 && tmpLetterIndex < size){
                    if (listener != null){
                        listener.onTouchingLetterListener(mLetterIndexList.get(tmpLetterIndex));
                    }
                    curLetterIndex = tmpLetterIndex;
                    invalidate();
                }
            }
        }

        return true;
    }

    private OnTouchLetterListener listener;

    public void setOnTouchLetterListener(OnTouchLetterListener listener){
        this.listener = listener;
    }

    public interface OnTouchLetterListener {
        void onTouchingLetterListener(String letter);

        void onTouchedLetterListener();
    }

    public void setLetterIndexList(List<String> list) {
        setLetterIndexList(list, true);
    }

    public void setLetterIndexList(List<String> list, boolean perform) {
        mLetterIndexList = perform ? list : Arrays.asList(mLetterIndexArray);
        invalidate();
    }
}

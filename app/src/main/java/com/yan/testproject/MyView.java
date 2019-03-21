package com.yan.testproject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.*;

/**
 * @author : yan
 * @date : 2019/3/19 21:05
 * @desc : todo
 */
public class MyView extends View {

    private static final String TAG = "yan";

    private OnKeyListener keyListener;
    private InputMethodManager inputManager;

    public MyView(Context context) {
        super(context);
//        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    private void init() {
        setFocusableInTouchMode(true);
        setWatchDeleteEvent(new MyKeyListener());
//        setOnKeyListener(keyListener);
        inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            /**
             * 弹出软键盘
             */
            requestFocus();
            inputManager.showSoftInput(this, InputMethodManager.SHOW_FORCED);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
//        return super.onCreateInputConnection(outAttrs);
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        return new MyInputConnection(this, false);
    }

    public void setWatchDeleteEvent(OnKeyListener listener) {
        keyListener = listener;
    }

    private class MyInputConnection extends BaseInputConnection {


        public MyInputConnection(View targetView, boolean fullEditor) {
            super(targetView, fullEditor);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if (keyListener != null) {
                keyListener.onKey(MyView.this, event.getKeyCode(), event);
            }
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if (beforeLength == 1 || afterLength == 0 || beforeLength == 0) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }
            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }

    class MyKeyListener implements OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            Log.e(TAG, "onKey: " + event );
            int action = event.getAction();
            if (action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    /**
                     * 删除操作
                     */
                    return true;
                }
                if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                    /**
                     * 只支持数字
                     */
                    return true;
                }
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    /**
                     * 确认键
                     */
                    return true;
                }
            }
            return false;
        }
    }
}

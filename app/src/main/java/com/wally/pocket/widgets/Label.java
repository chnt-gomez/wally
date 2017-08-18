package com.wally.pocket.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wally.pocket.R;

/**
 * Created by MAV1GA on 17/08/2017.
 */

public class Label extends LinearLayout {

    private static float SCALE;

    private TextView label, display;

    public Label(Context context, AttributeSet attrs) {
        super(context, attrs);
        SCALE = context.getResources().getDisplayMetrics().density;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Label,
                0,0
        );
        try{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT

            );
            this.setPadding(toDp(4),toDp(4),toDp(4), toDp(4));
            this.setLayoutParams(params);
            label = new TextView(context);
            display = new TextView(context);
            if (this.getOrientation() == HORIZONTAL){
                this.setWeightSum(2);
                label.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1F));
                display.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1F));
            }else{
                label.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                display.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            display.setPadding(toDp(0), toDp(8),toDp(0),toDp(8));
            label.setPadding(toDp(0),toDp(8),toDp(0),toDp(8));
            label.setText(a.getString(R.styleable.Label_label_text));
            display.setText(a.getString(R.styleable.Label_display_text));
            display.setTypeface(Typeface.DEFAULT_BOLD);
            label.setGravity(Gravity.CENTER_VERTICAL);
            display.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.RIGHT);

            Drawable img = a.getDrawable(R.styleable.Label_drawable);
            if (img != null) {
                label.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                label.setCompoundDrawablePadding(toDp(4));
            }

            this.addView(label);
            this.addView(display);



        }finally{
            a.recycle();
        }
    }

    public void setDisplay(String displayText){
        display.setText(displayText);
        invalidate();
        requestLayout();
    }

    public void setLabel(String labelText){
        label.setText(labelText);
        invalidate();
        requestLayout();
    }

    private int toDp(float pixels){
        return (int)(pixels * SCALE + 0.5F);
    }
}

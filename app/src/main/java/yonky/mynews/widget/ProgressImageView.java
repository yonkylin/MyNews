package yonky.mynews.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/10/19.*/

/*public class ProgressImageView extends AppCompatImageView {
    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void start(){
        setVisibility(View.VISIBLE);
        Animatable animatable = (Animatable)getDrawable();
        if(!animatable.isRunning()){
            animatable.start();
        }
    }

    public void stop(){
        Animatable animatable =(Animatable)getDrawable();
        if(animatable.isRunning()){
            animatable.stop();
        }
        setVisibility(View.GONE);
    }


}*/
public class ProgressImageView extends LinearLayout {

    private int radius= 50;
    private float a=0;
    ObjectAnimator animator;

    public void setA(float a) {
        this.a = a;
        invalidate();
    }

    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle((float)Math.cos(a-Math.PI/2)*radius,(float)Math.sin(a-Math.PI/2)*radius,5,paint);
        canvas.translate(-getWidth()/2,-getHeight()/2);
    }

    public void start(){
        setVisibility(View.VISIBLE);
        animator = ObjectAnimator.ofFloat(this,"a",0,(float)Math.PI*2);

        if(!animator.isRunning()){
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(1000);
            animator.start();
        }
    }

    public void stop(){

        if(animator.isRunning()){
            animator.cancel();
        }
        setVisibility(View.GONE);
    }
}


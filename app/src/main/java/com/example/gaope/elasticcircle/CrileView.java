package com.example.gaope.elasticcircle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by gaope on 2018/5/10.
 */

public class CrileView extends View {

    private static final String TAG = "CrileView";

    private static float C = 0.551915024494f;
    private float radius = 80;
    private float length =radius * C;
    private Paint paint;
    private float[] point;
    private float[] control;
    private PointF center;
    private int a = 1;
    private int aa = 0;
    private int bb = 0;
    private int cc = 0;
    private int dd = 0;
    private int count = 4;

    public CrileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#ffeb5a5f"));
        point = new float[8];
        control = new float[16];
        center = new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = 100;
        center.y = h/2 - 300;

        Log.d(TAG,"wwidth:"+w);
        point[0] = center.x + radius;
        point[1] = center.y;
        point[2] = center.x;
        point[3] = center.y + radius;
        point[4] = center.x - radius;
        point[5] = center.y;
        point[6] = center.x;
        point[7] = center.y - radius;

        control[0] = point[0];
        control[1] = point[1] - length;
        control[2] = point[0];
        control[3] = point[1] + length;
        control[4] = point[2] + length;
        control[5] = point[3];
        control[6] = point[2] - length;
        control[7] = point[3];
        control[8] = point[4];
        control[9] = point[5] + length;
        control[10] = point[4];
        control[11] = point[5] - length;
        control[12] = point[6] - length;
        control[13] = point[7];
        control[14] = point[6] + length;
        control[15] = point[7];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(point[0],point[1]);
        path.cubicTo(control[2],control[3],control[4],control[5],point[2],point[3]);
        path.cubicTo(control[6],control[7],control[8],control[9],point[4],point[5]);
        path.cubicTo(control[10],control[11],control[12],control[13],point[6],point[7]);
        path.cubicTo(control[14],control[15],control[0],control[1],point[0],point[1]);
        canvas.drawPath(path,paint);

        if (a == 1){
            aa();
        }
        a++;

    }

    private void aa() {
            ValueAnimator valueAnima = ValueAnimator.ofFloat(center.x,getWidth() - 100);
            valueAnima.setDuration(4000);
            valueAnima.setInterpolator(new  DecelerateInterpolator());
            valueAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float center1 = (float) animation.getAnimatedValue();
                    Log.d(TAG,"center1:"+center1);

                    if (center1 <= 320){
                        point[0] = center1  + radius + aa;
                        point[2] = center1;
                        point[4] = center1 - radius;
                        point[6] = center1;


                        if (aa <= 35){
                            aa = aa + 1;
                        }

                    }else if (center1 > 320 && center1 <= 540){
                        point[0] = center1  + radius + aa;
                        point[2] = center1;
                        point[4] = center1 - radius - bb;
                        point[6] = center1;
                        if (bb <= 35){
                            bb = bb + 1;
                        }

                    }else if (center1 > 540 && center1 <= 760){
                        if (cc <= 35){
                            cc = cc + 1;
                        }
                        point[0] = center1  + radius + aa - cc;
                        point[2] = center1;
                        point[4] = center1 - radius - bb;
                        point[6] = center1;
                    }else if (center1 > 760){
                        if (dd <= 35){
                            dd = dd + 1;
                        }
                        point[0] = center1  + radius;
                        point[2] = center1;
                        point[4] = center1 - radius - bb + dd;
                        point[6] = center1;
                    }


                    control[0] = point[0];
                    control[1] = point[1] - length;
                    control[2] = point[0];
                    control[3] = point[1] + length;
                    control[4] = point[2] + length;
                    control[5] = point[3];
                    control[6] = point[2] - length;
                    control[7] = point[3];
                    control[8] = point[4];
                    control[9] = point[5] + length;
                    control[10] = point[4];
                    control[11] = point[5] - length;
                    control[12] = point[6] - length;
                    control[13] = point[7];
                    control[14] = point[6] + length;
                    control[15] = point[7];

                    Log.d(TAG,"point0:"+control[0]);
                    Log.d(TAG,"aa:"+aa);

                    invalidate();
                }
            });
            valueAnima.start();
        }




}

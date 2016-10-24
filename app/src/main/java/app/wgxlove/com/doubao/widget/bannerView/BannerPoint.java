package app.wgxlove.com.doubao.widget.bannerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import app.wgxlove.com.doubao.R;
import app.wgxlove.com.doubao.assistTool.ScreenTool;

/**
 * 　轮播图指示点
 */
public class BannerPoint extends View {

    private Paint mNormalPaint;
    private Paint mCurrentPaint;
    private int mNormalColor = ContextCompat.getColor(getContext(), R.color.color_999999);
    private int mCurrentColor = ContextCompat.getColor(getContext(), R.color.colorAccent);

    private float mNormalCircleRadius = 6.0f;
    private float mCurrentCircleRadius = 9.0f;

    private float mPointOffset = 28.0f;

    private int mPointNum = 0;
    private int position;

    private int mWidth;
    private int mStartPosition;

    public BannerPoint(Context context) {
        this(context, null);
    }

    public BannerPoint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWidth = ScreenTool.getScreenSize(getContext())[0];
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setColor(mNormalColor);
        mCurrentPaint = new Paint(mNormalPaint);
        mCurrentPaint.setColor(mCurrentColor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPointNum <= 1)
            return;
        canvas.save();
        mStartPosition = (int) (mWidth-((mPointNum - 1) * (mPointOffset + mNormalCircleRadius) + mCurrentCircleRadius)) / 2;
        Paint p = mNormalPaint;
        for (int i = 0; i < mPointNum; i++) {
            if (i == position) {
                p = mCurrentPaint;
            } else {
                p = mNormalPaint;
            }
            canvas.drawCircle(mStartPosition + i * mPointOffset,  ScreenTool.dp2px(getContext(),200), mNormalCircleRadius, p);
        }
        canvas.restore();
    }

    /**
     * 设置指示点参数
     *
     * @param n 　指示点个数
     * @param p 　当前位置
     */
    public void setPointParams(int n, int p) {
        if (n < 0)
            throw new RuntimeException("轮播图指示点不能小于０");
        mPointNum = n;
        position = p;
        invalidate();
    }

    /**
     * @param nColor 　默认指示点颜色
     * @param mColor 　当前指示点颜色
     */
    public void setPointColor(int nColor, int mColor) {
        mNormalColor = ContextCompat.getColor(getContext(), nColor);
        mCurrentColor = ContextCompat.getColor(getContext(), mColor);
        invalidate();
    }
}

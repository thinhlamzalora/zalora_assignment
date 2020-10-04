package zalora.assignment.presentation.views

import android.content.Context
import android.util.AttributeSet
import com.zolad.zoominimageview.ZoomInImageView

class RatioImage : ZoomInImageView {
    private var mHeightRatio = 0.0
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)

    //Here we will set the aspect ratio
    var heightRatio: Double
        get() = mHeightRatio
        set(ratio) {
            mHeightRatio = ratio
            requestLayout()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mHeightRatio > 0.0) {
            // set the image views size
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height = (width * mHeightRatio).toInt()
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
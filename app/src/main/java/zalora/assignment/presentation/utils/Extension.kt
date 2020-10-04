package zalora.assignment.presentation.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.zolad.zoominimageview.ZoomInImageView
import zalora.assignment.R
import zalora.assignment.data.utils.Constant.PICASO_TAG
import zalora.assignment.presentation.views.RatioImage

fun RatioImage.loadImage(url: String, widthItem: Int, ratio: Double, target: Target){
    heightRatio = ratio
    if (target != null) {
        Picasso.get().load(url).resize(widthItem, 0).noFade()
            .tag(PICASO_TAG).into(target)
    }else
    {
        Picasso.get().load(url).resize(widthItem, 0).noFade().placeholder(R.drawable.bg_white)
            .error(R.drawable.bg_white)
            .tag(PICASO_TAG).into(this)
    }
}

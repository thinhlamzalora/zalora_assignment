package zalora.assignment.presentation.list_cat

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.item_cat.view.*
import zalora.assignment.R
import zalora.assignment.domain.model.Cat
import zalora.assignment.presentation.utils.loadImage
import zalora.assignment.presentation.views.RatioImage

class CatAdapter(private val itemSpacing: Int) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    private val items: ArrayList<Cat> = ArrayList()



    private val onRefreshClick: ((Cat, Int, RatioImage, Target) -> Unit) = {
        cat, width, imageView, target ->  imageView.loadImage(cat.image, width, cat.heightRatio, target!!)
    }

    private val widthItem by lazy {
        (Resources.getSystem().displayMetrics.widthPixels - itemSpacing * 4) / 3;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        )

    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addDataSource(items: List<Cat>) {
        this.items.addAll(items)
        notifyItemRangeInserted(itemCount, items.size - 1);
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CatViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var target: Target? = null
        fun bind(cat: Cat) {
            itemView.apply {
                if (target == null) {
                    target = object : Target {
                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            progressBar.visibility = View.GONE
                            btnRefresh.visibility = View.GONE
                            ivCat.setImageBitmap(bitmap)
                            if (from == Picasso.LoadedFrom.NETWORK || from == Picasso.LoadedFrom.DISK){
                                ivCat.alpha = 0f
                                ivCat.animate().setDuration(3000).alpha(1f).start()
                            }
                        }

                        override fun onBitmapFailed(
                            e: java.lang.Exception?,
                            errorDrawable: Drawable?
                        ) {
                            progressBar.visibility = View.GONE
                            btnRefresh.visibility = View.VISIBLE
                            ivCat.setImageBitmap(null)
                        }

                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                            progressBar.visibility = View.VISIBLE
                            btnRefresh.visibility = View.GONE
                            ivCat.setImageBitmap(null)
                        }

                    }
                }
                ivCat.loadImage(cat.image, widthItem, cat.heightRatio, target!!)
                btnRefresh.setOnClickListener {
                    onRefreshClick!!(cat, widthItem, ivCat, target!!)
                }
            }
        }
    }

}
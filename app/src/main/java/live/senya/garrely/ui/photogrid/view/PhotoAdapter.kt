package live.senya.garrely.ui.photogrid.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo.view.*
import live.senya.garrely.R
import live.senya.garrely.entity.Photo
import live.senya.garrely.ui.common.adapter.DiffAdapter
import live.senya.garrely.util.extension.inflate

class PhotoAdapter(
        private val onPhotoClick: (Long) -> Unit
) : DiffAdapter<Photo, PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return parent.inflate(R.layout.item_photo)
                .let(::PhotoViewHolder)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Photo) = with(itemView) {
            setOnClickListener { onPhotoClick(item.id) }

            Glide.with(this)
                    .load(item.imageUrl)
                    .into(imageView)
        }
    }
}


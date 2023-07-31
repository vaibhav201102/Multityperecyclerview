package com.example.multityperecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class CustomRecyclerViewAdapter(private val items: List<RecyclerViewItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val players = mutableListOf<SimpleExoPlayer>()

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is RecyclerViewItem.VideoItem -> VIEW_TYPE_VIDEO
            is RecyclerViewItem.ImageItem -> VIEW_TYPE_IMAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_VIDEO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_video, parent, false)
                VideoViewHolder(view)
            }
            VIEW_TYPE_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_image, parent, false)
                ImageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is VideoViewHolder -> {
                val videoItem = item as RecyclerViewItem.VideoItem

                holder.bind(videoItem.videoUrl)
            }
            is ImageViewHolder -> {
                val imageItem = item as RecyclerViewItem.ImageItem
                // Bind image data to ImageViewHolder views
                Glide
                    .with(holder.itemView.context)
                    .load(imageItem.imageUrl)
                    .into(holder.imageView)

            }
        }
    }
    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerView: PlayerView = itemView.findViewById(R.id.videoPlayerView)
        private val player: SimpleExoPlayer = SimpleExoPlayer.Builder(itemView.context).build()

        init {
            playerView.player = player
            players.add(player)

        }

        fun bind(videoUrl: String) {
            val mediaItem = MediaItem.fromUri(videoUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageItem: RecyclerViewItem.ImageItem) {
            // Bind image data to ImageViewHolder views
            // For example, you can use a library like Glide or Picasso to load the image
            // Replace 'loadImage' with the actual method to load the image from the imageUrl
            // Example using Glide:
            // Glide.with(itemView.context).load(imageItem.imageUrl).into(imageView)

            // Set the title text
        }
    }
    override fun getItemCount(): Int = items.size

    companion object {
        private const val VIEW_TYPE_VIDEO = 0
        private const val VIEW_TYPE_IMAGE = 1
    }
}

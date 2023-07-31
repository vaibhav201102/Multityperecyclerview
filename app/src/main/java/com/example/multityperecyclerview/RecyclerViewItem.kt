package com.example.multityperecyclerview


sealed class RecyclerViewItem {
    data class VideoItem(val videoUrl: String, val title: String) : RecyclerViewItem()
    data class ImageItem(val imageUrl: String, val title: String) : RecyclerViewItem()
}

    // Initialize views for image item
    // e.g., val imageView = itemView.findViewById<ImageView>(R.id.imageView)

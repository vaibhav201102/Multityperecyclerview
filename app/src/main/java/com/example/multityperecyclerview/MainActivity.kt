package com.example.multityperecyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            RecyclerViewItem.VideoItem("https://media.istockphoto.com/id/850423346/video/portrait-of-smiling-female-owner-standing-arms-crossed-in-delicatessen.mp4?s=mp4-640x640-is&k=20&c=LZYGPPRhDWQJ03eIbufMpdlDXtNe41OSzo1c8XTUk-E=", "Video 1 Title"),
            RecyclerViewItem.ImageItem("https://images.unsplash.com/photo-1682686578023-dc680e7a3aeb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80", "Image 1 Title"),
            RecyclerViewItem.VideoItem("https://media.istockphoto.com/id/850423346/video/portrait-of-smiling-female-owner-standing-arms-crossed-in-delicatessen.mp4?s=mp4-640x640-is&k=20&c=LZYGPPRhDWQJ03eIbufMpdlDXtNe41OSzo1c8XTUk-E=", "Video 2 Title"),
            // Add more video and image items as needed
        )

        val recyclerView = findViewById<ViewPager2>(R.id.recyclerView)
        val adapter = CustomRecyclerViewAdapter(items)

        recyclerView?.offscreenPageLimit = 3

        recyclerView?.clipToPadding = false
        recyclerView?.clipChildren = false

        recyclerView?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        recyclerView?.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        recyclerView?.adapter = adapter
    }
}
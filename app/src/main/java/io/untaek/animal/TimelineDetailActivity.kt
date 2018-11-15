package io.untaek.animal

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import io.untaek.animal.component.Viewer
import io.untaek.animal.firebase.Post
import kotlinx.android.synthetic.main.activity_timeline_detail.*

class TimelineDetailActivity : AppCompatActivity() {
    private lateinit var post: Post
    private lateinit var viewer: Viewer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_detail)

        intent.getSerializableExtra("data")?.let { d ->
            post = d as Post

            viewer = Viewer(textureView, imageView).apply {
                changeSource(post.content)
            }

            textView_description.text = post.description
            textView_user_name.text = post.user.name
            textView_tags.text = post.tags.values.map { s -> "#$s " }.reduce { acc, s -> acc + s }

            button_go_back.setOnClickListener { finish() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //viewer.release()
    }
}

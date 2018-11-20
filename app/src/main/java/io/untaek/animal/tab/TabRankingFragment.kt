package io.untaek.animal.tab

import android.annotation.SuppressLint
import android.os.Bundle

import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.untaek.animal.R
import io.untaek.animal.list.ScrollUpdateCallback
import io.untaek.animal.list.ScrollUpdateListener
import io.untaek.animal.list.TabRankingRecyclerViewAdapter
import io.untaek.animal.firebase.Post
import io.untaek.animal.firebase.UserDetail
import io.untaek.animal.firebase.dummy
import kotlinx.android.synthetic.main.tab_rank.view.*


class TabRankingFragment: Fragment(), ScrollUpdateCallback {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TabRankingRecyclerViewAdapter
    private lateinit var posts : ArrayList<Post>
    private lateinit var users : ArrayList<UserDetail>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tab_rank, container, false)
        posts = dummy.postList
        users = dummy.usersDetail
        recyclerView = view.recyclerView_tab_rank
        recyclerView.layoutManager = GridLayoutManager(view.context, 1)
        adapter = TabRankingRecyclerViewAdapter(view.context, posts, users)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(ScrollUpdateListener(adapter, users, this))

        return view
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var THIS: TabRankingFragment? = null
        fun instance(): TabRankingFragment {
            if(THIS == null) {
                THIS = TabRankingFragment()
            }
            return THIS!!
        }
    }
    override fun callback() {
        adapter.update()
    }

}
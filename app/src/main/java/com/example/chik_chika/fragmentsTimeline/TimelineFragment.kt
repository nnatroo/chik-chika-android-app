package com.example.chik_chika.fragmentsTimeline

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chik_chika.R
import com.example.chik_chika.RecyclerViewTweetAdapter
import com.example.chik_chika.Tweets
import com.google.firebase.database.*
import java.lang.ref.PhantomReference

class TimelineFragment: Fragment(R.layout.fragment_timeline) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Tweets")

        getData()
    }


    private fun getData(){
        reference.addValueEventListener(object  : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var list= ArrayList<Tweets>()
                for(data in snapshot.children){
                    var model = data.getValue(Tweets::class.java)
                    list.add(model as Tweets)
                }
                if (list.size>0){
                    val adapter= RecyclerViewTweetAdapter(list)
                    recyclerView.adapter = adapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}
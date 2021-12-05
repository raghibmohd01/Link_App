package com.raghib.linkapp

import android.app.DownloadManager
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.raghib.linkapp.adapters.PostAdapter
import com.raghib.linkapp.daos.PostDao
import com.raghib.linkapp.models.Post
import java.util.*

lateinit var fab: FloatingActionButton
lateinit var rvHome: RecyclerView
class MainActivity : AppCompatActivity() {

    val postDao:PostDao= PostDao()
    lateinit var postAdapter:PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab=findViewById(R.id.fab)
        rvHome=findViewById(R.id.rvHome)
        
        fab.setOnClickListener {
            val intent=Intent(this,NewPost::class.java)
            startActivity(intent)
        }
        val query=postDao.postCollection.orderBy("createdAt", Query.Direction.DESCENDING)
        val rvOptions=FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()

        postAdapter=PostAdapter(rvOptions)
        rvHome.layoutManager=LinearLayoutManager(this)
        rvHome.adapter=postAdapter




    }

    override fun onStart() {
        super.onStart()
        postAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        postAdapter.stopListening()
    }
}
package com.raghib.linkapp.daos

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.raghib.linkapp.models.Post
import com.raghib.linkapp.models.User
import kotlinx.coroutines.launch

class PostDao {

    val  db=FirebaseFirestore.getInstance()
    val postCollection=db.collection("posts")
    val auth=Firebase.auth

   suspend fun addPost(text:String)
    {       val currentUser = auth.currentUser


            val post = Post()
                post.text = text
                post.createdAt=System.currentTimeMillis()

        if (currentUser != null) {
            post.user = User(currentUser.uid,currentUser?.displayName,currentUser?.photoUrl.toString())
        }
             postCollection.add(post)


    }



}
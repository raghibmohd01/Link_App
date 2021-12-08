package com.raghib.linkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.raghib.linkapp.daos.PostDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

lateinit var etPost: EditText
lateinit var btnPost: Button

class NewPost : AppCompatActivity() {
    private val postDao by lazy { PostDao() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        supportActionBar!!.title="New Post"

        etPost = findViewById(R.id.etPost)
        btnPost = findViewById<Button>(R.id.btnPost)


        btnPost.setOnClickListener {

            var inputText: String = etPost.text.toString().trim()

//                    if(inputText.isNotEmpty()) {
                        GlobalScope.launch {
                            inputText?.let {

                                postDao.addPost(it)

                            }
                        }

                        etPost.setText("")
                        Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show()
                    //}
            finish()

        }

    }
}
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
        etPost = findViewById(R.id.etPost)
        var inputText: String = etPost.text.toString().trim()
        btnPost = findViewById<Button>(R.id.btnPost)


        btnPost.setOnClickListener {



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
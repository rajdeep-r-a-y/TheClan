package com.example.sociquipo

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sociquipo.daos.PostDao
import com.example.sociquipo.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : AppCompatActivity(), IPostAdapter {

    private lateinit var adapter:PostAdapter
    private lateinit var postDao:PostDao
    lateinit var toggle : ActionBarDrawerToggle

    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)


        toggle= ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1d3557")))
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout -> {

                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                    alertDialog.setTitle("Log Out")
                    alertDialog.setMessage("Are you sure you want to log out?")
                    alertDialog.setPositiveButton(
                        "yes"
                    ) { _, _ ->
                        auth.signOut()
                        val intent= Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    alertDialog.setNegativeButton(
                        "No"
                    ) { _, _ -> }
                    val alert: AlertDialog = alertDialog.create()
                    alert.setCanceledOnTouchOutside(false)
                    alert.show()
                }
                R.id.about -> {
                    val intent= Intent(this, AboutMe::class.java)
                    startActivity(intent)

                }
            }
            true
        }
        fab.setOnClickListener{
            val intent= Intent(this, CreatePostActivity::class.java)
            startActivity(intent)

        }
        memeButton.setOnClickListener{
            val intent= Intent(this, MemePopUpWindow::class.java)
            startActivity(intent)

        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postCollections= postDao.postCollections
        val query= postCollections.orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerViewOptions= FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post::class.java).build()
        adapter=PostAdapter(recyclerViewOptions,this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes(postId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


}
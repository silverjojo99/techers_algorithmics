package com.example.algorithmics

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProjectsActivity : AppCompatActivity() {

    private lateinit var rvProjects: RecyclerView
    private lateinit var pbProjects: ProgressBar
    private lateinit var navHome: LinearLayout
    private lateinit var navCourses: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        bindViews()
        setupProjectGrid()
        setupProgressBar()
        setupBottomNav()
    }

    private fun bindViews() {
        rvProjects = findViewById(R.id.grid_projects)
        pbProjects = findViewById(R.id.pb_projects)
        navHome    = findViewById(R.id.nav_home)
        navCourses = findViewById(R.id.nav_courses)
    }

    // ── 8 project cards in a 2-column grid ────────────────────────────────────
    private fun setupProjectGrid() {
        val projects = listOf(
            GameCard(1, "Weather App",    4.5f, R.drawable.img_game_placeholder),
            GameCard(2, "Calculator",     4.2f, R.drawable.img_game_placeholder),
            GameCard(3, "To-do List",     4.8f, R.drawable.img_game_placeholder),
            GameCard(4, "Clock App",      4.0f, R.drawable.img_game_placeholder),
            GameCard(5, "Chat Bot",       4.6f, R.drawable.img_game_placeholder),
            GameCard(6, "Drawing App",    4.3f, R.drawable.img_game_placeholder),
            GameCard(7, "Flashcard App",  4.7f, R.drawable.img_game_placeholder),
            GameCard(8, "Quiz Builder",   4.1f, R.drawable.img_game_placeholder)
        )

        val adapter = GameCardAdapter(projects) { project ->
            // TODO: Open project detail screen
        }

        rvProjects.apply {
            layoutManager = GridLayoutManager(this@ProjectsActivity, 2)
            this.adapter  = adapter
        }
    }

    // ── Pink progress bar at bottom — 60% as shown in XML ─────────────────────
    private fun setupProgressBar() {
        pbProjects.progress = 60
    }

    private fun setupBottomNav() {
        navHome.setOnClickListener {
            startActivity(Intent(this, MainDashboardActivity::class.java))
            finish()
        }
    }
}

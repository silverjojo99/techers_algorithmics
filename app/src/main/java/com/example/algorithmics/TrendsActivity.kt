package com.example.algorithmics



import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrendsActivity : AppCompatActivity() {

    private lateinit var rvTrends: RecyclerView
    private lateinit var pbTrend1: ProgressBar
    private lateinit var pbTrend2: ProgressBar
    private lateinit var pbTrend3: ProgressBar
    private lateinit var navHome: LinearLayout
    private lateinit var navCourses: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trends)

        bindViews()
        setupGameGrid()
        setupProgressBars()
        setupBottomNav()
    }

    private fun bindViews() {
        rvTrends  = findViewById(R.id.grid_trends)   // We swap GridLayout → RecyclerView
        pbTrend1  = findViewById(R.id.pb_trend1)
        pbTrend2  = findViewById(R.id.pb_trend2)
        pbTrend3  = findViewById(R.id.pb_trend3)
        navHome   = findViewById(R.id.nav_home)
        navCourses = findViewById(R.id.nav_courses)
    }

    // ── 2-column grid of game cards ───────────────────────────────────────────
    // GridLayoutManager(context, spanCount=2) gives us 2 columns automatically.
    // This replaces the static GridLayout <include> tags from the XML preview.
    private fun setupGameGrid() {
        val games = listOf(
            GameCard(1, "Scratch Cat",    4.5f, R.drawable.img_game_placeholder),
            GameCard(2, "Snake Game",     4.2f, R.drawable.img_game_placeholder),
            GameCard(3, "Maze Solver",    4.8f, R.drawable.img_game_placeholder),
            GameCard(4, "Quiz App",       4.0f, R.drawable.img_game_placeholder),
            GameCard(5, "Card Flip",      4.6f, R.drawable.img_game_placeholder),
            GameCard(6, "Memory Match",   4.3f, R.drawable.img_game_placeholder),
            GameCard(7, "Bubble Sort",    4.7f, R.drawable.img_game_placeholder),
            GameCard(8, "Binary Search",  4.1f, R.drawable.img_game_placeholder)
        )

        val adapter = GameCardAdapter(games) { game ->
            // TODO: Open game detail screen
        }

        rvTrends.apply {
            layoutManager = GridLayoutManager(this@TrendsActivity, 2)
            this.adapter  = adapter
        }
    }

    // ── Progress bars — values match the XML (70, 50, 85) ────────────────────
    // In a real app these values would come from the server
    private fun setupProgressBars() {
        pbTrend1.progress = 70
        pbTrend2.progress = 50
        pbTrend3.progress = 85
    }

    private fun setupBottomNav() {
        navHome.setOnClickListener {
            startActivity(Intent(this, MainDashboardActivity::class.java))
            finish()
        }
        navCourses.setOnClickListener { /* TODO */ }
    }
}

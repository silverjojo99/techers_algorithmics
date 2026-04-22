package com.example.algorithmics

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainDashboardActivity : AppCompatActivity() {

    companion object {
        // Key used by LoginActivity when passing the username via Intent extras
        const val EXTRA_USERNAME = "extra_username"
    }

    // ── Views ──────────────────────────────────────────────────────────────────
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var tvUsername: TextView
    private lateinit var ivHamburger: ImageView
    private lateinit var rvLessons: RecyclerView
    private lateinit var drawerAvatar: ImageView
    private lateinit var drawerUsername: TextView

    // Drawer menu items
    private lateinit var menuMain: TextView
    private lateinit var menuCourse: TextView
    private lateinit var menuOnline: TextView
    private lateinit var menuCommunity: TextView
    private lateinit var menuLaboratory: TextView

    // Bottom nav
    private lateinit var navHome: LinearLayout
    private lateinit var navCourses: LinearLayout
    private lateinit var navCommunity: LinearLayout
    private lateinit var navLab: LinearLayout

    // Adapter
    private lateinit var lessonAdapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_drawer)
        // NOTE: We use activity_menu_drawer.xml as root because it wraps
        // the DrawerLayout around the dashboard content (as per the XML design).

        val username = intent.getStringExtra(EXTRA_USERNAME) ?: "Student"

        bindViews()
        setupUserInfo(username)
        setupLessonList()
        setupDrawer()
        setupBottomNav()
    }

    // ── Step 4a: Wire all view references ─────────────────────────────────────
    private fun bindViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        tvUsername = findViewById(R.id.tv_username)
        ivHamburger = findViewById(R.id.iv_hamburger)
        rvLessons = findViewById(R.id.rv_course_list)   // recycler inside dashboard content
        drawerUsername = findViewById(R.id.drawer_avatar)     // TextView in drawer header

        // Drawer menu items
        menuMain = findViewById(R.id.menu_item_main)
        menuCourse = findViewById(R.id.menu_item_course)
        menuOnline = findViewById(R.id.menu_item_online)
        menuCommunity = findViewById(R.id.menu_item_community)
        menuLaboratory = findViewById(R.id.menu_item_laboratory)

        // Bottom nav tabs
        navHome = findViewById(R.id.nav_home)
        navCourses = findViewById(R.id.nav_courses)
        navCommunity = findViewById(R.id.nav_community)
        navLab = findViewById(R.id.nav_lab)
    }

    // ── Step 4b: Show the logged-in user's name ────────────────────────────────
    private fun setupUserInfo(username: String) {
        tvUsername.text = username
        // Also show it in the drawer header
        // (drawer_avatar is an ImageView in XML, but the TextView next to it holds the name)
        // You'd set the separate TextView: drawerUsernameText.text = username
    }

    // ── Step 4c: RecyclerView with sample lesson data ──────────────────────────
    // In a real app this data would come from a ViewModel/Repository.
    private fun setupLessonList() {
        val sampleLessons = listOf(
            Lesson(
                1, "Python", "MK3: OOP, Objects Methods", isCurrent = true,
                dateInfo = "Mon 10 Apr", thumbnailRes = R.drawable.img_python_thumb
            ),
            Lesson(
                2, "Python", "MK2: Functions & Loops", isCurrent = false,
                dateInfo = "Wed 5 Apr", thumbnailRes = R.drawable.img_python_thumb
            ),
            Lesson(
                3, "Python", "MK1: Variables & Types", isCurrent = false,
                dateInfo = "Fri 1 Apr", thumbnailRes = R.drawable.img_python_thumb
            )
        )

        lessonAdapter = LessonAdapter(sampleLessons) { lesson ->
            // When user taps a lesson row → open CourseListActivity
            val intent = Intent(this, CourseListActivity::class.java).apply {
                putExtra(CourseListActivity.EXTRA_LESSON_ID, lesson.id)
            }
            startActivity(intent)
        }

        rvLessons.apply {
            layoutManager = LinearLayoutManager(this@MainDashboardActivity)
            adapter = lessonAdapter
            // Disable nested scrolling so the parent ScrollView handles scrolling
            isNestedScrollingEnabled = false
        }
    }

    // ── Step 4d: Drawer open/close + menu item clicks ─────────────────────────
    private fun setupDrawer() {
        // Hamburger icon opens the drawer
        ivHamburger.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Each menu item closes the drawer then navigates
        menuMain.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            // Already on Main — no navigation needed
        }

        menuCourse.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, CourseListActivity::class.java))
        }

        menuOnline.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            // TODO: Navigate to online lesson screen
        }

        menuCommunity.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            // TODO: Navigate to community screen
        }

        menuLaboratory.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            // TODO: Navigate to lab screen
        }
    }

    // ── Step 4e: Bottom navigation bar ────────────────────────────────────────
    private fun setupBottomNav() {
        navHome.setOnClickListener { handleNavClick(NavDestination.HOME) }
        navCourses.setOnClickListener { handleNavClick(NavDestination.COURSES) }
        navCommunity.setOnClickListener { handleNavClick(NavDestination.COMMUNITY) }
        navLab.setOnClickListener { handleNavClick(NavDestination.LAB) }
    }

    private fun handleNavClick(destination: NavDestination) {
        when (destination) {
            NavDestination.HOME -> { /* Already here */
            }

            NavDestination.COURSES -> startActivity(Intent(this, CourseListActivity::class.java))
            NavDestination.COMMUNITY -> { /* TODO: CommunityActivity */
            }

            NavDestination.LAB -> { /* TODO: LabActivity */
            }
        }
    }

    // Close drawer on back press if it's open
    @SuppressLint("GestureBackNavigation")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }
}
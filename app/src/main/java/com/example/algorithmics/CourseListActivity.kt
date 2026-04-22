package com.example.algorithmics

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CourseListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LESSON_ID = "extra_lesson_id"
    }

    private lateinit var rvCourseList: RecyclerView
    private lateinit var navHome: LinearLayout
    private lateinit var navCourses: LinearLayout
    private lateinit var navCommunity: LinearLayout
    private lateinit var navLab: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        bindViews()
        setupCourseList()
        setupBottomNav()
    }

    private fun bindViews() {
        rvCourseList = findViewById(R.id.rv_course_list)
        navHome      = findViewById(R.id.nav_home)
        navCourses   = findViewById(R.id.nav_courses)
        navCommunity = findViewById(R.id.nav_community)
        navLab       = findViewById(R.id.nav_lab)
    }

    // ── Sample data — in production this comes from a ViewModel ───────────────
    private fun setupCourseList() {
        val courses = listOf(
            Lesson(1, "Python", "MK1: Variables & Types",    isCurrent = false, "Mon 3 Apr",  R.drawable.img_python_thumb),
            Lesson(2, "Python", "MK2: Functions & Loops",    isCurrent = false, "Wed 5 Apr",  R.drawable.img_python_thumb),
            Lesson(3, "Python", "MK3: OOP, Objects Methods", isCurrent = true,  "Mon 10 Apr", R.drawable.img_python_thumb),
            Lesson(4, "Python", "MK4: File I/O",             isCurrent = false, "Wed 12 Apr", R.drawable.img_python_thumb),
            Lesson(5, "Python", "MK5: Error Handling",       isCurrent = false, "Mon 17 Apr", R.drawable.img_python_thumb)
        )

        val adapter = LessonAdapter(courses) { lesson ->
            // TODO: Open lesson detail screen
        }

        rvCourseList.apply {
            layoutManager = LinearLayoutManager(this@CourseListActivity)
            this.adapter  = adapter
        }
    }

    // ── Courses tab is highlighted as "active" here ────────────────────────────
    private fun setupBottomNav() {
        navHome.setOnClickListener { finish() }   // Go back to dashboard (HOME)
        navCourses.setOnClickListener   { /* Already here */ }
        navCommunity.setOnClickListener { /* TODO */ }
        navLab.setOnClickListener       { /* TODO */ }
    }
}

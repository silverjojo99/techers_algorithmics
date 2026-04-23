package com.example.techers_algorithmics

import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.algorithmics.R

/**
 * SCREEN 5 — Teacher Dashboard with tabs (All / by Group / by Lessons / by Students)
 */
class TeacherDashboardTabsActivity : AppCompatActivity() {

    private lateinit var tabAll: TextView
    private lateinit var tabByGroup: TextView
    private lateinit var tabByLessons: TextView
    private lateinit var tabByStudents: TextView
    private lateinit var spinnerGroup: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_dashboard_tabs)

        tabAll        = findViewById(R.id.tab_all2)
        tabByGroup    = findViewById(R.id.tab_by_group2)
        tabByLessons  = findViewById(R.id.tab_by_lessons2)
        tabByStudents = findViewById(R.id.tab_by_students2)
        spinnerGroup  = findViewById(R.id.spinner_group)

        setActiveTab(tabByGroup) // default active

        tabAll.setOnClickListener        { setActiveTab(it as TextView) }
        tabByGroup.setOnClickListener    { setActiveTab(it as TextView) }
        tabByLessons.setOnClickListener  { setActiveTab(it as TextView) }
        tabByStudents.setOnClickListener {
            // Navigate to students list
            startActivity(Intent(this, StudentListActivity::class.java))
        }

        // Lesson notes button
        findViewById<android.widget.Button>(R.id.btn_lesson_notes).setOnClickListener {
            // TODO: open lesson notes screen
        }
    }

    private fun setActiveTab(selected: TextView) {
        val allTabs = listOf(tabAll, tabByGroup, tabByLessons, tabByStudents)
        allTabs.forEach { tab ->
            if (tab == selected) {
                tab.setBackgroundResource(R.drawable.bg_tab_active)
                tab.setTextColor(getColor(R.color.white))
            } else {
                tab.setBackgroundResource(R.drawable.bg_tab_inactive)
                tab.setTextColor(getColor(R.color.text_dark))
            }
        }
    }
}

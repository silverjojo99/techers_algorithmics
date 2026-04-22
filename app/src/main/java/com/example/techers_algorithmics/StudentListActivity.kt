package com.example.techers_algorithmics

import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * SCREEN 6 — Students List
 * Shows a filterable RecyclerView of students (group + lesson spinners)
 */
class StudentListActivity : AppCompatActivity() {

    private lateinit var rvStudents: RecyclerView
    private lateinit var spinnerGroup: Spinner
    private lateinit var spinnerLesson: Spinner

    // Sample data – replace with real API/DB call
    private val students = listOf(
        StudentItem("Ahmed Ahmed",  "Group · Python", "Active"),
        StudentItem("Sara Ali",     "Group · Java",   "Active"),
        StudentItem("Youssef Omar", "Group · Web",    "Inactive")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        rvStudents    = findViewById(R.id.rv_students)
        spinnerGroup  = findViewById(R.id.spinner_student_group)
        spinnerLesson = findViewById(R.id.spinner_student_lesson)

        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = StudentListAdapter(students) { student ->
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("student_name", student.name)
            startActivity(intent)
        }

        // TODO: populate spinners from API
    }
}

/** Simple data class for a student row */
data class StudentItem(
    val name: String,
    val groupLesson: String,
    val status: String
)

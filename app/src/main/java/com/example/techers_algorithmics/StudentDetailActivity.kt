package com.example.techers_algorithmics

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.algorithmics.R

/**
 * SCREEN 7 & 8 — Student Detail Page
 * Shows student info, course info, and lesson progress
 */
class StudentDetailActivity : AppCompatActivity() {

    private lateinit var ivBack         : ImageView
    private lateinit var tvStudentName  : TextView
    private lateinit var tvStudentSub   : TextView
    private lateinit var tvGroupChip    : TextView
    private lateinit var tvStudentId    : TextView
    private lateinit var tvCategory     : TextView
    private lateinit var tvStudentGroup : TextView
    private lateinit var tvCourseName   : TextView
    private lateinit var tvCourseDesc   : TextView
    private lateinit var tvLocation     : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        ivBack          = findViewById(R.id.iv_back)
        tvStudentName   = findViewById(R.id.tv_student_name)
        tvStudentSub    = findViewById(R.id.tv_student_subtitle)
        tvGroupChip     = findViewById(R.id.tv_group_chip)
        tvStudentId     = findViewById(R.id.tv_student_id)
        tvCategory      = findViewById(R.id.tv_student_category)
        tvStudentGroup  = findViewById(R.id.tv_student_group)
        tvCourseName    = findViewById(R.id.tv_course_name)
        tvCourseDesc    = findViewById(R.id.tv_course_desc)
        tvLocation      = findViewById(R.id.tv_location)

        // Receive data from StudentListActivity
        val studentName = intent.getStringExtra("student_name") ?: "Ahmed Ahmed"
        tvStudentName.text = studentName

        // TODO: load full student data from API using student ID
        populateDummyData()

        ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun populateDummyData() {
        tvStudentSub.text   = "Student"
        tvGroupChip.text    = "Group"
        tvStudentId.text    = "123457"
        tvCategory.text     = "category id: 046.1"
        tvStudentGroup.text = "python class"
        tvCourseName.text   = "Python"
        tvCourseDesc.text   = "Python – starts December"
        tvLocation.text     = "Location Ahmed-Ahmed"
    }
}

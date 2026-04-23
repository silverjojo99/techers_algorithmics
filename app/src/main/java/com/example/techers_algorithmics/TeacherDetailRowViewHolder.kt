package com.example.techers_algorithmics

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.algorithmics.R

/**
 * Reusable ViewHolder for item_teacher_detail_row.xml
 * Used in both TeacherDashboardTabsActivity and StudentDetailActivity
 *
 * Usage:
 *   val holder = TeacherDetailRowViewHolder(rowView)
 *   holder.bind(label = "Lesson 1", val1 = "Present", val2 = "Absent", progress = 75)
 */
class TeacherDetailRowViewHolder(itemView: View) {

    private val tvLabel    : TextView    = itemView.findViewById(R.id.tv_detail_label)
    private val tvVal1     : TextView    = itemView.findViewById(R.id.tv_detail_val1)
    private val tvVal2     : TextView    = itemView.findViewById(R.id.tv_detail_val2)
    private val progressBar: ProgressBar = itemView.findViewById(R.id.pb_detail)

    fun bind(
        label   : String,
        val1    : String,
        val2    : String,
        progress: Int        // 0-100
    ) {
        tvLabel.text      = label
        tvVal1.text       = val1
        tvVal2.text       = val2
        progressBar.progress = progress
    }
}

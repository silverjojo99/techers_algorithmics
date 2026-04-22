package com.example.techers_algorithmics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter + ViewHolder for item_student_list_row.xml (Screen 6)
 */
class StudentListAdapter(
    private val items: List<StudentItem>,
    private val onClick: (StudentItem) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar  : ImageView = itemView.findViewById(R.id.iv_row_avatar)
        val tvName    : TextView  = itemView.findViewById(R.id.tv_row_name)
        val tvGroup   : TextView  = itemView.findViewById(R.id.tv_row_group)
        val tvStatus  : TextView  = itemView.findViewById(R.id.tv_row_status)

        fun bind(student: StudentItem) {
            tvName.text   = student.name
            tvGroup.text  = student.groupLesson
            tvStatus.text = student.status

            // Change badge color based on status
            val bgRes = if (student.status.equals("Active", ignoreCase = true))
                R.drawable.bg_badge_green
            else
                R.drawable.bg_badge_gray   // add this drawable for inactive
            tvStatus.setBackgroundResource(bgRes)

            itemView.setOnClickListener { onClick(student) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_list_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}

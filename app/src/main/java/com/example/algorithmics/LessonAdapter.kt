package com.example.algorithmics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.algorithmics.R
import com.example.algorithmics.Lesson

/**
 * Adapter for lesson rows (item_lesson_card.xml).
 * Used in: MainDashboardActivity and CourseListActivity.
 *
 * @param lessons    The list of lessons to display.
 * @param onItemClick Callback invoked when a row is tapped. Receives the Lesson.
 */
class LessonAdapter(
    private var lessons: List<Lesson>,
    private val onItemClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    // ── ViewHolder: holds references to every view inside one card ─────────────
    // We look up views ONCE here (not on every bind) — this is the performance
    // reason ViewHolder exists.
    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivThumb: ImageView    = itemView.findViewById(R.id.iv_course_thumb)
        val tvSubject: TextView   = itemView.findViewById(R.id.tv_subject)
        val tvUnitLabel: TextView = itemView.findViewById(R.id.tv_unit_label)
        val tvBadgeStart: TextView = itemView.findViewById(R.id.tv_badge_start)
        val tvBadgeDate: TextView = itemView.findViewById(R.id.tv_badge_date)
    }

    // ── Inflate the XML layout for each row ────────────────────────────────────
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson_card, parent, false)
        return LessonViewHolder(view)
    }

    // ── Bind data to views for the row at [position] ───────────────────────────
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]

        holder.ivThumb.setImageResource(lesson.thumbnailRes)
        holder.tvSubject.text   = lesson.subject
        holder.tvUnitLabel.text = lesson.unitLabel
        holder.tvBadgeDate.text = lesson.dateInfo

        // The green "current lesson" badge is only shown when isCurrent = true
        holder.tvBadgeStart.visibility = if (lesson.isCurrent) View.VISIBLE else View.GONE

        // Entire row is clickable
        holder.itemView.setOnClickListener { onItemClick(lesson) }
    }

    override fun getItemCount(): Int = lessons.size

    // ── Called by the Activity/Fragment when data changes ──────────────────────
    // DiffUtil would be better for large lists, but this is clean for this scale.
    fun updateLessons(newLessons: List<Lesson>) {
        lessons = newLessons
        notifyDataSetChanged()
    }
}

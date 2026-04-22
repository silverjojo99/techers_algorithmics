package com.example.algorithmics

/**
 * Represents a single lesson row shown in the dashboard and course list.
 * Maps to: item_lesson_card.xml
 */
data class Lesson(
    val id: Int,
    val subject: String,          // e.g. "Python" — shown in purple tag
    val unitLabel: String,        // e.g. "MK3: OOP, Objects Methods"
    val isCurrent: Boolean,       // controls green "current lesson" badge
    val dateInfo: String,         // shown in yellow badge
    val thumbnailRes: Int         // drawable resource id for the thumbnail
)

/**
 * Represents a game/project card shown in Trends and Projects screens.
 * Maps to: item_game_card.xml
 */
data class GameCard(
    val id: Int,
    val label: String,            // e.g. "Game 1"
    val rating: Float,            // e.g. 4.5 — shown next to star icon
    val thumbnailRes: Int         // drawable resource id
)

/**
 * Represents a goal item in the 2x2 goals grid on the dashboard.
 * Maps to: item_goals_grid.xml (each cell)
 */
data class GoalItem(
    val label: String,            // e.g. "Learning Path", "Achievements"
    val iconRes: Int              // drawable resource id
)

/**
 * The bottom nav destinations. We use an enum so nav logic is type-safe
 * and we avoid raw string/int comparisons everywhere.
 */
enum class NavDestination {
    HOME, COURSES, COMMUNITY, LAB
}

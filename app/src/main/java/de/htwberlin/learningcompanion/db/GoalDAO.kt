package de.htwberlin.learningcompanion.db

import androidx.room.*
import de.htwberlin.learningcompanion.model.Goal

@Dao
interface GoalDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoal(goal: Goal)

    @Update
    fun updateGoal(goal: Goal)

    @Delete
    fun deleteGoal(goal: Goal)

    @Query("SELECT * FROM goal WHERE id == :id")
    fun getGoalByID(id: Int): List<Goal>

    @Query("SELECT * FROM goal")
    fun getGoals(): List<Goal>
}
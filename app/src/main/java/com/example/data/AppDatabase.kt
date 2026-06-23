package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "doc_reminders")
data class DocReminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val docType: String, // "National ID", "Passport", "Driver License"
    val docNumber: String,
    val holderName: String,
    val expiryDate: String, // String representation format e.g., "2026-12-31" or user filled
    val isCompleted: Boolean = false
)

@Entity(tableName = "health_appointments")
data class HealthAppointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val reason: String,
    val facilityName: String, // e.g., "Kigali University Teaching Hospital (CHUK)"
    val date: String, // e.g., "2026-07-15"
    val time: String, // e.g., "10:00"
    val notes: String = ""
)

@Entity(tableName = "utility_reminders")
data class UtilityReminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val utilityType: String, // "REG Electricity", "WASAC Water", "Internet"
    val meterNumber: String,
    val amount: String,
    val dueDate: String
)

@Entity(tableName = "saved_opportunities")
data class SavedOpportunity(
    @PrimaryKey val opportunityId: String, // Unique ID for job/scholarship
    val title: String,
    val company: String,
    val type: String, // "Job" or "Scholarship"
    val deadline: String,
    val description: String,
    val link: String,
    val savedAt: Long = System.currentTimeMillis()
)

@Dao
interface HubDao {
    // Doc Reminders
    @Query("SELECT * FROM doc_reminders ORDER BY expiryDate ASC")
    fun getAllDocReminders(): Flow<List<DocReminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocReminder(reminder: DocReminder)

    @Delete
    suspend fun deleteDocReminder(reminder: DocReminder)

    // Health Appointments
    @Query("SELECT * FROM health_appointments ORDER BY date ASC, time ASC")
    fun getAllHealthAppointments(): Flow<List<HealthAppointment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthAppointment(appointment: HealthAppointment)

    @Delete
    suspend fun deleteHealthAppointment(appointment: HealthAppointment)

    // Utility Reminders
    @Query("SELECT * FROM utility_reminders ORDER BY dueDate ASC")
    fun getAllUtilityReminders(): Flow<List<UtilityReminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUtilityReminder(utility: UtilityReminder)

    @Delete
    suspend fun deleteUtilityReminder(utility: UtilityReminder)

    // Saved Opportunities
    @Query("SELECT * FROM saved_opportunities ORDER BY savedAt DESC")
    fun getAllSavedOpportunities(): Flow<List<SavedOpportunity>>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_opportunities WHERE opportunityId = :id)")
    suspend fun isOpportunitySaved(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedOpportunity(opp: SavedOpportunity)

    @Query("DELETE FROM saved_opportunities WHERE opportunityId = :id")
    suspend fun deleteSavedOpportunityById(id: String)
}

@Database(
    entities = [
        DocReminder::class,
        HealthAppointment::class,
        UtilityReminder::class,
        SavedOpportunity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hubDao(): HubDao
}

class HubRepository(private val hubDao: HubDao) {
    val docReminders: Flow<List<DocReminder>> = hubDao.getAllDocReminders()
    val healthAppointments: Flow<List<HealthAppointment>> = hubDao.getAllHealthAppointments()
    val utilityReminders: Flow<List<UtilityReminder>> = hubDao.getAllUtilityReminders()
    val savedOpportunities: Flow<List<SavedOpportunity>> = hubDao.getAllSavedOpportunities()

    suspend fun isOpportunitySaved(id: String): Boolean = hubDao.isOpportunitySaved(id)

    suspend fun insertDocReminder(reminder: DocReminder) = hubDao.insertDocReminder(reminder)
    suspend fun deleteDocReminder(reminder: DocReminder) = hubDao.deleteDocReminder(reminder)

    suspend fun insertHealthAppointment(appointment: HealthAppointment) = hubDao.insertHealthAppointment(appointment)
    suspend fun deleteHealthAppointment(appointment: HealthAppointment) = hubDao.deleteHealthAppointment(appointment)

    suspend fun insertUtilityReminder(utility: UtilityReminder) = hubDao.insertUtilityReminder(utility)
    suspend fun deleteUtilityReminder(utility: UtilityReminder) = hubDao.deleteUtilityReminder(utility)

    suspend fun insertSavedOpportunity(opp: SavedOpportunity) = hubDao.insertSavedOpportunity(opp)
    suspend fun deleteSavedOpportunityById(id: String) = hubDao.deleteSavedOpportunityById(id)
}

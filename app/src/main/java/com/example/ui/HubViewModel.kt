package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HubViewModel(application: Application) : AndroidViewModel(application) {

    // Database and Repository Setup
    private val database by lazy {
        Room.databaseBuilder(
            getApplication(),
            AppDatabase::class.java,
            "rwanda_service_hub_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    
    private val repository by lazy { HubRepository(database.hubDao()) }

    // App Navigation & Translation State
    val currentLanguage = MutableStateFlow<AppLanguage>(AppLanguage.EN)
    val selectedTab = MutableStateFlow<String>("services") // "services", "reminders", "opportunities", "civil_desk", "ai_guide"

    // Search and Filters
    val serviceSearchQuery = MutableStateFlow<String>("")
    val opportunitySearchQuery = MutableStateFlow<String>("")
    val activeFilter = MutableStateFlow<String>("All") // "All", "Job", "Scholarship", "Saved"

    // Room Database Flows
    val docReminders: StateFlow<List<DocReminder>> = repository.docReminders
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val healthAppointments: StateFlow<List<HealthAppointment>> = repository.healthAppointments
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val utilityReminders: StateFlow<List<UtilityReminder>> = repository.utilityReminders
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val savedOpportunities: StateFlow<List<SavedOpportunity>> = repository.savedOpportunities
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Set of Bookmarked Opportunity IDs
    val bookmarkedOpportunityIds: StateFlow<Set<String>> = repository.savedOpportunities
        .map { list -> list.map { it.opportunityId }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    // Intelligent Citizen AI Chat Guide State
    private val _chatMessages = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val chatMessages: StateFlow<List<Pair<String, Boolean>>> = _chatMessages.asStateFlow()

    private val _isChatLoading = MutableStateFlow<Boolean>(false)
    val isChatLoading: StateFlow<Boolean> = _isChatLoading.asStateFlow()

    init {
        // Prepopulate with a helpful language-specific welcome messages
        resetWelcomeMessage(AppLanguage.EN)
    }

    fun changeLanguage(language: AppLanguage) {
        currentLanguage.value = language
        resetWelcomeMessage(language)
    }

    private fun resetWelcomeMessage(lang: AppLanguage) {
        val welcome = when (lang) {
            AppLanguage.EN -> "Hello! I am your AI Citizen Guide для Rwanda. Ask me any question about administrative files, IremboGov, health centers, or public guidelines in Kigali."
            AppLanguage.RW -> "Muraho! Ndi Umuyobozi wawe wa AI mu Rwanda. Mbaza ikibazo cyose ku byangombwa, serivisi za Irembo, ibitaro cyangwa gahunda rusange mu rwatubyaye."
            AppLanguage.FR -> "Bonjour ! Je suis votre guide citoyen IA pour le Rwanda. Posez-moi vos questions sur l'administration, les procédures IremboGov ou les cliniques locales."
        }
        _chatMessages.value = listOf(welcome to false)
    }

    // Reminders Form Actions
    fun addDocReminder(docType: String, docNo: String, holder: String, expiry: String) {
        viewModelScope.launch {
            repository.insertDocReminder(
                DocReminder(
                    docType = docType,
                    docNumber = docNo,
                    holderName = holder,
                    expiryDate = expiry
                )
            )
        }
    }

    fun deleteDocReminder(reminder: DocReminder) {
        viewModelScope.launch {
            repository.deleteDocReminder(reminder)
        }
    }

    fun addHealthAppointment(reason: String, facility: String, date: String, time: String, notes: String) {
        viewModelScope.launch {
            repository.insertHealthAppointment(
                HealthAppointment(
                    reason = reason,
                    facilityName = facility,
                    date = date,
                    time = time,
                    notes = notes
                )
            )
        }
    }

    fun deleteHealthAppointment(appointment: HealthAppointment) {
        viewModelScope.launch {
            repository.deleteHealthAppointment(appointment)
        }
    }

    fun addUtilityReminder(type: String, meter: String, amount: String, date: String) {
        viewModelScope.launch {
            repository.insertUtilityReminder(
                UtilityReminder(
                    utilityType = type,
                    meterNumber = meter,
                    amount = amount,
                    dueDate = date
                )
            )
        }
    }

    fun deleteUtilityReminder(utility: UtilityReminder) {
        viewModelScope.launch {
            repository.deleteUtilityReminder(utility)
        }
    }

    // Opportunity bookmark management
    fun toggleOpportunityBookmark(opp: TranslationData.Opportunity) {
        viewModelScope.launch {
            val isBookmarked = bookmarkedOpportunityIds.value.contains(opp.id)
            if (isBookmarked) {
                repository.deleteSavedOpportunityById(opp.id)
            } else {
                repository.insertSavedOpportunity(
                    SavedOpportunity(
                        opportunityId = opp.id,
                        title = opp.titles[AppLanguage.EN] ?: "",
                        company = opp.institutions[AppLanguage.EN] ?: "",
                        type = opp.categories[AppLanguage.EN] ?: "Job",
                        deadline = opp.deadLines[AppLanguage.EN] ?: "",
                        description = opp.descriptions[AppLanguage.EN] ?: "",
                        link = opp.links
                    )
                )
            }
        }
    }

    // AI Chat Messaging
    fun sendChatMessage(message: String) {
        if (message.isBlank()) return
        
        // Append user massage
        _chatMessages.value = _chatMessages.value + (message to true)
        _isChatLoading.value = true

        viewModelScope.launch {
            try {
                val reply = GeminiAssistantService.askAssistant(message, currentLanguage.value)
                _chatMessages.value = _chatMessages.value + (reply to false)
            } catch (e: Exception) {
                val errorMsg = when (currentLanguage.value) {
                    AppLanguage.EN -> "Server issue occurs. Check connection or try later."
                    AppLanguage.RW -> "Habaye ikibazo mu ntoki cyangwa interineti. Gerageza mukanya."
                    AppLanguage.FR -> "Erreur serveur. Veuillez vérifier votre réseau."
                } + " (${e.localizedMessage})"
                _chatMessages.value = _chatMessages.value + (errorMsg to false)
            } finally {
                _isChatLoading.value = false
            }
        }
    }

    fun clearChat() {
        _chatMessages.value = emptyList()
        resetWelcomeMessage(currentLanguage.value)
    }
}

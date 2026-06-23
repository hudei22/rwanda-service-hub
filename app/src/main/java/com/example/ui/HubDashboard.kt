package com.example.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.data.*
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HubDashboardScreen(viewModel: HubViewModel) {
    val currentTab by viewModel.selectedTab.collectAsState()
    val currentLang by viewModel.currentLanguage.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Logo Box matching rounded-2xl bg-[#005CAB] with subtle border
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)), RoundedCornerShape(12.dp))
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_app_icon_1782201627092),
                                contentDescription = "App Logo",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = TranslationData.getUiString("app_title", currentLang),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                letterSpacing = (-0.5).sp
                            )
                            Text(
                                text = "Kigali Digital Citizen Desk",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                },
                actions = {
                    // Modern styled Language selection chips
                    Row(
                        modifier = Modifier.padding(end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AppLanguage.values().forEach { lang ->
                            val isSelected = lang == currentLang
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                                    )
                                    .clickable { viewModel.changeLanguage(lang) }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                                    .testTag("lang_btn_${lang.name.lowercase()}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = lang.name,
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                                    else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = currentTab,
                lang = currentLang,
                onTabSelected = { viewModel.selectedTab.value = it }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Highly Styled Warm Amber Offline Banner (matching the Quick Reminders/Alerts of Vibrant Palette HTML)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Circular icon box with standard transparent white overlay
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.4f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Active Offline Sync",
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Citizen Desk Sync",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                            Text(
                                text = TranslationData.getUiString("offline_notice", currentLang),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                when (currentTab) {
                    "services" -> GovServicesTab(viewModel, currentLang)
                    "reminders" -> TrackAndRemindTab(viewModel, currentLang)
                    "opportunities" -> OpportunitiesTab(viewModel, currentLang)
                    "civil_desk" -> CivilDeskTab(viewModel, currentLang)
                    "ai_guide" -> AiAssistantTab(viewModel, currentLang)
                }
            }
        }
    }
}

// ---------------------- SUB-TAB 1: GOV SERVICES ----------------------
@Composable
fun GovServicesTab(viewModel: HubViewModel, lang: AppLanguage) {
    val searchQuery by viewModel.serviceSearchQuery.collectAsState()
    val services = TranslationData.govServicesList
    val context = LocalContext.current

    val filteredServices = remember(searchQuery, lang) {
        services.filter { service ->
            val title = service.titles[lang] ?: ""
            val desc = service.descriptions[lang] ?: ""
            title.contains(searchQuery, ignoreCase = true) || desc.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // High Contrast Blue "Breaking News" Banner (From 'Vibrant Palette' Design HTML)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (lang == AppLanguage.RW) "AMAKURU MASHYA" else if (lang == AppLanguage.FR) "DERNIÈRES NOUVELLES" else "BREAKING NEWS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White.copy(alpha = 0.85f),
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = if (lang == AppLanguage.RW) "Irembo & Amavugurura ya Mutuelle - Byose Biragenda neza offline." 
                                   else if (lang == AppLanguage.FR) "Mises à jour Irembo & Mutuelle disponibles hors ligne."
                                   else "New offline services & instant renewal alerts active.",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 2.dp),
                            lineHeight = 16.sp
                        )
                    }
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Search Input with stylish extra-rounded boundary
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.serviceSearchQuery.value = it },
            placeholder = { Text(TranslationData.getUiString("search_placeholder", lang)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.serviceSearchQuery.value = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .testTag("gov_search_bar"),
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp, start = 14.dp, end = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredServices) { service ->
                var expanded by remember { mutableStateOf(false) }

                // Map specific Vibrant backgrounds and texts to create a cohesive colored card look
                val cardBg = when (service.id) {
                    "national_id" -> VibrantBlueBg
                    "e_passport" -> VibrantIndigoBg
                    "mutuelle" -> VibrantRoseBg
                    "driving_license" -> VibrantTealBg
                    "birth_certificate" -> VibrantBlueBg
                    else -> MaterialTheme.colorScheme.surface
                }
                val cardTextColor = when (service.id) {
                    "national_id" -> VibrantBlueText
                    "e_passport" -> VibrantIndigoText
                    "mutuelle" -> VibrantRoseText
                    "driving_license" -> VibrantTealText
                    "birth_certificate" -> VibrantBlueText
                    else -> MaterialTheme.colorScheme.onSurface
                }
                val categoryName = when (service.id) {
                    "national_id" -> "GOVERNMENT"
                    "e_passport" -> "PASSPORT & CITIZEN"
                    "mutuelle" -> "HEALTHCARE"
                    "driving_license" -> "TRANSPORT"
                    "birth_certificate" -> "CIVIL REGISTRY"
                    else -> "CIVIC HUB"
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .clickable { expanded = !expanded }
                        .testTag("service_card_${service.id}"),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(1.dp, cardTextColor.copy(alpha = 0.12f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // High contrast round-square icon box
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color.White.copy(alpha = 0.65f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = when (service.id) {
                                        "national_id" -> Icons.Default.AccountBox
                                        "e_passport" -> Icons.Default.Info
                                        "mutuelle" -> Icons.Default.Home
                                        "driving_license" -> Icons.Default.Build
                                        "birth_certificate" -> Icons.Default.Face
                                        else -> Icons.Default.List
                                    },
                                    contentDescription = null,
                                    tint = cardTextColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = categoryName,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = cardTextColor.copy(alpha = 0.75f),
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = service.titles[lang] ?: "",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = cardTextColor,
                                    lineHeight = 18.sp
                                )
                                Text(
                                    text = TranslationData.getUiString("irembo_hint", lang),
                                    fontSize = 11.sp,
                                    color = cardTextColor.copy(alpha = 0.6f)
                                )
                            }
                            Icon(
                                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "Expand details",
                                tint = cardTextColor.copy(alpha = 0.6f)
                            )
                        }

                        if (expanded) {
                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = cardTextColor.copy(alpha = 0.1f))
                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = service.descriptions[lang] ?: "",
                                fontSize = 13.sp,
                                color = cardTextColor.copy(alpha = 0.85f),
                                lineHeight = 17.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Metadata boxes with nice matching backdrop opacity
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                              ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            Color.White.copy(alpha = 0.6f),
                                            RoundedCornerShape(12.dp)
                                        )
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = TranslationData.getUiString("cost_label", lang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = cardTextColor
                                    )
                                    Text(
                                        text = service.costs[lang] ?: "",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = cardTextColor.copy(alpha = 0.85f)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            Color.White.copy(alpha = 0.6f),
                                            RoundedCornerShape(12.dp)
                                        )
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = TranslationData.getUiString("time_label", lang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = cardTextColor
                                    )
                                    Text(
                                        text = service.times[lang] ?: "",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = cardTextColor.copy(alpha = 0.85f)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = TranslationData.getUiString("requirements_label", lang),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = cardTextColor
                            )

                            // Steps list nicely spaced
                            val stepsList = service.steps[lang] ?: emptyList()
                            stepsList.forEachIndexed { idx, step ->
                                Row(
                                    modifier = Modifier.padding(top = 6.dp, start = 4.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = "${idx + 1}. ",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = cardTextColor
                                    )
                                    Text(
                                        text = step,
                                        fontSize = 12.sp,
                                        color = cardTextColor.copy(alpha = 0.85f),
                                        lineHeight = 16.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            // Elegant web launch portal trigger
                            Button(
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(service.iremboUrl))
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        Toast.makeText(context, "Could not launch browser", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = cardTextColor)
                            ) {
                                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.White)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = TranslationData.getUiString("visit_portal", lang),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ---------------------- SUB-TAB 2: TRACK & REMIND ----------------------
@Composable
fun TrackAndRemindTab(viewModel: HubViewModel, lang: AppLanguage) {
    val documents by viewModel.docReminders.collectAsState()
    val appointments by viewModel.healthAppointments.collectAsState()
    val utilities by viewModel.utilityReminders.collectAsState()

    var showFormDialog by remember { mutableStateOf(false) }
    var formTypeToOpen by remember { mutableStateOf<String>("doc") } // "doc", "health", "utility"

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    formTypeToOpen = "doc"
                    showFormDialog = true
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag("add_reminder_fab")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add reminder")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Highly Polished Tab Category Selector with capsules matching Design HTML
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("doc", "health", "utility").forEach { type ->
                    val isSelected = formTypeToOpen == type
                    val selectionColor = when (type) {
                        "doc" -> VibrantAmberText
                        "health" -> VibrantRoseText
                        "utility" -> VibrantTealText
                        else -> MaterialTheme.colorScheme.primary
                    }
                    val badgeBg = when (type) {
                        "doc" -> VibrantAmberSecondary
                        "health" -> VibrantRoseBg
                        "utility" -> VibrantTealBg
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (isSelected) badgeBg
                                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            )
                            .border(
                                1.dp,
                                if (isSelected) selectionColor.copy(alpha = 0.3f)
                                else Color.Transparent,
                                RoundedCornerShape(20.dp)
                            )
                            .clickable { formTypeToOpen = type }
                            .padding(vertical = 10.dp)
                            .testTag("sub_reminder_tab_$type"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (type) {
                                "doc" -> TranslationData.getUiString("national_id", lang)
                                "health" -> if (lang == AppLanguage.RW) "Amavururiro" else if (lang == AppLanguage.FR) "Santé" else "Health"
                                else -> if (lang == AppLanguage.RW) "Inyandiko" else if (lang == AppLanguage.FR) "Factures" else "Utility"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) selectionColor
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            // Empty state helper
            val isEmpty = when (formTypeToOpen) {
                "doc" -> documents.isEmpty()
                "health" -> appointments.isEmpty()
                else -> utilities.isEmpty()
            }

            if (isEmpty) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "",
                            modifier = Modifier.size(54.dp),
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = TranslationData.getUiString("empty_reminders", lang),
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (formTypeToOpen == "doc") {
                        items(documents) { doc ->
                            // Document Renewal Card - Matches design: bg-[#FFE082], rounded-3xl, amber text
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("doc_card_${doc.id}"),
                                colors = CardDefaults.cardColors(containerColor = VibrantAmberSecondary),
                                shape = RoundedCornerShape(24.dp),
                                border = BorderStroke(1.dp, VibrantAmberText.copy(alpha = 0.15f)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Circular/rounded square icon wrapper with transparent white
                                    Box(
                                        modifier = Modifier
                                            .size(44.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color.White.copy(alpha = 0.5f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.AccountBox,
                                            contentDescription = null,
                                            tint = VibrantAmberText,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "${doc.docType} (${doc.docNumber})",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 14.sp,
                                            color = VibrantAmberText
                                        )
                                        Text(
                                            text = doc.holderName,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = VibrantAmberText.copy(alpha = 0.8f)
                                        )
                                        Text(
                                            text = "${TranslationData.getUiString("expiry_date", lang)}: ${doc.expiryDate}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFB71C1C) // nice intense error red
                                        )

                                        val daysLeft = getDaysRemaining(doc.expiryDate)
                                        if (daysLeft.isNotEmpty()) {
                                            Text(
                                                text = daysLeft,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = VibrantAmberText
                                            )
                                        }
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteDocReminder(doc) },
                                        modifier = Modifier.testTag("del_doc_${doc.id}")
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFB71C1C))
                                    }
                                }
                            }
                        }
                    } else if (formTypeToOpen == "health") {
                        items(appointments) { appt ->
                            // Health Appointment Card - Matches design: bg-rose-100, rounded-3xl, rose text
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("appt_card_${appt.id}"),
                                colors = CardDefaults.cardColors(containerColor = VibrantRoseBg),
                                shape = RoundedCornerShape(24.dp),
                                border = BorderStroke(1.dp, VibrantRoseText.copy(alpha = 0.15f)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(44.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color.White.copy(alpha = 0.6f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Info,
                                            contentDescription = null,
                                            tint = VibrantRoseText,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = appt.reason,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 14.sp,
                                            color = VibrantRoseText
                                        )
                                        Text(
                                            text = appt.facilityName,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = VibrantRoseText.copy(alpha = 0.8f)
                                        )
                                        Text(
                                            text = "${appt.date} at ${appt.time}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = VibrantRoseText
                                        )
                                        if (appt.notes.isNotEmpty()) {
                                            Text(
                                                text = appt.notes,
                                                fontSize = 11.sp,
                                                color = VibrantRoseText.copy(alpha = 0.7f),
                                                lineHeight = 15.sp
                                            )
                                        }
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteHealthAppointment(appt) },
                                        modifier = Modifier.testTag("del_appt_${appt.id}")
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = VibrantRoseText)
                                    }
                                }
                            }
                        }
                    } else {
                        items(utilities) { util ->
                            // Utility Billing Reminder Card - Matches design: bg-teal-100, rounded-3xl, teal text
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("util_card_${util.id}"),
                                colors = CardDefaults.cardColors(containerColor = VibrantTealBg),
                                shape = RoundedCornerShape(24.dp),
                                border = BorderStroke(1.dp, VibrantTealText.copy(alpha = 0.15f)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(44.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color.White.copy(alpha = 0.6f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Check,
                                            contentDescription = null,
                                            tint = VibrantTealText,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = util.utilityType,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 14.sp,
                                            color = VibrantTealText
                                        )
                                        Text(
                                            text = "Meter/Account: ${util.meterNumber}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = VibrantTealText.copy(alpha = 0.8f)
                                        )
                                        Text(
                                            text = "Amount: RWF ${util.amount}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = VibrantTealText
                                        )
                                        Text(
                                            text = "Due Date: ${util.dueDate}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFB71C1C)
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteUtilityReminder(util) },
                                        modifier = Modifier.testTag("del_util_${util.id}")
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = VibrantTealText)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Input Forms Dialog
    if (showFormDialog) {
        Dialog(onDismissRequest = { showFormDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Headline choose form type inside dialog too
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "New Reminder",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { showFormDialog = false }) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    // Form type selector tabs
                    Row {
                        listOf("doc", "health", "utility").forEach { t ->
                            val isSelected = formTypeToOpen == t
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                        else Color.Transparent
                                    )
                                    .clickable { formTypeToOpen = t }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = t.uppercase(),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    if (formTypeToOpen == "doc") {
                        var docType by remember { mutableStateOf("National ID") }
                        var docNo by remember { mutableStateOf("") }
                        var docHolder by remember { mutableStateOf("") }
                        var docExpiry by remember { mutableStateOf("") }

                        Text(TranslationData.getUiString("doc_type", lang), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("National ID", "Passport", "Driver License").forEach { dt ->
                                val isSelected = docType == dt
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                                        .clickable { docType = dt }
                                        .padding(horizontal = 4.dp, vertical = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        dt,
                                        fontSize = 10.sp,
                                        color = if (isSelected) Color.White else Color.DarkGray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = docNo,
                            onValueChange = { docNo = it },
                            label = { Text(TranslationData.getUiString("doc_number", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("form_input_doc_id"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = docHolder,
                            onValueChange = { docHolder = it },
                            label = { Text(TranslationData.getUiString("holder_name", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("form_input_doc_name"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = docExpiry,
                            onValueChange = { docExpiry = it },
                            label = { Text("YYYY-MM-DD") },
                            placeholder = { Text("e.g. 2026-12-31") },
                            modifier = Modifier.fillMaxWidth().testTag("form_input_doc_expiry"),
                            singleLine = true
                        )

                        Button(
                            onClick = {
                                if (docNo.isNotBlank() && docHolder.isNotBlank() && docExpiry.isNotBlank()) {
                                    viewModel.addDocReminder(docType, docNo, docHolder, docExpiry)
                                    showFormDialog = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth().testTag("form_save_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(TranslationData.getUiString("btn_save", lang))
                        }
                    } else if (formTypeToOpen == "health") {
                        var reason by remember { mutableStateOf("") }
                        var facility by remember { mutableStateOf("") }
                        var date by remember { mutableStateOf("") }
                        var time by remember { mutableStateOf("") }
                        var notes by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = reason,
                            onValueChange = { reason = it },
                            label = { Text(TranslationData.getUiString("health_reason", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("appt_input_reason")
                        )

                        OutlinedTextField(
                            value = facility,
                            onValueChange = { facility = it },
                            label = { Text(TranslationData.getUiString("health_facility", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("appt_input_facility")
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = date,
                                onValueChange = { date = it },
                                label = { Text("Date (YYYY-MM-DD)") },
                                modifier = Modifier.weight(1f).testTag("appt_input_date")
                            )
                            OutlinedTextField(
                                value = time,
                                onValueChange = { time = it },
                                label = { Text("Time (HH:MM)") },
                                modifier = Modifier.weight(1f).testTag("appt_input_time")
                            )
                        }

                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            label = { Text(TranslationData.getUiString("health_notes", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("appt_input_notes")
                        )

                        Button(
                            onClick = {
                                if (reason.isNotBlank() && facility.isNotBlank() && date.isNotBlank()) {
                                    viewModel.addHealthAppointment(reason, facility, date, time, notes)
                                    showFormDialog = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth().testTag("appt_save_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(TranslationData.getUiString("btn_save", lang))
                        }
                    } else {
                        var utilityType by remember { mutableStateOf("REG Electricity") }
                        var meterNo by remember { mutableStateOf("") }
                        var amount by remember { mutableStateOf("") }
                        var dueDate by remember { mutableStateOf("") }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("REG Electricity", "WASAC Water", "Internet").forEach { ut ->
                                val isSelected = utilityType == ut
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                                        .clickable { utilityType = ut }
                                        .padding(vertical = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        ut.replace(" ", "\n"),
                                        fontSize = 9.sp,
                                        color = if (isSelected) Color.White else Color.DarkGray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = meterNo,
                            onValueChange = { meterNo = it },
                            label = { Text(TranslationData.getUiString("meter_number", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("util_input_meter")
                        )

                        OutlinedTextField(
                            value = amount,
                            onValueChange = { amount = it },
                            label = { Text(TranslationData.getUiString("utility_amount", lang)) },
                            modifier = Modifier.fillMaxWidth().testTag("util_input_amount")
                        )

                        OutlinedTextField(
                            value = dueDate,
                            onValueChange = { dueDate = it },
                            label = { Text("Due Date (YYYY-MM-DD)") },
                            modifier = Modifier.fillMaxWidth().testTag("util_input_due")
                        )

                        Button(
                            onClick = {
                                if (meterNo.isNotBlank() && dueDate.isNotBlank()) {
                                    viewModel.addUtilityReminder(utilityType, meterNo, amount, dueDate)
                                    showFormDialog = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth().testTag("util_save_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(TranslationData.getUiString("btn_save", lang))
                        }
                    }
                }
            }
        }
    }
}

// Help compute expiration remaining
fun getDaysRemaining(dateString: String): String {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val expiryDate = sdf.parse(dateString) ?: return ""
        val diff = expiryDate.time - System.currentTimeMillis()
        val days = TimeUnit.MILLISECONDS.toDays(diff)
        if (days < 0) "Expired" else "$days days remaining"
    } catch (e: Exception) {
        ""
    }
}

// ---------------------- SUB-TAB 3: OPPORTUNITIES ----------------------
@Composable
fun OpportunitiesTab(viewModel: HubViewModel, lang: AppLanguage) {
    val searchQuery by viewModel.opportunitySearchQuery.collectAsState()
    val activeFilter by viewModel.activeFilter.collectAsState()
    val bookmarkedIds by viewModel.bookmarkedOpportunityIds.collectAsState()
    val savedOpportunitiesList by viewModel.savedOpportunities.collectAsState()
    val context = LocalContext.current

    val rawOppsList = TranslationData.sampleOpportunities

    // Combine static list and any saved items offline
    val filteredOpps = remember(searchQuery, activeFilter, bookmarkedIds, savedOpportunitiesList, lang) {
        rawOppsList.filter { opp ->
            val matchesSearch = opp.titles[lang]?.contains(searchQuery, ignoreCase = true) == true ||
                    opp.institutions[lang]?.contains(searchQuery, ignoreCase = true) == true ||
                    opp.descriptions[lang]?.contains(searchQuery, ignoreCase = true) == true

            val matchesFilter = when (activeFilter) {
                "Job" -> opp.categories[AppLanguage.EN] == "Job"
                "Scholarship" -> opp.categories[AppLanguage.EN] == "Scholarship"
                "Saved" -> bookmarkedIds.contains(opp.id)
                else -> true
            }

            matchesSearch && matchesFilter
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // High Contrast Extra rounded Search Input
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.opportunitySearchQuery.value = it },
            placeholder = { Text(TranslationData.getUiString("opps_search_placeholder", lang)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.opportunitySearchQuery.value = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .testTag("opps_search_bar"),
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
        )

        // Switchable categories chips with high fidelity colors and pill layouts
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("All", "Job", "Scholarship", "Saved").forEach { filter ->
                val isSelected = activeFilter == filter
                val chipColor = when (filter) {
                    "Job" -> VibrantTealText
                    "Scholarship" -> VibrantIndigoText
                    "Saved" -> VibrantAmberText
                    else -> MaterialTheme.colorScheme.primary
                }
                val chipBg = when (filter) {
                    "Job" -> VibrantTealBg
                    "Scholarship" -> VibrantIndigoBg
                    "Saved" -> VibrantAmberSecondary
                    else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected) chipColor
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                        .clickable { viewModel.activeFilter.value = filter }
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .testTag("opp_filter_$filter"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (filter) {
                            "Job" -> if (lang == AppLanguage.RW) "Akazi" else if (lang == AppLanguage.FR) "Emplois" else "Jobs"
                            "Scholarship" -> if (lang == AppLanguage.RW) "Buruse" else "Scholarships"
                            "Saved" -> if (lang == AppLanguage.RW) "Bibitswe" else "Bookmarked"
                            else -> if (lang == AppLanguage.RW) "Byose" else "All"
                        },
                        color = if (isSelected) Color.White
                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (filteredOpps.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No active listings found.",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredOpps) { opp ->
                    val isBookmarked = bookmarkedIds.contains(opp.id)

                    // Alternate gorgeous card themes matching custom design
                    val cardBg = if (opp.categories[AppLanguage.EN] == "Job") VibrantTealBg else VibrantIndigoBg
                    val cardTextColor = if (opp.categories[AppLanguage.EN] == "Job") VibrantTealText else VibrantIndigoText

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("opp_card_${opp.id}"),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(1.dp, cardTextColor.copy(alpha = 0.12f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    // Pill Tag
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color.White.copy(alpha = 0.65f))
                                            .padding(horizontal = 10.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = (opp.categories[lang] ?: "").uppercase(),
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = cardTextColor,
                                            letterSpacing = 0.5.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = opp.titles[lang] ?: "",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = cardTextColor,
                                        lineHeight = 18.sp
                                    )
                                    Text(
                                        text = opp.institutions[lang] ?: "",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = cardTextColor.copy(alpha = 0.75f)
                                    )
                                }

                                // Interactive Bookmark Button
                                IconButton(
                                    onClick = { viewModel.toggleOpportunityBookmark(opp) },
                                    modifier = Modifier.testTag("bookmark_${opp.id}")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Save Offline",
                                        tint = if (isBookmarked) VibrantAmberText else Color.LightGray.copy(alpha = 0.6f)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = opp.descriptions[lang] ?: "",
                                fontSize = 12.sp,
                                color = cardTextColor.copy(alpha = 0.85f),
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(color = cardTextColor.copy(alpha = 0.1f))
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${TranslationData.getUiString("opps_deadline", lang)}: ${opp.deadLines[lang]}",
                                    fontSize = 11.sp,
                                    color = Color(0xFFB71C1C),
                                    fontWeight = FontWeight.Bold
                                )

                                Button(
                                    onClick = {
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(opp.links))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            Toast.makeText(context, "Cannot open portal link", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    shape = RoundedCornerShape(14.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = cardTextColor),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                                    modifier = Modifier.height(34.dp)
                                ) {
                                    Text(
                                        text = TranslationData.getUiString("opps_apply", lang),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ---------------------- SUB-TAB 4: CIVIL DESK (EMERGENCY & NEWS) ----------------------
@Composable
fun CivilDeskTab(viewModel: HubViewModel, lang: AppLanguage) {
    val contacts = TranslationData.emergencyContacts
    val alerts = TranslationData.civicAlerts
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // News Section Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = TranslationData.getUiString("alerts_subtitle", lang),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        items(alerts) { alert ->
            val isWarning = alert.type == "warning"
            val isSuccess = alert.type == "success"

            val cardBg = if (isWarning) VibrantRoseBg else if (isSuccess) VibrantTealBg else VibrantIndigoBg
            val textColor = if (isWarning) VibrantRoseText else if (isSuccess) VibrantTealText else VibrantIndigoText

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, textColor.copy(alpha = 0.12f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.65f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = alert.type.uppercase(),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = textColor,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Text(
                            text = alert.timestamp,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor.copy(alpha = 0.65f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = alert.titles[lang] ?: "",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        color = textColor,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = alert.contents[lang] ?: "",
                        fontSize = 12.sp,
                        color = textColor.copy(alpha = 0.85f),
                        lineHeight = 16.sp
                    )
                }
            }
        }

        // Emergency Hotline Header Section
        item {
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = TranslationData.getUiString("emergency_desk", lang),
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        items(contacts) { contact ->
            val desc = when (lang) {
                AppLanguage.EN -> contact.nameEn
                AppLanguage.RW -> contact.nameRw
                AppLanguage.FR -> contact.nameFr
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        try {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.number}"))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Cannot dial number", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .testTag("emergency_dial_${contact.number}"),
                colors = CardDefaults.cardColors(containerColor = VibrantRoseBg),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, VibrantRoseText.copy(alpha = 0.12f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(Color.White.copy(alpha = 0.65f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = when (contact.icon) {
                                    "local_police" -> Icons.Default.AccountBox
                                    "traffic" -> Icons.Default.Warning
                                    "local_fire_department" -> Icons.Default.Home
                                    "medical_services" -> Icons.Default.Info
                                    else -> Icons.Default.Phone
                                },
                                contentDescription = null,
                                tint = VibrantRoseText,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = desc,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp,
                                color = VibrantRoseText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Hotline: ${contact.number}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = VibrantRoseText.copy(alpha = 0.7f)
                            )
                        }
                    }

                    // Dial icon button in energetic style
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(VibrantRoseText, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = "Call", tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

// ---------------------- SUB-TAB 5: AI CLIENT GUIDE ----------------------
@Composable
fun AiAssistantTab(viewModel: HubViewModel, lang: AppLanguage) {
    val messages by viewModel.chatMessages.collectAsState()
    val isLoading by viewModel.isChatLoading.collectAsState()
    var inputMessage by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    // Scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ) {
        // Suggestions list for easy tap requests
        val suggestions = remember(lang) {
            when (lang) {
                AppLanguage.EN -> listOf(
                    "How to apply ID card on Irembo?",
                    "Where do I search for public scholarships?",
                    "What are Kigali road closures?"
                )
                AppLanguage.RW -> listOf(
                    "Nasaba gute Indangamuntu kuri Irembo?",
                    "Nabonye he inkunga ya buruse?",
                    "Imihanda ifunzwe Kigali ni iyihe?"
                )
                AppLanguage.FR -> listOf(
                    "Comment faire sa CNI sur Irembo?",
                    "Où postuler aux bourses d'études Hongrie?",
                    "Quelles routes fermées à Kigali?"
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Citizens AI Assistant Guide",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = if (lang == AppLanguage.RW) "Gusa Chat" else if (lang == AppLanguage.FR) "Effacer" else "Clear Chat",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB71C1C),
                modifier = Modifier
                    .clickable { viewModel.clearChat() }
                    .testTag("clear_chat_btn")
            )
        }

        // Suggestions row in horizontal scrolling capsule formats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            suggestions.forEach { sug ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(VibrantIndigoBg)
                        .border(1.dp, VibrantIndigoText.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                        .clickable {
                            inputMessage = sug
                            viewModel.sendChatMessage(sug)
                            inputMessage = ""
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = sug,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = VibrantIndigoText,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Messages thread list
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages) { msg ->
                val isUser = msg.second
                val content = msg.first

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp,
                                    bottomStart = if (isUser) 20.dp else 0.dp,
                                    bottomEnd = if (isUser) 0.dp else 20.dp
                                )
                            )
                            .background(
                                if (isUser) MaterialTheme.colorScheme.primary
                                else VibrantIndigoBg
                            )
                            .border(
                                width = 1.dp,
                                color = if (isUser) Color.Transparent else VibrantIndigoText.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp,
                                    bottomStart = if (isUser) 20.dp else 0.dp,
                                    bottomEnd = if (isUser) 0.dp else 20.dp
                                )
                            )
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                            .widthIn(max = 280.dp)
                    ) {
                        Text(
                            text = content,
                            color = if (isUser) Color.White else VibrantIndigoText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            if (isLoading) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = VibrantIndigoBg),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, VibrantIndigoText.copy(alpha = 0.12f))
                        ) {
                            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp, color = VibrantIndigoText)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Guide is drafting answer...", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = VibrantIndigoText)
                            }
                        }
                    }
                }
            }
        }

        // Input Field and dispatch button in premium round shape
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = inputMessage,
                onValueChange = { inputMessage = it },
                placeholder = { Text("Ask administrative steps...") },
                modifier = Modifier
                    .weight(1f)
                    .testTag("ai_chat_input_field"),
                shape = RoundedCornerShape(24.dp),
                maxLines = 3,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            )

            FloatingActionButton(
                onClick = {
                    if (inputMessage.isNotBlank()) {
                        viewModel.sendChatMessage(inputMessage)
                        inputMessage = ""
                    }
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier
                    .size(46.dp)
                    .testTag("ai_chat_send_button")
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send", modifier = Modifier.size(18.dp))
            }
        }
    }
}

// ---------------------- TAB BAR NAVIGATION ACTIONS ----------------------
@Composable
fun BottomNavigationBar(selectedTab: String, lang: AppLanguage, onTabSelected: (String) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        val tabs = listOf(
            Triple("services", Icons.Default.List, TranslationData.getUiString("tab_services", lang)),
            Triple("reminders", Icons.Default.Notifications, TranslationData.getUiString("tab_reminders", lang)),
            Triple("opportunities", Icons.Default.Star, TranslationData.getUiString("tab_opportunities", lang)),
            Triple("civil_desk", Icons.Default.Home, TranslationData.getUiString("tab_civil", lang)),
            Triple("ai_guide", Icons.Default.Face, TranslationData.getUiString("tab_assistant", lang))
        )

        tabs.forEach { (route, icon, label) ->
            NavigationBarItem(
                selected = selectedTab == route,
                onClick = { onTabSelected(route) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, fontSize = 9.sp, fontWeight = FontWeight.Bold, maxLines = 1) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                ),
                modifier = Modifier.testTag("bottom_tab_$route")
            )
        }
    }
}

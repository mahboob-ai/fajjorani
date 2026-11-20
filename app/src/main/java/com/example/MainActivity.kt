package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PresentToAll
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.PresentToAll
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.AgriLensViewModel
import com.example.ui.components.AgriLensLogoMark
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.DiagnoseScreen
import com.example.ui.screens.ExpertScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.PresentationScreen
import com.example.ui.theme.AgriLensTheme
import kotlinx.coroutines.launch

object AgriLensRoutes {
    const val ROUTE_HOME = "home"
    const val ROUTE_DIAGNOSE = "diagnose"
    const val ROUTE_EXPERT = "expert"
    const val ROUTE_LIBRARY = "library"
    const val ROUTE_HISTORY = "history"
    const val ROUTE_ABOUT = "about"
    const val ROUTE_PRESENTATION = "presentation"
}

private data class NavDestinationItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

private val primaryBottomDestinations = listOf(
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_HOME,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        testTag = "nav_home"
    ),
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_DIAGNOSE,
        label = "Diagnose",
        selectedIcon = Icons.Filled.CameraAlt,
        unselectedIcon = Icons.Outlined.CameraAlt,
        testTag = "nav_diagnose"
    ),
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_EXPERT,
        label = "AI Expert",
        selectedIcon = Icons.Filled.Psychology,
        unselectedIcon = Icons.Outlined.Psychology,
        testTag = "nav_expert"
    ),
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_LIBRARY,
        label = "Library",
        selectedIcon = Icons.Filled.MenuBook,
        unselectedIcon = Icons.Outlined.MenuBook,
        testTag = "nav_library"
    ),
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_HISTORY,
        label = "History",
        selectedIcon = Icons.Filled.History,
        unselectedIcon = Icons.Outlined.History,
        testTag = "nav_history"
    )
)

private val allDestinations = primaryBottomDestinations + listOf(
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_ABOUT,
        label = "About",
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
        testTag = "nav_about"
    ),
    NavDestinationItem(
        route = AgriLensRoutes.ROUTE_PRESENTATION,
        label = "Slides",
        selectedIcon = Icons.Filled.PresentToAll,
        unselectedIcon = Icons.Outlined.PresentToAll,
        testTag = "nav_presentation"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // STEP 1: Configure App Check with launch intent before UI, ViewModels, or Firebase calls
        configureAppCheck(this, intent)

        enableEdgeToEdge()
        setContent {
            AgriLensTheme {
                AgriLensAppRoot()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgriLensAppRoot(viewModel: AgriLensViewModel = viewModel()) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AgriLensRoutes.ROUTE_HOME
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val diagnosisState by viewModel.diagnosisState.collectAsStateWithLifecycle()
    val expertState by viewModel.expertState.collectAsStateWithLifecycle()
    val libraryState by viewModel.libraryState.collectAsStateWithLifecycle()
    val diagnosisHistory by viewModel.diagnosisHistory.collectAsStateWithLifecycle()
    val chatMessages by viewModel.chatMessages.collectAsStateWithLifecycle()
    val selectedHistoryRecord by viewModel.selectedHistoryRecord.collectAsStateWithLifecycle()
    val expertLanguage by viewModel.expertLanguage.collectAsStateWithLifecycle()
    val currentSlideIndex by viewModel.currentSlideIndex.collectAsStateWithLifecycle()
    val showSpeakerNotes by viewModel.showSpeakerNotes.collectAsStateWithLifecycle()

    fun navigateTo(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val isPresentation = currentRoute == AgriLensRoutes.ROUTE_PRESENTATION

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useNavigationRail = maxWidth >= 720.dp

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = !useNavigationRail && !isPresentation,
            drawerContent = {
                ModalDrawerSheet {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AgriLensLogoMark(size = 38.dp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "AgriLens AI",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Team BioVision • SDG 2",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                    allDestinations.forEach { dest ->
                        val selected = currentRoute == dest.route
                        NavigationDrawerItem(
                            selected = selected,
                            onClick = {
                                coroutineScope.launch { drawerState.close() }
                                navigateTo(dest.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    when (dest.route) {
                                        AgriLensRoutes.ROUTE_ABOUT -> "About Project"
                                        AgriLensRoutes.ROUTE_PRESENTATION -> "Presentation Mode (8 Slides)"
                                        else -> dest.label
                                    }
                                )
                            },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        ) {
            Scaffold(
                contentWindowInsets = WindowInsets.safeDrawing,
                topBar = {
                    if (!isPresentation) {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            navigationIcon = {
                                if (!useNavigationRail) {
                                    IconButton(
                                        onClick = { coroutineScope.launch { drawerState.open() } },
                                        modifier = Modifier.testTag("open_drawer_button")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = "Open navigation menu"
                                        )
                                    }
                                }
                            },
                            title = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    AgriLensLogoMark(size = 28.dp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "AgriLens AI",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(
                                            text = "Let the Leaf Tell the Story.",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = { navigateTo(AgriLensRoutes.ROUTE_ABOUT) },
                                    modifier = Modifier.testTag("topbar_about_button")
                                ) {
                                    Icon(
                                        imageVector = if (currentRoute == AgriLensRoutes.ROUTE_ABOUT) {
                                            Icons.Filled.Info
                                        } else {
                                            Icons.Outlined.Info
                                        },
                                        contentDescription = "About Project",
                                        tint = if (currentRoute == AgriLensRoutes.ROUTE_ABOUT) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.onSurfaceVariant
                                        }
                                    )
                                }
                                IconButton(
                                    onClick = { navigateTo(AgriLensRoutes.ROUTE_PRESENTATION) },
                                    modifier = Modifier.testTag("topbar_presentation_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.PresentToAll,
                                        contentDescription = "Presentation Mode",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    if (!useNavigationRail && !isPresentation) {
                        NavigationBar {
                            primaryBottomDestinations.forEach { dest ->
                                val selected = currentRoute == dest.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { navigateTo(dest.route) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                                            contentDescription = dest.label
                                        )
                                    },
                                    label = { Text(dest.label) },
                                    modifier = Modifier.testTag(dest.testTag)
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (useNavigationRail && !isPresentation) {
                        NavigationRail(modifier = Modifier.fillMaxHeight()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            allDestinations.forEach { dest ->
                                val selected = currentRoute == dest.route
                                NavigationRailItem(
                                    selected = selected,
                                    onClick = { navigateTo(dest.route) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                                            contentDescription = dest.label
                                        )
                                    },
                                    label = { Text(dest.label) },
                                    modifier = Modifier.testTag("rail_${dest.testTag}")
                                )
                            }
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = AgriLensRoutes.ROUTE_HOME,
                        modifier = Modifier.weight(1f)
                    ) {
                        composable(AgriLensRoutes.ROUTE_HOME) {
                            HomeScreen(
                                onStartDiagnosis = { navigateTo(AgriLensRoutes.ROUTE_DIAGNOSE) },
                                onAskExpert = { navigateTo(AgriLensRoutes.ROUTE_EXPERT) },
                                onOpenLibraryCategory = { cat ->
                                    viewModel.selectLibraryCategory(cat)
                                    viewModel.selectLibraryCrop(null)
                                    navigateTo(AgriLensRoutes.ROUTE_LIBRARY)
                                },
                                onOpenAbout = { navigateTo(AgriLensRoutes.ROUTE_ABOUT) },
                                onOpenPresentation = { navigateTo(AgriLensRoutes.ROUTE_PRESENTATION) }
                            )
                        }

                        composable(AgriLensRoutes.ROUTE_DIAGNOSE) {
                            DiagnoseScreen(
                                uiState = diagnosisState,
                                onSelectCategory = viewModel::selectDiagnosisCategory,
                                onSelectCrop = viewModel::selectDiagnosisCrop,
                                onUpdateCropSearch = viewModel::updateDiagnosisCropSearch,
                                onImageSelected = viewModel::onDiagnosisImageSelected,
                                onRemoveImage = viewModel::removeDiagnosisImage,
                                onAnalyseLeaf = viewModel::analyseLeaf,
                                onResetForAnotherLeaf = viewModel::resetDiagnosisForAnotherLeaf,
                                onAskExpertWithResult = { assessment ->
                                    viewModel.prepareExpertQuestionFromDiagnosis(assessment)
                                    navigateTo(AgriLensRoutes.ROUTE_EXPERT)
                                }
                            )
                        }

                        composable(AgriLensRoutes.ROUTE_EXPERT) {
                            ExpertScreen(
                                uiState = expertState,
                                messages = chatMessages,
                                preferredLanguage = expertLanguage,
                                onSetLanguage = viewModel::setExpertLanguage,
                                onUpdateDraft = viewModel::updateExpertDraft,
                                onImageSelected = viewModel::onExpertImageSelected,
                                onRemoveAttachedImage = viewModel::removeExpertAttachedImage,
                                onSendMessage = viewModel::sendExpertMessage,
                                onClearChat = viewModel::clearChatHistory,
                                onDismissError = viewModel::dismissExpertError,
                                onToggleFallbackFaqs = viewModel::toggleFallbackFaqs
                            )
                        }

                        composable(AgriLensRoutes.ROUTE_LIBRARY) {
                            LibraryScreen(
                                uiState = libraryState,
                                onUpdateSearch = viewModel::updateLibrarySearch,
                                onSelectCategory = viewModel::selectLibraryCategory,
                                onSelectCrop = viewModel::selectLibraryCrop,
                                onDiagnoseCrop = { crop ->
                                    viewModel.prepareDiagnosisForCrop(crop)
                                    navigateTo(AgriLensRoutes.ROUTE_DIAGNOSE)
                                },
                                onAskExpertAboutCrop = { crop ->
                                    viewModel.prepareExpertQuestionForCrop(crop)
                                    navigateTo(AgriLensRoutes.ROUTE_EXPERT)
                                }
                            )
                        }

                        composable(AgriLensRoutes.ROUTE_HISTORY) {
                            HistoryScreen(
                                diagnoses = diagnosisHistory,
                                chatMessages = chatMessages,
                                selectedRecord = selectedHistoryRecord,
                                onOpenRecord = viewModel::openHistoryRecord,
                                onDeleteRecord = viewModel::deleteDiagnosisRecord,
                                onClearAllHistory = viewModel::clearAllHistory,
                                onAskExpertWithResult = { assessment ->
                                    viewModel.prepareExpertQuestionFromDiagnosis(assessment)
                                    navigateTo(AgriLensRoutes.ROUTE_EXPERT)
                                },
                                onStartNewDiagnosis = { navigateTo(AgriLensRoutes.ROUTE_DIAGNOSE) },
                                onOpenExpertChat = { navigateTo(AgriLensRoutes.ROUTE_EXPERT) }
                            )
                        }

                        composable(AgriLensRoutes.ROUTE_ABOUT) {
                            AboutScreen(
                                onOpenPresentationMode = { navigateTo(AgriLensRoutes.ROUTE_PRESENTATION) }
                            )
                        }

                        composable(AgriLensRoutes.ROUTE_PRESENTATION) {
                            PresentationScreen(
                                currentSlideIndex = currentSlideIndex,
                                showSpeakerNotes = showSpeakerNotes,
                                onPreviousSlide = viewModel::previousSlide,
                                onNextSlide = viewModel::nextSlide,
                                onGoToSlide = viewModel::goToSlide,
                                onToggleSpeakerNotes = viewModel::toggleSpeakerNotes,
                                onJumpToDiagnose = { navigateTo(AgriLensRoutes.ROUTE_DIAGNOSE) },
                                onJumpToExpert = { navigateTo(AgriLensRoutes.ROUTE_EXPERT) },
                                onExitPresentation = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

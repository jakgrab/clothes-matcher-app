package com.example.clothesmatcher.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.compose.composable
import com.example.clothesmatcher.screens.landing.LandingScreen
import com.example.clothesmatcher.screens.loading.LoadingScreen
import com.example.clothesmatcher.screens.main.main.MainScreen
import com.example.clothesmatcher.screens.main.main.MainViewModel
import com.example.clothesmatcher.screens.match.MatchingScreen
import com.example.clothesmatcher.screens.options.OptionsScreen
import com.example.clothesmatcher.screens.options.OptionsViewModel
import com.example.clothesmatcher.screens.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClothesNavigation() {

    val animatedNavController = rememberAnimatedNavController()
    val mainViewModel = hiltViewModel<MainViewModel>()
    //val optionsViewModel = hiltViewModel<OptionsViewModel>()

    AnimatedNavHost(
        navController = animatedNavController,
        startDestination = ClothesScreens.SplashScreen.name
    ) {
        composable(
            route = ClothesScreens.SplashScreen.name,
            enterTransition = {
                fadeIn(animationSpec = tween(200))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(200))
            }
        ) {
            SplashScreen(navController = animatedNavController)
        }
        composable(
            route = ClothesScreens.LandingScreen.name,
            enterTransition = {
                if (initialState.destination.hierarchy.any {
                        it.route == ClothesScreens.SplashScreen.name }
                ) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            exitTransition = {
                if (targetState.destination.hierarchy.any {
                        it.route == ClothesScreens.MainScreen.name }
                ) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
        ) {
            LandingScreen(navController = animatedNavController)
        }
        composable(
            route = ClothesScreens.MainScreen.name,
            enterTransition = {
                if (initialState.destination.hierarchy.any {
                    it.route == ClothesScreens.LandingScreen.name
                    }) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            exitTransition = {
                if (targetState.destination.hierarchy.any {
                    it.route == ClothesScreens.MatchingScreen.name
                    }) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popEnterTransition = {
                if (initialState.destination.hierarchy.any {
                    it.route == ClothesScreens.MatchingScreen.name
                    }) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popExitTransition = {
                if (initialState.destination.hierarchy.any {
                        it.route == ClothesScreens.MatchingScreen.name
                    }) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            }
        ) { MainScreen(mainViewModel = mainViewModel, navController = animatedNavController) }

        composable(route = ClothesScreens.OptionsScreen.name) {
            OptionsScreen()//optionsViewModel = optionsViewModel, navController = animatedNavController)
        }

        composable(
            route = ClothesScreens.MatchingScreen.name,
            enterTransition = {
                if (initialState.destination.hierarchy.any {
                        it.route == ClothesScreens.MainScreen.name
                    }) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            exitTransition = {
                if (targetState.destination.hierarchy.any {
                        it.route == ClothesScreens.MainScreen.name
                    }) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left, // TODO maybe right?
                        animationSpec = tween(700)
                    )
                } else null
            },
            popEnterTransition = {
                if (initialState.destination.hierarchy.any {
                        it.route == ClothesScreens.MatchingScreen.name
                    }) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popExitTransition = {
                if (initialState.destination.hierarchy.any {
                        it.route == ClothesScreens.MatchingScreen.name
                    }) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            }
        ) { MatchingScreen(navController = animatedNavController, viewModel = mainViewModel) }

        composable(
            route = ClothesScreens.LoadingScreen.name,
            enterTransition = {
                if (initialState.destination.hierarchy.any {
                        it.route == ClothesScreens.MainScreen.name
                    }) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            exitTransition = {
                if (targetState.destination.hierarchy.any {
                        it.route == ClothesScreens.MatchingScreen.name
                    }) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            }
        ) {
            LoadingScreen(navController = animatedNavController, viewModel = mainViewModel)
        }
    }
}
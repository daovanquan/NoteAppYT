package com.example.noteappyt.navigation.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.noteappyt.feature.note.presentation.FavoriteScreen
import com.example.noteappyt.navigation.Screen
import com.example.noteappyt.navigation.Tab

fun NavGraphBuilder.favorites(navController: NavController){
    navigation(
        startDestination = Screen.FavoriteScreen.route,
        route = Tab.Favorites.route
    ){
        composable(
            route = Screen.FavoriteScreen.route
        ){
            FavoriteScreen(
                navController = navController,
                onEditNoteClick = { noteId ->
                    navController.navigate(
                        route = "${Screen.AddEditNoteScreen.route}/$noteId"
                    )
                })
        }
    }
}
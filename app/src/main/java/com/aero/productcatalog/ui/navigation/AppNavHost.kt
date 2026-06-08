package com.aero.productcatalog.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aero.productcatalog.MainViewModel
import com.aero.productcatalog.ui.features.account.AccountScreen
import com.aero.productcatalog.ui.features.addCategory.AddCategoryScreen
import com.aero.productcatalog.ui.features.cart.CartScreen
import com.aero.productcatalog.ui.features.favorites.FavoritesScreen
import com.aero.productcatalog.ui.features.productDetail.DetailScreen
import com.aero.productcatalog.ui.features.productstore.ProductStoreScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    navigationCallbacks: NavigationCallbacks,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.HOME.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(NavScreens.HOME.route) {
            ProductStoreScreen(
                navigationCallbacks = navigationCallbacks,
                onThemeChange = mainViewModel::onThemeChange,
                productStoreViewModel = hiltViewModel()
            )
        }
        composable(NavScreens.FAVORITES.route) {
            FavoritesScreen(
                navigationCallbacks = navigationCallbacks,
                favoritesViewModel = hiltViewModel()
            )
        }
        composable("detail/{productId}") {
            DetailScreen(
                navigationCallbacks = navigationCallbacks,
                detailViewModel = hiltViewModel()
            )
        }
        composable(NavScreens.CART.route) {
            CartScreen(
                navigationCallbacks = navigationCallbacks,
                cartViewModel = hiltViewModel(),
                onCheckoutSuccess = {
                    navigationCallbacks.navigateToHome()
                }
            )
        }
        composable(NavScreens.ACCOUNT.route) {
            AccountScreen(
                navigationCallbacks = navigationCallbacks,
                viewModel = hiltViewModel()
            )
        }
        composable(NavScreens.ADD_CATEGORY.route) {
            AddCategoryScreen(
                navigationCallbacks = navigationCallbacks,
                viewModel = hiltViewModel()
            )
        }
    }
}

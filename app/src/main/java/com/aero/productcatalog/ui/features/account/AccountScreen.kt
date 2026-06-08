package com.aero.productcatalog.ui.features.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.aero.productcatalog.ui.features.account.components.AccountHeader
import com.aero.productcatalog.ui.features.account.components.AccountOption
import com.aero.productcatalog.ui.navigation.NavigationCallbacks

@Composable
fun AccountScreen(
    navigationCallbacks: NavigationCallbacks
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader(
            userName = "Usuario Demo",
            userEmail = "usuario@ejemplo.com"
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Acciones",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(25))
        ) {
            Column {
                AccountOption(
                    title = "Vender un producto",
                    icon = Icons.Default.Sell,
                    onClick = { /* Próximamente */ }
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                AccountOption(
                    title = "Aumentar una categoría",
                    icon = Icons.Default.Add,
                    onClick = { /* Próximamente */ }
                )
            }
        }
    }
}

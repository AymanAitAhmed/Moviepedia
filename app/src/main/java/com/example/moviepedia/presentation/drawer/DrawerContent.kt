package com.example.moviepedia.presentation.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent() {
    ModalDrawerSheet(
        drawerShape = RectangleShape,
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerContentColor = MaterialTheme.colorScheme.onSurface
    ) {
        val itemSelected = rememberSaveable() {
            mutableStateOf(0)
        }

        drawerItems.forEachIndexed { index, navigationItem ->
            NavigationDrawerItem(
                label = {
                    Text(text = navigationItem.title)
                },
                selected = itemSelected.value == index,
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (itemSelected.value == index) navigationItem.selectedIcon
                            else navigationItem.unselectedIcon
                        ),
                        contentDescription = navigationItem.title
                    )
                },
                onClick = {
                    itemSelected.value = index
                    println("item: ${navigationItem.title} clicked")
                }
            )
        }
    }
}
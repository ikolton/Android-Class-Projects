package com.example.shopapp.views.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopapp.db.Item
import com.example.shopapp.views.Screen
import com.example.shopapp.views.ITEM_ID_KEY
import org.koin.compose.koinInject

@Composable
fun Items( navController: NavController) {

    val viewModel: ItemsViewModel = koinInject()
    val itemsList by viewModel.items.collectAsState()

    LazyColumn(Modifier.padding(12.dp)) {
        items(itemsList) { item ->
            ItemObject(item, navController)
        }
    }
}

@Composable
fun ItemObject(item: Item, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate(
                    Screen.ItemsDetails.route.replace(
                        "{$ITEM_ID_KEY}",
                        item.id.toString()
                    )
                )
            },
    ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                text = item.name,
                fontSize = 30.sp,
            )

    }
}

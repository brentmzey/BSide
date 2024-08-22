package love.bside

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import love.bside.components.cards.SwipeableCardStack
import love.bside.models.User
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.painterResource

@OptIn(InternalResourceApi::class)
val bsideLogoResource = DrawableResource(
    id = "bside_logo",
    items = setOf(
        ResourceItem(
            qualifiers = emptySet(), // Adjust Qualifier as needed
            path = "assets/bside/bside_logo.png",
            offset = 0L, // Adjust offset as needed
            size = 0L // Adjust size as needed
        )
    )
)

@Composable
fun BSideLogo() {
    Image(
        painter = painterResource(bsideLogoResource),
        contentDescription = "BSide Logo",
        modifier = Modifier.size(150.dp).padding(16.dp)
    )
}

@Composable
fun App() {

    var users by remember { mutableStateOf<List<User>>(emptyList()) }

//    LaunchedEffect(Unit) {
//        loadUsers { fetchedUsers ->
//            users = fetchedUsers
//        }
//    }
    MaterialTheme {
        loadUsers()
        Box(modifier = Modifier.fillMaxSize()) {
            BSideLogo()
            val cards = listOf("Card 1", "Card 2", "Card 3")
            SwipeableCardStack(cards = cards)
        }
    }
}

fun loadUsers() {

}

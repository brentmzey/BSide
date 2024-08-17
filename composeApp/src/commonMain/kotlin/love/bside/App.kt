package love.bside

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier,
    onSwipe: (direction: SwipeDirection) -> Unit,
    content: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        val direction = when {
                            abs(offsetX) > abs(offsetY) && offsetX > 0 -> SwipeDirection.RIGHT
                            abs(offsetX) > abs(offsetY) && offsetX < 0 -> SwipeDirection.LEFT
                            abs(offsetY) > abs(offsetX) && offsetY > 0 -> SwipeDirection.DOWN
                            else -> SwipeDirection.UP
                        }
                        onSwipe(direction)
                        offsetX = 0f
                        offsetY = 0f
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
    ) {
        content()
    }
}

enum class SwipeDirection {
    LEFT, RIGHT, UP, DOWN
}

@Composable
fun SwipeableCardStack(cards: List<String>) {
    val cardStates = remember { cards.map { mutableStateOf(true) } }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        cards.forEachIndexed { index, card ->
            if (cardStates[index].value) {
                SwipeableCard(
                    modifier = Modifier.zIndex(index.toFloat()),
                    onSwipe = { direction ->
                        cardStates[index].value = false
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = card, style = MaterialTheme.typography.h4)
                        }
                    }
                }
            }
        }
    }
}

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
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            BSideLogo()
            val cards = listOf("Card 1", "Card 2", "Card 3")
            SwipeableCardStack(cards = cards)
        }
    }
}
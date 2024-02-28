package com.example.lab


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lab.ui.theme.LABTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LABTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UsersList();
                }
            }
        }
    }
}

@Composable
fun ChangeColorButton() {
    var buttonColor by remember { mutableStateOf(Color.Red) }

    // The UI should recompose when buttonColor changes
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { buttonColor = if (buttonColor == Color.Red) Color.Green else Color.Red },
            // Use the buttonColor variable for the background color
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("Click Me")
        }
    }
}

@Composable
fun CustomLayout() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val imagePainter: Painter = painterResource(id = R.drawable.img_1) // Replace with your image resource ID
        Image(
            painter = imagePainter,
            contentDescription = null, // Provide an appropriate content description
            modifier = Modifier
                .size(100.dp) // Replace with your desired size
                .clip(RoundedCornerShape(10.dp)) // Rounded corners
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f), // Takes up remaining space
            verticalArrangement = Arrangement.Center
        ) {
            val nameState = remember { mutableStateOf(TextFieldValue()) }
            val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }

            // Custom name input field
            BasicTextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(Color.LightGray, RoundedCornerShape(4.dp)),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.padding(8.dp)) {
                        if (nameState.value.text.isEmpty()) {
                            Text("AYAN", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Custom phone number input field
            BasicTextField(
                value = phoneNumberState.value,
                onValueChange = { phoneNumberState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(Color.LightGray, RoundedCornerShape(4.dp)),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.padding(8.dp)) {
                        if (phoneNumberState.value.text.isEmpty()) {
                            Text("03188014252", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
fun UserCard(name: String, age: Int, imageId: Int) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(44.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = name, fontSize = 18.sp)
                Text(text = "Age: $age", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun UsersList() {
    // Dummy data for demonstration
    val users = listOf(
        Pair("Iron Man", 43),
        Pair("Ayan", 38),
        Pair("Thor", 25),
        Pair("Hulk", 48),
    )
    // You would replace the drawable id with the actual ids from your drawables
    val images = listOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
    )

    Column {
        users.zip(images).forEach { (user, imageId) ->
            UserCard(name = user.first, age = user.second, imageId = imageId)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LABTheme {
        UsersList();
    }
}
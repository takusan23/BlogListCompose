package io.github.takusan23.bloglistcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar() {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxHeight().padding(10.dp),
                            ) {
                                Text(text = "ブログ一覧")
                            }
                        }
                    }
                ) {
                    BlogCardList()
                }
            }
        }
    }
}

@Composable
fun CountUp() {
    // 押した回数を保持する
    var count by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.padding(10.dp), // スペース確保
        verticalAlignment = Alignment.CenterVertically, // まんなかに
    ) {
        Text(
            text = "押した回数 $count",
        )
        IconButton(onClick = {
            // カウントアップ
            count++
        }) {
            Icon(imageVector = Icons.Outlined.Home)
        }
    }
}

@Preview
@Composable
fun CountUpPreview() {
    CountUp()
}

@Composable
fun BlogCard(blogTitle: String) {
    // 押した回数を保持する
    var count by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp), // スペース確保
            verticalArrangement = Arrangement.Center, // まんなかに
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Outlined.Book, // androidx.compose.material:material-icons-core がいる？
                    modifier = Modifier.height(50.dp).width(50.dp),
                )
                Text(
                    text = blogTitle,
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) // 区切り線
            IconButton(onClick = {
                // カウントアップ
                count++
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder)
                    Text(text = "$count")
                }
            }
        }
    }
}

@Composable
fun BlogCardList() {
    val blogList = listOf(
        "Jetpack Compose 略して？",
        "MotionLayoutでミニプレイヤー",
        "2020年おすすめボカロ",
        "ボカロバラードいいよね",
    )
    Surface(Modifier.fillMaxSize()) {
        LazyColumn(content = {
            items(blogList) {
                BlogCard(it)
            }
        })
    }
}

@Composable
fun VideoInfo() {
    // 表示するか
    var isShow by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(text = "動画タイトル", modifier = Modifier.weight(1f)) // アイコンまでずっと伸ばす
                IconButton(onClick = {
                    isShow = !isShow
                }) {
                    // アイコンも変更
                    Icon(imageVector = if (isShow) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore)
                }
            }

            // 表示するか
            if (isShow) {
                Text(
                    text = """
                    ニコ動にもサムネ登録機能ついてから最後一瞬だけサムネ用の画像を表示させるやつも見なくなりましたね。
                    """.trimIndent()
                )
            }

        }
    }
}


@Composable
@Preview
fun VideoInfoPreview() {
    VideoInfo()
}

@Composable
fun CardCenterText() {
    Column {
        Card(
            modifier = Modifier.padding(10.dp).fillMaxWidth().height(100.dp),
            backgroundColor = Color.Cyan,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "まんなかに居座る Row")
                Icon(imageVector = Icons.Outlined.Android)
            }
        }
        Card(
            modifier = Modifier.padding(10.dp).fillMaxWidth().height(100.dp),
            backgroundColor = Color.Cyan,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "まんなかに居座る Column")
                Icon(imageVector = Icons.Outlined.Android)
            }
        }
    }
}

@Preview
@Composable
fun CardCenterTextPreview() {
    CardCenterText()
}
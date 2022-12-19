package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeTextDefaults
import com.arniodev.translator.R
import com.arniodev.translator.data.EngineItem
import com.arniodev.translator.ui.ModifierExtends.clickVfx
import com.arniodev.translator.ui.gooLiPuhuiSansFamily
import com.arniodev.translator.ui.isRound

class EngineChoosingActivity : AppCompatActivity() {

    private lateinit var engineList: List<EngineItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        engineList = listOf(
            EngineItem(
                R.drawable.google_translate_icon,
                getString(R.string.google_translate),
                "Google"
            ), EngineItem(
                R.drawable.deepl_icon,
                getString(R.string.deepl_translate),
                "DeepL"
            )
        )
        setContent {
            var currentEngine by remember {
                mutableStateOf("")
            }
            val localDensity = LocalDensity.current
            var textHeight by remember {
                mutableStateOf(0.dp)
            }
            val buttonColor by animateColorAsState(
                targetValue = if (currentEngine.isEmpty()) Color(
                    41,
                    41,
                    41,
                    255
                ) else Color(68, 138, 255, 255)
            )
            val buttonAlpha by animateFloatAsState(targetValue = if (currentEngine.isEmpty()) 0.6f else 1f)
            UniversalBackground(title = "", onBack = ::finish) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    horizontalAlignment = if (isRound()) Alignment.CenterHorizontally else Alignment.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Key,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = "选择翻译引擎",
                        color = Color.White,
                        fontFamily = gooLiPuhuiSansFamily,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "选择您的翻译引擎以继续\n稍后可以在设置里更改",
                        color = Color.White,
                        fontFamily = gooLiPuhuiSansFamily,
                        fontSize = 12.sp, modifier = Modifier.alpha(0.6f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(modifier = Modifier.requiredSizeIn(maxHeight = 6000.dp)) {
                        engineList.forEach {
                            item {
                                EngineItemUI(engineItem = it, it.engine == currentEngine) {
                                    if (currentEngine == it) currentEngine = ""
                                    currentEngine = it
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .alpha(buttonAlpha)
                            .clickVfx {
                                if (currentEngine.isNotEmpty()) {
                                    prefs.edit {
                                        putString("engine", currentEngine)
                                    }
                                    val intent = Intent(
                                        this@EngineChoosingActivity,
                                        InitFinishedActivity::class.java
                                    )
                                    startActivity(intent)
                                    finish()
                                }
                            }
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(25.dp))
                            .background(buttonColor)
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "下一步",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = gooLiPuhuiSansFamily,
                            modifier = Modifier.onGloballyPositioned {
                                textHeight = with(localDensity) { it.size.height.toDp() }
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForwardIos,
                            contentDescription = null,
                            modifier = Modifier.size(textHeight),
                        )
                    }
                    if (isRound()) Spacer(modifier = Modifier.height(40.dp)) else Spacer(
                        modifier = Modifier.height(
                            8.dp
                        )
                    )
                }
            }
        }

        /*val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val arrowView = findViewById<View>(R.id.engine_next_step_arrow) as ImageView
        val nextBtnView = findViewById<View>(R.id.engine_next_step)
        val engineRecyclerView = findViewById<View>(R.id.engine_list) as RecyclerView
        val adapter = EngineAdapter(this, engineList)
        val layoutManager = LinearLayoutManager(this)

        engineRecyclerView.layoutManager = layoutManager
        engineRecyclerView.adapter = adapter
        engineRecyclerView.addItemDecoration(RecyclerDecoration())

        nextBtnView.setOnClickListener {
            if( arrowView.alpha == 1F ) {
                prefs.edit {
                    putString("engine", adapter.getEngine())
                }
                val intent = Intent(this,InitFinishedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }*/
    }

    @Composable
    fun EngineItemUI(engineItem: EngineItem, isSelected: Boolean, onClick: (String) -> Unit) {
        val localDensity = LocalDensity.current
        var textHeight by remember {
            mutableStateOf(0.dp)
        }
        Row(
            modifier = Modifier
                .clickVfx { onClick(engineItem.engine) }
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .background(Color(41, 41, 41, 255))
                .padding(vertical = 18.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = isSelected) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .clip(
                            RoundedCornerShape(360.dp)
                        )
                        .background(Color(68, 138, 255, 255))
                        .height(textHeight.minus(6.dp))

                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Image(
                painter = painterResource(id = engineItem.icon),
                contentDescription = null,
                modifier = Modifier.size(textHeight),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = engineItem.name,
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = gooLiPuhuiSansFamily,
                modifier = Modifier.onGloballyPositioned {
                    textHeight = with(localDensity) { it.size.height.toDp() }
                })
        }
    }

    @Composable
    fun UniversalBackground(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
        val timeSource = TimeTextDefaults.timeSource("HH:mm")
        val timeText = timeSource.currentTime
        var textHeight by remember { mutableStateOf(0.dp) }
        val localDensity = LocalDensity.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier
                    .fillMaxSize()
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Spacer(Modifier.height(6.dp))
                    if (isRound()) {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = gooLiPuhuiSansFamily,
                            color = Color.White,
                            modifier = Modifier
                                .onGloballyPositioned {
                                    textHeight = with(localDensity) {
                                        it.size.height.toDp()
                                    }
                                }
                                .align(Alignment.CenterHorizontally)
                                .clickable(
                                    onClick = {
                                        onBack()
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ), maxLines = 1, overflow = TextOverflow.Ellipsis
                        )
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(start = 7.5f.dp, end = 7.5f.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(
                                    onClick = {
                                        onBack()
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBackIos,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(16.dp)
                                        .width(16.dp)
                                        .align(Alignment.CenterVertically)
                                        .offset(y = 0.9f.dp),
                                    tint = Color.White
                                )
                                //Spacer(Modifier.width(2.dp))
                                Text(
                                    text = title,
                                    fontSize = 16.sp,
                                    fontFamily = gooLiPuhuiSansFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .onGloballyPositioned {
                                            textHeight = with(localDensity) {
                                                it.size.height.toDp()
                                            }
                                        }
                                        .align(Alignment.CenterVertically),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = timeText,
                                    fontSize = 16.sp,
                                    fontFamily = gooLiPuhuiSansFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                }   //标题栏
                content()
            }   //内容
        }
    }
}
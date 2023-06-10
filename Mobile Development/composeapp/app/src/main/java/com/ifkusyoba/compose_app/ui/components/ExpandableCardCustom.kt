package com.ifkusyoba.compose_app.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCardCustom(
    @StringRes title: Int,
    @StringRes answer: Int,
) {
    var expandState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(
        targetValue = if (expandState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            expandState = !expandState
        },
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = stringResource(id = title),
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                IconButton(
                    modifier = Modifier
                        .alpha(1f)
                        .weight(1f)
                        .rotate(rotateState),
                    onClick = { expandState = !expandState }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "ExpandableCardCustom",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            if (expandState) {
                Text(
                    text = stringResource(id = answer),
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardCustomPreview() {
    ComposeAppTheme {
        ExpandableCardCustom(
            title = R.string.faq_question1,
            answer = R.string.faq_answer1,
        )
    }
}
package com.pazlin.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(
    text: String,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    style: Style = Style.IconAtLeft,
    rounded: Int = 14,
    backColor: Color = Color(0xff525252),
    foreColor: Color = Color(0xffd4d2d2),
    buttonWidth: Dp = 140.dp,
    buttonHeight: Dp = 70.dp,
    textSize: TextUnit = ((with(LocalDensity.current) { buttonHeight.toSp() })) / 2.3,
    iconSize: Dp = (with(LocalDensity.current) { textSize.toDp() }),
    spacer: Dp =
        if (textSize == 0.sp || iconSize == 0.dp || icon == null || text == "") 0.dp
        else ((with(LocalDensity.current) { textSize.toDp() }) + iconSize) / 4,

    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        shape = RoundedCornerShape(rounded),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backColor,
            contentColor = foreColor,
            disabledContainerColor = backColor.copy(alpha = 0.4f),
            disabledContentColor = foreColor.copy(alpha = 0.6f)
        ),
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight)
    )


    {
        when (style) {
            Style.IconAtLeft -> SlotRow(
                spacer,
                { ButtonIcon(icon = icon, iconSize) },
                { ButtonText(text = text, fontSize = textSize) })

            Style.IconAtRight -> SlotRow(
                spacer,
                { ButtonText(text = text, fontSize = textSize) },
                { ButtonIcon(icon = icon, iconSize) })

            Style.IconAtTop -> SlotColumn(
                spacer,
                { ButtonIcon(icon = icon, iconSize) },
                { ButtonText(text = text, fontSize = textSize) })

            Style.IconAtBottom -> SlotColumn(
                spacer,
                { ButtonText(text = text, fontSize = textSize) },
                { ButtonIcon(icon = icon, iconSize) })

        }
    }
}


@Composable
fun SlotRow(
    spacer: Dp,
    firstContent: @Composable() (() -> Unit)? = null,
    secondContent: @Composable() (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center

    ) {
        firstContent?.let { it() }
        Spacer(modifier = Modifier.width(spacer))
        secondContent?.invoke()
    }
}

@Composable
fun SlotColumn(
    spacer: Dp,
    firstContent: @Composable() (() -> Unit)? = null,
    secondContent: @Composable() (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        firstContent?.let { it() }
        Spacer(modifier = Modifier.height(spacer))
        secondContent?.invoke()
    }
}

@Composable
fun ButtonText(text: String, fontSize: TextUnit) {
    Text(
        text = text,
        fontSize = fontSize,
    )
}

@Composable
fun ButtonIcon(icon: ImageVector?, iconHeight: Dp) {
    icon?.let {
        Row(
            Modifier.width(iconHeight)
        ) {
            Column(Modifier.height(iconHeight)) {
                Icon(
                    imageVector = it,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentDescription = null
                )
            }
        }
    }
}


enum class Style() {
    IconAtLeft,
    IconAtRight,
    IconAtTop,
    IconAtBottom
}


@Preview(name = "Preview 0")
@Composable
fun PreviewCustomButton0() {
    CustomButton(
        style = Style.IconAtRight,
        rounded = 100,
        icon = Icons.Default.Build,
        text = "",
        backColor = Color(0xff75b2e0),
        foreColor = Color(0xff154c75),
        textSize = 0.sp,
        iconSize = 60.dp,
        buttonWidth = 100.dp,
        buttonHeight = 100.dp
    ) {}

}

@Preview(name = "Preview 1")
@Composable
fun PreviewCustomButton1() {
    CustomButton(
        style = Style.IconAtRight,
        rounded = 100,
        icon = Icons.Default.Build,
        text = "BUTTON",
        backColor = Color(0xff75b2e0),
        foreColor = Color(0xff154c75),
        textSize = 40.sp,
        buttonWidth = 300.dp,
        buttonHeight = 100.dp
    ) {}
}

@Preview(name = "Preview 2")
@Composable
fun PreviewCustomButton2() {
    CustomButton(
        enabled = false,
        style = Style.IconAtLeft,
        rounded = 50,
        icon = Icons.Default.Build,
        text = "BUTTON",
        backColor = Color(0xff75b2e0),
        foreColor = Color(0xff154c75),
        textSize = 40.sp,
        buttonWidth = 300.dp,
        buttonHeight = 100.dp
    ) {}
}

@Preview(name = "Preview 3")
@Composable
fun PreviewCustomButton3() {
    CustomButton(
        style = Style.IconAtRight,
        rounded = 10,
        icon = Icons.Default.Build,
        text = "BUTTON",
        backColor = Color(0xfffcba03),
        foreColor = Color(0xff876a18),
        textSize = 40.sp,
        iconSize = 60.dp,
        buttonWidth = 300.dp,
        buttonHeight = 100.dp
    ) {}
}

@Preview(name = "Preview 4")
@Composable
fun PreviewCustomButton4() {
    CustomButton(
        style = Style.IconAtRight,
        rounded = 100,
        text = "BUU",
        backColor = Color(0xff178f57),
        foreColor = Color(0xffaee6cc),
        textSize = 60.sp,
        iconSize = 0.dp,
        buttonWidth = 300.dp,
        buttonHeight = 300.dp
    ) {}
}

@Preview(name = "Preview 5")
@Composable
fun PreviewCustomButton5() {
    CustomButton(
        style = Style.IconAtRight,
        rounded = 20,
        icon = Icons.Default.Build,
        text = "Text",
        backColor = Color(0xffb3205b),
        foreColor = Color(0xfff2bfd4),
        textSize = 70.sp,
        iconSize = 70.dp,
        spacer = 80.dp,
        buttonWidth = 340.dp,
        buttonHeight = 100.dp
    ) {}
}

@Preview(name = "Preview 6")
@Composable
fun PreviewCustomButton6() {
    CustomButton(
        style = Style.IconAtTop,
        rounded = 100,
        icon = Icons.Default.LocationOn,
        text = "Location",
        backColor = Color(0xfff2ba49),
        foreColor = Color(0xff573f10),
        textSize = 60.sp,
        iconSize = 60.dp,
        buttonWidth = 300.dp,
        buttonHeight = 300.dp
    ) {}
}

@Preview(name = "Preview 7")
@Composable
fun PreviewCustomButton7() {
    CustomButton(
        enabled = false,
        style = Style.IconAtTop,
        rounded = 14,
        icon = Icons.Default.LocationOn,
        text = "Location",
        backColor = Color(0xfff2ba49),
        foreColor = Color(0xff573f10),
        spacer = 0.dp,
        textSize = 55.sp,
        iconSize = 80.dp,
        buttonWidth = 380.dp,
        buttonHeight = 200.dp
    ) {}
}

@Preview(name = "Preview 8")
@Composable
fun PreviewCustomButton8() {
    CustomButton(
        "Button"
    ) {}
}

@Preview(name = "Preview 9")
@Composable
fun PreviewCustomButton9() {
    CustomButton(
        "Button",
        buttonHeight = 50.dp,
        buttonWidth = 300.dp
    ) {}
}
package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.surajvanshsv.cartify_ecomemerceapp.ui.theme.PrimaryColor

@Composable
fun SectionTitle(   // section title and see all composable
    title : String , // title of section
    actionText : String , // text of see all
    onActionClick : ()-> Unit // action to be performed when see all is clicked
){


    Row( // store title and see all
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // space between title and see all
        verticalAlignment = Alignment.CenterVertically // align title and see all in center

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            text = actionText,
            style = MaterialTheme.typography.bodyMedium.copy(color = PrimaryColor),
            modifier = Modifier.clickable{ // to make text clickable like button and perform action
                onActionClick()
            }

        )
    }
}
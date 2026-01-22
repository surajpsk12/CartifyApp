package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.surajvanshsv.cartify_ecomemerceapp.ui.theme.CategoryChipColor

@Composable
fun CategoryChip( // category chip composable
    icon : String ,
    text : String ,
    isSelected : Boolean ,
    onClick : () -> Unit
){

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) CategoryChipColor
            else Color.LightGray.copy(alpha = 0.1f)
        ),
        border = BorderStroke(
            width = if(isSelected) 0.dp else 1.dp,
            color =  Color.LightGray.copy(alpha = 0.1f)
        ),
        modifier = Modifier.clickable{
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)

        ) {
            Icon(
                painter = rememberAsyncImagePainter(icon),
                contentDescription = text,
                tint = if(isSelected) Color.White else Color.Black,
                modifier = Modifier.size(20.dp)
            )
            // text
            Text(
                text = text,
                color = if(isSelected) Color.White else Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)

            )


        }

    }

}
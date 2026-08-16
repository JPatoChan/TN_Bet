package com.example.tn_bet.widget

import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.tn_bet.data.RetrofitClient
import com.example.tn_bet.R

class TitansWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val teamData = try {
            RetrofitClient.titansApi.getTeamInfo().team
        } catch (_: Exception) {
            null
        }

        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(R.color.tn_red))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = teamData?.displayName ?: "TN Titans",
                    style = TextStyle(
                        color = ColorProvider(R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = GlanceModifier.height(4.dp))
                Text(
                    text = "Record: ${teamData?.record?.items?.firstOrNull()?.summary ?: "---"}",
                    style = TextStyle(
                        color = ColorProvider(R.color.white),
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

class TitansWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TitansWidget()
}

package com.example.tn_bet.data

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val team: Team
)

@Serializable
data class Team(
    val id: String,
    val location: String,
    val name: String,
    val nickname: String,
    val displayName: String,
    val color: String? = null,
    val alternateColor: String? = null,
    val logos: List<Logo> = emptyList(),
    val record: RecordSummary? = null
)

@Serializable
data class Logo(
    val href: String,
    val width: Int? = null,
    val height: Int? = null
)

@Serializable
data class RecordSummary(
    val items: List<RecordItem> = emptyList()
)

@Serializable
data class RecordItem(
    val summary: String? = null
)

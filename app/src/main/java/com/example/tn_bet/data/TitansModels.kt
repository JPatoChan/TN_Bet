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

@Serializable
data class ScheduleResponse(
    val events: List<TitansEvent> = emptyList()
)

@Serializable
data class TitansEvent(
    val id: String,
    val date: String,
    val name: String,
    val shortName: String? = null,
    val competitions: List<Competition> = emptyList()
)

@Serializable
data class Competition(
    val id: String,
    val competitors: List<Competitor> = emptyList()
)

@Serializable
data class Competitor(
    val id: String,
    val team: TeamSummary
)

@Serializable
data class TeamSummary(
    val id: String,
    val location: String? = null,
    val name: String? = null,
    val nickname: String? = null,
    val displayName: String,
    val abbreviation: String? = null,
    val logos: List<Logo> = emptyList()
)

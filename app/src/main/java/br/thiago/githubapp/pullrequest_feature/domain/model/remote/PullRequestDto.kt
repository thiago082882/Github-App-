package br.thiago.githubapp.pullrequest_feature.domain.model.remote

import com.google.gson.annotations.SerializedName

data class PullRequestDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: UserDto,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("body") val body: String
)

data class UserDto(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

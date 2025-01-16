package br.thiago.githubapp.repositories_feature.domain.model.remote

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
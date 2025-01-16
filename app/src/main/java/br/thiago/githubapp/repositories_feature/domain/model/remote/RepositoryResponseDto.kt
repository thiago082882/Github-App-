package br.thiago.githubapp.repositories_feature.domain.model.remote

import com.google.gson.annotations.SerializedName

data class RepositoryResponseDto(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<RepositoryDto>
)


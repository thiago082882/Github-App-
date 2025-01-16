package br.thiago.githubapp.repositories_feature.domain.model.remote


import com.google.gson.annotations.SerializedName

data class RepositoryDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("owner") val owner: OwnerDto,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("forks_count") val forksCount: Int
)




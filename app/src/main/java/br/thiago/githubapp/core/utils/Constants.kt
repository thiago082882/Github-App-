package br.thiago.githubapp.core.utils

object Constants {

    const val TIMEOUT_SECONDS = 15L
    const val BASE_URL = "https://api.github.com/"
    const val PULL_REQUEST_ENDPOINT = "repos/{owner}/{repo}/pulls"
    const val REPOSITORY_ENDPOINT = "search/repositories?q=language:kotlin&sort=stars"

}
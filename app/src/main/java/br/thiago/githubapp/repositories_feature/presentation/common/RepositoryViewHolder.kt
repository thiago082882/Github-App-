package br.thiago.githubapp.repositories_feature.presentation.common

import androidx.recyclerview.widget.RecyclerView
import br.thiago.githubapp.databinding.RepositoryListItemBinding
import br.thiago.githubapp.repositories_feature.domain.model.Repository
import coil.load

class RepositoryViewHolder(
    private val binding: RepositoryListItemBinding,
    private val onClick: (Repository) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Repository) {
        binding.repoName.text = item.name
        binding.fullName.text = item.fullName
        binding.repoDescription.text = item.description
        binding.repoStars.text = "Stars: ${item.stargazers_count}"
        binding.repoForks.text = "Forks: ${item.forks_count}"
        binding.ownerAvatar.load(item.owner.avatar_url)


        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}

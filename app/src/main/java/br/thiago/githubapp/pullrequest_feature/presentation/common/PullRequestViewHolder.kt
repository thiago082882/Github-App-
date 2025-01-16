package br.thiago.githubapp.pullrequest_feature.presentation.common

import androidx.recyclerview.widget.RecyclerView
import br.thiago.githubapp.databinding.PullRequestListItemBinding
import br.thiago.githubapp.pullrequest_feature.domain.model.PullRequest
import coil.load
import java.text.SimpleDateFormat
import java.util.Locale

class PullRequestViewHolder(
    private val binding: PullRequestListItemBinding,
    private val onClick: (PullRequest) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    private val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private var isExpanded = false

    fun bind(item: PullRequest) {
        binding.prTitle.text = item.title
        binding.prBody.text = item.body
        binding.prUserName.text = item.user.login
        binding.prUserAvatar.load(item.user.avatarUrl)

        val date = inputDateFormat.parse(item.createdAt)
        val formattedDate = date?.let { outputDateFormat.format(it) } ?: item.createdAt
        binding.prCreatedAt.text = "Criado em: $formattedDate"

        binding.prBody.apply {
            ellipsize = android.text.TextUtils.TruncateAt.END
            maxLines = 1
            setSingleLine(true)
            setOnClickListener {
                isExpanded = !isExpanded
                if (isExpanded) {
                    ellipsize = null
                    setSingleLine(false)
                    maxLines = Int.MAX_VALUE
                } else {
                    ellipsize = android.text.TextUtils.TruncateAt.END
                    setSingleLine(true)
                    maxLines = 1
                }
                requestLayout()
            }
        }

        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}

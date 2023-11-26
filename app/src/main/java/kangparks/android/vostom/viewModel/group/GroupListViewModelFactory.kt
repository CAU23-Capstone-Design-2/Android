package kangparks.android.vostom.viewModel.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupListViewModelFactory(private val token: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroupListViewModel(token) as T
    }
}

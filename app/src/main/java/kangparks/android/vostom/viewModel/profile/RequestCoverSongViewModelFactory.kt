package kangparks.android.vostom.viewModel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RequestCoverSongViewModelFactory(
    private val token : String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RequestCoverSongViewModel(token = token) as T
    }
}
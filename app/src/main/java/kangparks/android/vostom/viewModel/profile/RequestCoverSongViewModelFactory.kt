package kangparks.android.vostom.viewModel.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kangparks.android.vostom.utils.store.getAccessToken

class RequestCoverSongViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {
    val token = getAccessToken(applicationContext)!!
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RequestCoverSongViewModel(token = token) as T
    }
}
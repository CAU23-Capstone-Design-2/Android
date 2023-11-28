package kangparks.android.vostom.viewModel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kangparks.android.vostom.models.content.CoverSong

class ProfileViewModelFactory (
    private val token: String,
    private val userImgUrl: String,
    private val userName: String,
    private val likedCoverItemList: List<CoverSong>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            token = token,
            userImgUrlFromStore = userImgUrl,
            userNameFromStore = userName,
            likedCoverItemListFromStore = likedCoverItemList
        ) as T
    }
}
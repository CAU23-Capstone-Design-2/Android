package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.item.YoutubePlayItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateContentViewModel : ViewModel() {
    private val _songItem = MutableLiveData<YoutubePlayItem?>(null)
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    val songItem : MutableLiveData<YoutubePlayItem?> = _songItem
    fun setSongItem(item : YoutubePlayItem?){
        _songItem.value = item
    }

    fun createCoverSong(token : String){
        coroutineScope.launch {
//            songItem.value?.let { createCover(token, it.contentUri) }
        }
    }
}
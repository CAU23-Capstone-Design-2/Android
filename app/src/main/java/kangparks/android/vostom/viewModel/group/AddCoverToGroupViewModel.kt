package kangparks.android.vostom.viewModel.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.CoverSong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AddCoverToGroupViewModel : ViewModel(){
    private val _songItem = MutableLiveData<CoverSong?>(null)
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    val songItem : LiveData<CoverSong?> = _songItem

    fun setSongItem(item : CoverSong?){
        _songItem.value = item
    }

    fun addCoverToGroup(token : String) {
        coroutineScope.launch {
//            songItem.value?.let { createCover(token, it.contentUri) }
        }
    }
}
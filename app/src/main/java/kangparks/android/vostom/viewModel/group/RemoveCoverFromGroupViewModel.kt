package kangparks.android.vostom.viewModel.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Music
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RemoveCoverFromGroupViewModel : ViewModel() {
    private val _songItem = MutableLiveData<Music?>(null)
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    val songItem : LiveData<Music?> = _songItem

    fun setSongItem(item : Music?){
        _songItem.value = item
    }

    fun removeCoverFromGroup(token : String) {
        coroutineScope.launch {
//            songItem.value?.let { createCover(token, it.contentUri) }
        }
    }
}
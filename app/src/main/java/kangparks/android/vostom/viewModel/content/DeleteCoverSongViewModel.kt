package kangparks.android.vostom.viewModel.content

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.networks.content.deleteCoverSong
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteCoverSongViewModel : ViewModel(){
    private val _songItem = MutableLiveData<Music?>(null)
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    val songItem : LiveData<Music?> = _songItem

    fun setSongItem(item : Music?){
        _songItem.value = item
    }

    fun deleteCurrentCoverSong(context : Context) {
        val token = getAccessToken(context)

        coroutineScope.launch {
            songItem.value?.let {
                deleteCoverSong(
                    token = token!!,
                    musicId = _songItem.value!!.id
                )
            }
        }
    }



}
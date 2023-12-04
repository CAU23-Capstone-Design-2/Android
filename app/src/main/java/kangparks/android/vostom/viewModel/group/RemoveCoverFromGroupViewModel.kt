package kangparks.android.vostom.viewModel.group

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Group
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.networks.group.addMusicToGroup
import kangparks.android.vostom.utils.networks.group.deleteMusicFromGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RemoveCoverFromGroupViewModel : ViewModel() {
    private val _songItem = MutableLiveData<Music?>(null)
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    val songItem : LiveData<Music?> = _songItem

    fun setSongItem(item : Music?){
        _songItem.value = item
    }

    fun removeCoverFromGroup(
        context : Context,
        currentGroup : Group
    ) {
        coroutineScope.launch {
            songItem.value?.let {
                deleteMusicFromGroup(
                    context = context,
                    musicId =  _songItem.value!!.id,
                    groupId = currentGroup.teamId
                )
            }
        }
    }
}
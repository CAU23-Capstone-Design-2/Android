package kangparks.android.vostom.viewModel.learning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.item.YoutubePlayItem

class SingingViewModel : ViewModel(){
    private val _songItem = MutableLiveData<YoutubePlayItem?>(null)

    val songItem : MutableLiveData<YoutubePlayItem?> = _songItem


    fun setSongItem(item : YoutubePlayItem?){
        _songItem.value = item
    }
}
package kangparks.android.vostom.viewModel.content

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kangparks.android.vostom.models.item.YoutubePlayItem
import kangparks.android.vostom.utils.networks.content.createCover
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateContentViewModel : ViewModel() {
    private val _songItem = MutableLiveData<YoutubePlayItem?>(null)
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    val songItem : LiveData<YoutubePlayItem?> = _songItem
    fun setSongItem(item : YoutubePlayItem?){
        _songItem.value = item
    }

    fun createCoverSong(
        token : String,
        context : Context,
//        navController : NavHostController
    ){
        coroutineScope.launch {
            songItem.value?.let {
                val result = createCover(
                    token = token,
                    title = it.title,
                    imgUrl = it.thumbnailUri,
                    url = it.contentUri
                )

//                if(result){
//
//                }else{
//                    CoroutineScope(Dispatchers.Main).launch {
//                        Toast.makeText(context, "알 수 없는 오류 발생했습니다.\n다시 시도해 주세요.", Toast.LENGTH_LONG).show()
//                    }
//                }
            }
        }
    }
}
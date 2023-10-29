package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.models.content.Song
import kangparks.android.vostom.utils.dummy.othersContentItemList

class OthersContentDetailViewModel constructor(private val _currentSinger : Singer): ViewModel() {

    private val _id : Int = _currentSinger.id
    private val _listOfCoverSongs = MutableLiveData<List<Song>>(
        othersContentItemList
    )

    val singerName : String = _currentSinger.name
    val singerDescription : String = _currentSinger.description
    val singerImageUri : String = _currentSinger.imageUri
    val listOfCoverSongs : MutableLiveData<List<Song>> = _listOfCoverSongs

    init {
        // TODO : 서버에서 곡 리스트 받아 오기
    }

    // 음악 재생도 여기서 관리 할까 고민중...

}

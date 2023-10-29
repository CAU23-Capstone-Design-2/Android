package kangparks.android.vostom.viewModel.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.utils.dummy.othersItemList

class OthersContentListViewModel : ViewModel() {

    private val _singerList = MutableLiveData<List<Singer>>(othersItemList)
    val singerList: LiveData<List<Singer>> = _singerList

    init {
        // TODO : 서버에서 가수 리스트 받아오기
    }
}
package kangparks.android.vostom.viewModel.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Singer

sealed class OthersContentViewType(val viewType : String){
    object OthersContentList : OthersContentViewType("OthersContentList")
    object OthersContentDetail : OthersContentViewType("OthersContentDetail")
}

class OthersContentBottomSheetViewModel : ViewModel() {
    private val _currentView = MutableLiveData<OthersContentViewType>()
    private val _currentSinger = MutableLiveData<Singer?>()

    val currentView : LiveData<OthersContentViewType> = _currentView
    val currentSigner : LiveData<Singer?> = _currentSinger

    init {
        _currentView.value = OthersContentViewType.OthersContentList
        _currentSinger.value = null
    }

    fun changeView(viewType: OthersContentViewType){
        _currentView.value = viewType
    }

    fun setSinger(singer : Singer){
        _currentSinger.value = singer
    }


}
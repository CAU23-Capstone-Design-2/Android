package kangparks.android.vostom.viewModel.bottomsheet

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Singer

sealed class OthersContentViewType(val viewType : String){
    object OthersContentList : OthersContentViewType("OthersContentList"),
        State<OthersContentViewType> {
        override val value: OthersContentViewType
            get() = this
    }

    object OthersContentDetail : OthersContentViewType("OthersContentDetail"),
        State<OthersContentViewType> {
        override val value: OthersContentViewType
            get() = this
    }
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
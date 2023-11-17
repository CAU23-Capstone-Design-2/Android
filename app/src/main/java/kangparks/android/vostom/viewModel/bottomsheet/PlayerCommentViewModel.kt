package kangparks.android.vostom.viewModel.bottomsheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.utils.networks.content.getCommentData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PlayerCommentViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)
    private val _commentList : MutableLiveData<List<Comment>> = MutableLiveData<List<Comment>>(listOf())

    val commentList : MutableLiveData<List<Comment>> = _commentList

    fun getCommentList(
        token : String,
        coverId : String
    ) {
        coroutineScope.launch {
            // TODO 서버에서 데이터 받아오기
//            getCommentData(token, coverId)
        }
        //_commentList.value =
    }
}
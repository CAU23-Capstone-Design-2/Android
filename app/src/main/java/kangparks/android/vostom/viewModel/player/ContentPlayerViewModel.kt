package kangparks.android.vostom.viewModel.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.models.content.CoverSong
import kangparks.android.vostom.utils.dummy.dummyCommentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ContentPlayerViewModel : ViewModel(){
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private var _exoPlayer : ExoPlayer? = null

    private val _isPlaying : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _isPaused : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _currentSong : MutableLiveData<CoverSong?> = MutableLiveData<CoverSong?>(null)
    private val _isShowPlayer : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val _currentSongCommentList : MutableLiveData<List<Comment>> = MutableLiveData<List<Comment>>(listOf())

    val isPlaying : LiveData<Boolean> = _isPlaying
    val isPaused : LiveData<Boolean> = _isPaused
    val currentSong : LiveData<CoverSong?> = _currentSong
    val isShowPlayer : LiveData<Boolean> = _isShowPlayer

    val currentSongCommentList : LiveData<List<Comment>> = _currentSongCommentList

    fun setPlayer(player: ExoPlayer){
        _exoPlayer = player
    }

    fun getPlayer() : ExoPlayer? {
        return _exoPlayer
    }

    fun playMusic(song: CoverSong){
        _isPlaying.value = true
        _isPaused.value = false
        _currentSong.value = song
    }

    fun showPlayer(){
        _isShowPlayer.value = true
    }

    fun hidePlayer(){
        _isShowPlayer.value = false
    }

    fun resumeMusic(){
        _exoPlayer?.play()
        _isPaused.value = false
    }

    fun pauseMusic(){
        _exoPlayer?.pause()
        _isPaused.value = true
    }

    fun nextMusic(){
//        _exoPlayer?.next()
    }

    fun prevMusic(){
//        _exoPlayer?.prev()
    }

    fun stopMusic(){
        _exoPlayer?.stop()
        _isPlaying.value = false
        _isPaused.value = false
        _currentSong.value = null
    }

    fun updateCommentList(){
        coroutineScope.launch {
            _currentSongCommentList.postValue(dummyCommentList)
        }
    }
}
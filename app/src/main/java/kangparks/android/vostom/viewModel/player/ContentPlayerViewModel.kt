package kangparks.android.vostom.viewModel.player

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyCommentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ContentPlayerViewModel : ViewModel(){
    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private var _exoPlayer : ExoPlayer? = null

    private val _isPlaying : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _isPaused : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _currentSong : MutableLiveData<Music?> = MutableLiveData<Music?>(null)
    private val _isShowPlayer : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val _currentSongCommentList : MutableLiveData<List<Comment>> = MutableLiveData<List<Comment>>(listOf())

    val isPlaying : LiveData<Boolean> = _isPlaying
    val isPaused : LiveData<Boolean> = _isPaused
    val currentSong : LiveData<Music?> = _currentSong
    val isShowPlayer : LiveData<Boolean> = _isShowPlayer

    val currentSongCommentList : LiveData<List<Comment>> = _currentSongCommentList

    fun setPlayer(player: ExoPlayer){
        _exoPlayer = player
    }

    fun getPlayer(
        context : Context
    ) : ExoPlayer {
        return if(_exoPlayer == null) {
            ExoPlayer.Builder(context).build()
        } else{
            _exoPlayer as ExoPlayer
        }
    }

    fun setMediaSource(
        context : Context,
        mediaSource : ProgressiveMediaSource
    ){
        if(_exoPlayer == null){
            val newPlayer = ExoPlayer.Builder(context).build().apply {
                setMediaSource(mediaSource)
                playWhenReady = true
                prepare()
                volume = 1f
            }

            _exoPlayer = newPlayer
        }else{
            _exoPlayer!!.setMediaSource(mediaSource)
        }
    }

    fun playMusic(music: Music){
        _isPlaying.value = true
        _isPaused.value = false
        _currentSong.value = music
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
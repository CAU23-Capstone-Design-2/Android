package kangparks.android.vostom.viewModel.player

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.helper.media.getMediaSource
import kotlinx.coroutines.CoroutineScope

class StarMusicPlayerViewModel : ViewModel(){

    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private var _exoPlayer: ExoPlayer? = null

    private val _isPlaying: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _isPaused: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val _currentSong: MutableLiveData<Music?> = MutableLiveData<Music?>(null)
    private val _currentPlayIndex : MutableLiveData<Int> = MutableLiveData<Int>(-1)

    val isPlaying: LiveData<Boolean> = _isPlaying
    val isPaused: LiveData<Boolean> = _isPaused

    val currentPlayIndex: LiveData<Int> = _currentPlayIndex

    val currentSong: LiveData<Music?> = _currentSong

    fun getPlayer(): ExoPlayer? = _exoPlayer

    fun setMediaSource(
        contents : List<Music>,
        index : Int,
        context : Context
    ) {
        var mediaSources = mutableListOf<ProgressiveMediaSource>()

        for(content in contents){
            mediaSources.add(
                getMediaSource(
                    context = context,
                    musicId = content.id
                )
            )
        }

        if(_exoPlayer == null) {
            _exoPlayer = ExoPlayer.Builder(context).build().apply {
                setMediaSources(mediaSources.toList())
                playWhenReady = true
                volume = 1f
                prepare()
                seekToDefaultPosition(index)
            }

            _exoPlayer!!.addListener(object : Player.Listener{
                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)

                    if(reason == 1){
                        _currentPlayIndex.postValue(_currentPlayIndex.value?.plus(1) ?: -1)
                        _currentSong.postValue(contents[_currentPlayIndex.value!!])
                    }
                }
            })
        }
        else{
            _exoPlayer?.setMediaSources(mediaSources.toList())
            _exoPlayer?.prepare()

            _exoPlayer?.seekToDefaultPosition(index)
        }

        _isPlaying.value = true
        _isPaused.value = false
        _currentPlayIndex.postValue(index)
        _currentSong.postValue(contents[index])
    }

    fun play(music : Music){
        _isPlaying.value = true
        _isPaused.value = false
//        _currentSong.postValue(music)
    }

    fun stop(){
        _exoPlayer?.stop()
        _exoPlayer = null
        _currentSong.value = null

        _isPlaying.value = false
        _isPaused.value = false
    }

    fun pause(){
        _exoPlayer?.pause()
        _isPaused.value = true
    }

    fun resume(){
        _exoPlayer?.playWhenReady = true
        _isPaused.value = false
    }

}
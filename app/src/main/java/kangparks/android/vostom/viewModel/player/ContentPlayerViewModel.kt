package kangparks.android.vostom.viewModel.player

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.helper.media.getMediaSource
import kangparks.android.vostom.utils.networks.comment.createComment
import kangparks.android.vostom.utils.networks.comment.deleteComment
import kangparks.android.vostom.utils.networks.comment.getCommentList
import kangparks.android.vostom.utils.networks.comment.likeComment
import kangparks.android.vostom.utils.networks.comment.unlikeComment
import kangparks.android.vostom.utils.networks.comment.updateComment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ContentPlayerViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(viewModelScope.coroutineContext)

    private var _exoPlayer: ExoPlayer? = null

//    private var _mediaSession = null
//    private var mediaSessionCompat : MediaSessionCompat? = null
//    private var mediaSessionConnector : MediaSession? = null

    private val _isPlaying: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private val _isPaused: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val _currentPlayList : MutableLiveData<List<Music>> = MutableLiveData<List<Music>>(listOf())
    private var _currentPlayIndex : Int = 0

    private val _currentSong: MutableLiveData<Music?> = MutableLiveData<Music?>(null)
    private val _currentSongCurrentProgress : MutableLiveData<Long> = MutableLiveData<Long>(0L)
    private val _currentSongDuration : MutableLiveData<Long> = MutableLiveData<Long>(0L)

    private val _isShowPlayer: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val _currentSongCommentList: MutableLiveData<List<Comment>> =
        MutableLiveData<List<Comment>>(listOf())

    val isPlaying: LiveData<Boolean> = _isPlaying
    val isPaused: LiveData<Boolean> = _isPaused

    val currentSong: LiveData<Music?> = _currentSong
    val currentSongCurrentProgress: LiveData<Long> get() = _currentSongCurrentProgress
    val currentSongDuration: LiveData<Long> = _currentSongDuration
//    val currentPlayList: LiveData<List<Music>> = _currentPlayList
//    val currentPlayIndex: LiveData<Int> = _currentPlayIndex

    private var updatePositionJob: Job? = null

    val isShowPlayer: LiveData<Boolean> = _isShowPlayer

    val currentSongCommentList: LiveData<List<Comment>> = _currentSongCommentList

    init {
        Log.d("ContentPlayerViewModel", "ContentPlayerViewModel init")
        if(_exoPlayer == null){
            Log.d("ContentPlayerViewModel", "ContentPlayerViewModel init : _exoPlayer is null")
        }else{
            Log.d("ContentPlayerViewModel", "ContentPlayerViewModel init : _exoPlayer is not null")
        }
    }

    private fun startUpdatePositionCoroutine() {
        updatePositionJob = viewModelScope.launch {
            while (true) {
                delay(100)
                val currentPosition = _exoPlayer?.currentPosition ?: 0
                _currentSongCurrentProgress.postValue(currentPosition)
            }
        }
    }

    fun setPlayer(player: ExoPlayer) {
        Log.d("setPlayer", "setPlayer : ${player}")
        _exoPlayer = player
    }

    fun getPlayer(): ExoPlayer? {
//        Log.d("getPlayer", "getPlayer : ${_exoPlayer}")
//        return if (_exoPlayer == null) {
//            Log.d("getPlayer", "getPlayer : _exoPlayer is null")
//            _exoPlayer = ExoPlayer.Builder(context).build()
//            _exoPlayer as ExoPlayer
//        } else {
//            Log.d("getPlayer", "getPlayer : _exoPlayer is not null")
//            _exoPlayer as ExoPlayer
//        }
        return _exoPlayer
    }

    fun setCurrentSongCurrentProgress(progress : Long){
        _currentSongCurrentProgress.postValue(progress)
    }

//    fun runCurrentSongCurrentProgress(){
//        coroutineScope.launch {
//            while (_currentSongCurrentProgress.value!! < _currentSongDuration.value!!) {
//                delay(1000L)
//                _currentSongCurrentProgress.value = _currentSongCurrentProgress.value!! + 1000L
//                Log.d("runCurrentSongCurrentProgress", "runCurrentSongCurrentProgress : ${_currentSongCurrentProgress.value} / ${_currentSongDuration.value}")
//            }
//        }
//    }

    fun setMediaSource(
        context: Context,
        mediaSource: ProgressiveMediaSource,
        index : Int,
        playList : List<Music>
    ) {
        _currentPlayIndex = index
        _currentPlayList.postValue(playList)

        val mediaSources = mutableListOf<ProgressiveMediaSource>()

        for (element in playList) {
            mediaSources.add(getMediaSource(
                context = context,
                musicId = element.id
            ))
        }

        if (_exoPlayer == null) {
            Log.d("setMediaSource", "setMediaSource : _exoPlayer is null")
            val newPlayer = ExoPlayer.Builder(context).build().apply {
//                setMediaSource(mediaSource)
                setMediaSources(mediaSources.toList())
                playWhenReady = true
                volume = 1f
                prepare()
                seekToDefaultPosition(index)
            }
            newPlayer.addListener(object : Player.Listener {
                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    if (playbackState == ExoPlayer.STATE_READY) {
                        Log.d("setMediaSource", "setMediaSource - ExoPlayer.STATE_READY : ${newPlayer.duration}")
                        val realDurationMillis: Long = newPlayer.duration
                        _currentSongDuration.postValue(realDurationMillis)
//
//                        if(!_isPlaying.value!!){
//                            runCurrentSongCurrentProgress()
//                        }
                    }
//                    if (playbackState == ExoPlayer.STATE_ENDED) {
////                        performEndExoPlayer()
//                    }
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    _currentSongCurrentProgress.value = 0L

//                    Log.d("setMediaSource", "setMediaSource - onMediaItemTransition : ${mediaItem?.mediaId} ${_exoPlayer!!.contentPosition}")
                }

//                override fun onIsPlayingChanged(isPlaying: Boolean) {
//                    _isPlaying.postValue(isPlaying)
//                    _isPaused.postValue(!isPlaying)
//                }
            })
            _currentSongDuration.postValue(newPlayer.duration)
            Log.d("setMediaSource", "setMediaSource : ${newPlayer.duration}")
            _currentSongCurrentProgress.postValue(0L)
            Log.d("setMediaSource", "setMediaSource : ${0L}")
//            runCurrentSongCurrentProgress()
            startUpdatePositionCoroutine()
            _exoPlayer = newPlayer
        } else {
            Log.d("setMediaSource", "setMediaSource : _exoPlayer is not null")

//            val newPlayer = ExoPlayer.Builder(context).build().apply {
//                setMediaSource(mediaSource)
//                playWhenReady = true
//                prepare()
//
//                volume = 1f
//            }
//
//            _exoPlayer = newPlayer
//            _exoPlayer!!.setMediaSource(mediaSource)
            _exoPlayer!!.setMediaSources(mediaSources.toList())
            _exoPlayer!!.seekToDefaultPosition(index)

            _currentSongDuration.value = _exoPlayer!!.duration
            Log.d("setMediaSource", "setMediaSource : ${_exoPlayer!!.duration}")
            _currentSongCurrentProgress.value = 0L
            Log.d("setMediaSource", "setMediaSource : ${0L}")
        }
    }

    fun playMusic(music: Music) {
        _isPlaying.value = true
        _isPaused.value = false
        _currentSong.value = music
    }

    fun showPlayer() {
        _isShowPlayer.value = true
    }

    fun hidePlayer() {
        _isShowPlayer.value = false
    }

    fun resumeMusic() {
        _exoPlayer?.play()
        _isPaused.value = false
        startUpdatePositionCoroutine()
    }

    fun pauseMusic() {
        _exoPlayer?.pause()
        _isPaused.value = true
        updatePositionJob?.cancel()
    }

    fun nextMusic(context: Context) {
        if(_exoPlayer != null){
            if(_exoPlayer!!.hasNextMediaItem()){

                _exoPlayer?.seekToNextMediaItem()
                _currentPlayIndex += 1
//                val currentIndex = _currentPlayIndex.value!!.plus(1)
//                Log.d("_currentPlayIndex", "_currentPlayIndex : ${_currentPlayIndex.value}")
//                _currentSong.postValue( _currentPlayList.value?.get(currentIndex))
                _currentSong.postValue(_currentPlayList.value?.get(_currentPlayIndex))

//                _currentSongDuration.value = _exoPlayer!!.duration
                _currentSongCurrentProgress.value = 0L

                updateCommentList(context)
            }
            else{
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context, "마지막 곡입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun prevMusic(context: Context) {
        Log.d("prevMusic,", "prevMusic : ${_currentPlayIndex}")
        if(_exoPlayer != null){
            if(_exoPlayer!!.hasPreviousMediaItem()){
                _exoPlayer?.seekToPreviousMediaItem()
//                Log.d(_exoPlayer?.contentPosition
//                _currentPlayIndex.value!!.minus(1)
//                _currentPlayIndex.postValue(_exoPlayer!!.currentWindowIndex)
                _currentPlayIndex -= 1
                Log.d("_currentPlayIndex", "_currentPlayIndex : ${_currentPlayIndex}")
//                val currentIndex = _currentPlayIndex.value!!.minus(1)
                _currentSong.postValue(_currentPlayList.value?.get(_currentPlayIndex))


//                _currentSongDuration.value = _exoPlayer!!.duration
                _currentSongCurrentProgress.value = 0L

                updateCommentList(context)
            }
            else{
                CoroutineScope(Dispatchers.Main).launch{
                    Toast.makeText(context, "첫번째 곡입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun stopMusic() {
        _exoPlayer?.stop()
        _exoPlayer = null
        _isPlaying.value = false
        _isPaused.value = false
        _currentSong.value = null

        _currentSongDuration.value = 0L
        _currentSongCurrentProgress.value = 0L

        _currentPlayList.value = listOf()
        _currentPlayIndex = 0

        _currentSongCommentList.value = listOf()
        updatePositionJob?.cancel()
    }

    fun addComment(
        context: Context,
        commentContent: String,
    ) {
        coroutineScope.launch {
            _currentSong.value?.id?.let {
                val result = createComment(
                    context = context,
                    musicId = it,
                    comment = commentContent
                )

                if (result) {
                    val newCommentList = getCommentList(context, _currentSong.value!!.id)
                    _currentSongCommentList.postValue(newCommentList)
                }
            }
        }
    }

    fun updateCommentList(context: Context) {
        coroutineScope.launch {
            delay(500)

            if(_currentSong.value != null) {
                val result = getCommentList(context, _currentSong.value!!.id)
                _currentSongCommentList.postValue(result)
            }


//            _currentSongCommentList.postValue(dummyCommentList)
        }
    }

    fun setLikeComment(
        context: Context,
        commentId: Int,
    ){
        coroutineScope.launch {
            val result = likeComment(
                context = context,
                commentId = commentId,
            )

            if (result) {
                val newCommentList = getCommentList(context, _currentSong.value!!.id)
                _currentSongCommentList.postValue(newCommentList)
            }
        }
    }

    fun setUnLikeComment(
        context: Context,
        commentId: Int,
    ){
        coroutineScope.launch {
            val result = unlikeComment(
                context = context,
                commentId = commentId,
            )

            if (result) {
                val newCommentList = getCommentList(context, _currentSong.value!!.id)
                _currentSongCommentList.postValue(newCommentList)
            }
        }
    }

    fun deleteSelectedComment(
        context: Context,
        commentId: Int,
    ){
        coroutineScope.launch {
            val result = deleteComment(
                context = context,
                commentId = commentId,
            )
//            Log.d("deleteSelectedComment", "deleteSelectedComment : $result")
            if (result) {
                val newCommentList = getCommentList(context, _currentSong.value!!.id)
                _currentSongCommentList.postValue(newCommentList)
                Log.d("deleteSelectedComment", "deleteSelectedComment : $newCommentList")
            }
        }
    }

    fun editCurrentComment(
        context: Context,
        commentId: Int,
        comment : String
    ){
        Log.d("editCurrentComment", "editCurrentComment : $commentId, $comment")
        coroutineScope.launch {
            val result = updateComment(
                context = context,
                commentId = commentId,
                comment = comment
            )

            if (result) {
                val newCommentList = getCommentList(context, _currentSong.value!!.id)
                _currentSongCommentList.postValue(newCommentList)
            }
        }
    }

    override fun onCleared() {
        updatePositionJob?.cancel()
        super.onCleared()
    }

}
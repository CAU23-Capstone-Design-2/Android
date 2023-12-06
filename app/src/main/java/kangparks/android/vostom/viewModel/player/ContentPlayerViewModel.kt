package kangparks.android.vostom.viewModel.player

import android.content.Context
import android.media.MediaSession2
import android.media.session.MediaSession
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kangparks.android.vostom.models.content.Comment
import kangparks.android.vostom.models.content.Music
import kangparks.android.vostom.utils.dummy.dummyCommentList
import kangparks.android.vostom.utils.helper.media.getMediaSource
import kangparks.android.vostom.utils.networks.comment.createComment
import kangparks.android.vostom.utils.networks.comment.deleteComment
import kangparks.android.vostom.utils.networks.comment.getCommentList
import kangparks.android.vostom.utils.networks.comment.likeComment
import kangparks.android.vostom.utils.networks.comment.unlikeComment
import kangparks.android.vostom.utils.networks.comment.updateComment
import kotlinx.coroutines.CoroutineScope
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
    private val _currentSong: MutableLiveData<Music?> = MutableLiveData<Music?>(null)
    private val _currentPlayList : MutableLiveData<List<Music>> = MutableLiveData<List<Music>>(listOf())
    private val _currentPlayIndex : MutableLiveData<Int> = MutableLiveData<Int>(-1)

    private val _isShowPlayer: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private val _currentSongCommentList: MutableLiveData<List<Comment>> =
        MutableLiveData<List<Comment>>(listOf())

    val isPlaying: LiveData<Boolean> = _isPlaying
    val isPaused: LiveData<Boolean> = _isPaused
    val currentSong: LiveData<Music?> = _currentSong
    val currentPlayList: LiveData<List<Music>> = _currentPlayList
    val currentPlayIndex: LiveData<Int> = _currentPlayIndex


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

    fun setPlayer(player: ExoPlayer) {
        Log.d("setPlayer", "setPlayer : ${player}")
        _exoPlayer = player
    }

    fun getPlayer(
        context: Context
    ): ExoPlayer? {
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

    fun setMediaSource(
        context: Context,
        mediaSource: ProgressiveMediaSource,
        index : Int,
        playList : List<Music>
    ) {
        _currentPlayIndex.postValue(index)
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
                seekToDefaultPosition(index)
                playWhenReady = true
                prepare()

                volume = 1f
            }

            _exoPlayer = newPlayer
//            newPlayer.

//            newPlayer.play()
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
    }

    fun pauseMusic() {
        _exoPlayer?.pause()
        _isPaused.value = true
    }

    fun nextMusic() {
        if(_exoPlayer != null){
            if(_exoPlayer!!.hasNext() == true){
                _exoPlayer?.next()
            }
        }
    }

    fun prevMusic() {
        if(_exoPlayer != null){
            if(_exoPlayer!!.hasPrevious() == true){
                _exoPlayer?.previous()
            }
        }
    }

    fun stopMusic() {
        _exoPlayer?.stop()
        _exoPlayer = null
        _isPlaying.value = false
        _isPaused.value = false
        _currentSong.value = null
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

}
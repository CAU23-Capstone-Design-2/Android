package kangparks.android.vostom.viewModel.bottomsheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kangparks.android.vostom.models.content.Singer
import kangparks.android.vostom.models.content.Song

class OthersContentDetailViewModel constructor(private val _currentSinger : Singer): ViewModel() {

    private val _id : Int = _currentSinger.id
    private val _listOfCoverSongs = MutableLiveData<List<Song>>(
        listOf(
            Song(
                id = 1,
                title = "Hype Boy",
                singer = "뉴진스",
                imageUri = "https://image.bugsm.co.kr/album/images/500/40780/4078016.jpg"
            ),
            Song(
                id = 2,
                title = "Bubble",
                singer = "STAYC",
                imageUri = "https://images.genius.com/db59d80f50cce13d329bdf098a632d4e.1000x1000x1.jpg"
            ),
            Song(
                id = 3,
                title = "한 페이지가 될 수 있게",
                singer = "데이식스",
                imageUri = "https://image.bugsm.co.kr/album/images/500/202657/20265759.jpg"
            ),
            Song(
                id = 4,
                title = "여행",
                singer = "볼빨간 사춘기",
                imageUri = "https://image.bugsm.co.kr/album/images/500/201687/20168753.jpg"
            ),
            Song(
                id = 5,
                title = "사건의 지평선",
                singer = "윤하",
                imageUri = "https://image.bugsm.co.kr/album/images/500/40734/4073469.jpg"
            ),
            Song(
                id = 6,
                title = "어떻게 이별까지 사랑하겠어, 널 사랑하는 거지",
                singer = "악동뮤지션",
                imageUri = "https://image.bugsm.co.kr/album/images/500/202788/20278851.jpg"
            ),
            Song(
                id = 7,
                title = "힘내! Way to go",
                singer = "소녀시대",
                imageUri = "https://image.bugsm.co.kr/album/images/500/1745/174521.jpg"
            ),
            Song(
                id = 8,
                title = "너 없인 안 된다",
                singer = "비투비",
                imageUri = "https://image.bugsm.co.kr/album/images/500/201722/20172261.jpg"
            ),

        )
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

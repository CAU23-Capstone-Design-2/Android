package kangparks.android.vostom.utils.helper.search
import android.util.Log
import kangparks.android.vostom.models.item.YoutubePlayItem
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


@OptIn(ExperimentalStdlibApi::class)
suspend fun getSongList(
    keyword : String,
    postWord : String = ""
) : List<YoutubePlayItem>?{

    val baseUrl = "https://www.youtube.com/results?search_query=${postWord}"
    var listOfSong = mutableListOf<YoutubePlayItem>()
    var countItem = 0

    val keywordList = keyword.split(" ")

    var keywordForQuery = ""

    for(i in keywordList){
        keywordForQuery += "$i+"
    }
    keywordForQuery = keywordForQuery.substring(0, keywordForQuery.length-1)

    try {
        val docs : Document = Jsoup.connect(baseUrl+keywordForQuery).get()
        Log.d("getJsonOnject :","docs : $docs")

        val videoResults = docs.select("script")

        for(video in videoResults){
            try {
                Log.d("getJsonOnject :","video : $video")
                Log.d("getJsonOnject :","video.data().length : ${video.data().length}")
                val scriptData = video.data()
                if(scriptData.contains("var ytInitialData")){
                    val startIndex = scriptData.indexOf("{\"responseContext\":")
                    val endIndex = scriptData.indexOf(";", startIndex)
                    val ytInitialData = scriptData.substring(startIndex, endIndex + 1)

                    val jsonObject = JSONObject(ytInitialData)

                    val contentsObject = jsonObject.getJSONObject("contents")
                    val twoColumnSearchResultsRenderer = contentsObject.getJSONObject("twoColumnSearchResultsRenderer")
                    val primaryContents = twoColumnSearchResultsRenderer.getJSONObject("primaryContents")
                    val sectionListRenderer = primaryContents.getJSONObject("sectionListRenderer")
                    val contentsArray = sectionListRenderer.getJSONArray("contents")

                    val itemSectionRenderer = contentsArray.getJSONObject(0).getJSONObject("itemSectionRenderer")
                    val contents = itemSectionRenderer.getJSONArray("contents")
                    Log.d("getJsonOnject :","content : $contents")

                    for (j in 0..<contents.length()){
                        val content = contents.getJSONObject(j)

                        Log.d("getJsonOnject :","content : $content")

                        try {
                            val videoRenderer = content.getJSONObject("videoRenderer")

                            val thumbnail = videoRenderer.getJSONObject("thumbnail")
                            val thumbnails = thumbnail.getJSONArray("thumbnails")
                            val thumbnailUrl = thumbnails.getJSONObject(0).getString("url")

                            val title = videoRenderer.getJSONObject("title")
                            val runs = title.getJSONArray("runs")
                            val titleText = runs.getJSONObject(0).getString("text")

                            val navigationEndpoint = videoRenderer.getJSONObject("navigationEndpoint")
                            val commandMetadata = navigationEndpoint.getJSONObject("commandMetadata")
                            val webCommandMetadata = commandMetadata.getJSONObject("webCommandMetadata")
                            val url = webCommandMetadata.getString("url")
//                        val urlParts = url.split("/watch?v=", "&pp")

                            listOfSong.add(YoutubePlayItem(countItem, titleText, thumbnailUrl, url))
                            countItem++
                        }catch(e: Exception){
                            Log.d("Search Result :","$e")
                        }
                    }
                }
            }catch(e: Exception){
                Log.d("loop executon :","$e")
            }

        }
    } catch (e: Exception){
        Log.e("Error get Youtube list :","${e.message}")
        return null
    }

    return listOfSong
}
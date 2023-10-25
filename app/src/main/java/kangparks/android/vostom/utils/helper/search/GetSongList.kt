package kangparks.android.vostom.utils.helper.search
import android.icu.util.RangeValueIterator
import android.util.Log
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

const val baseUrl = "https://www.youtube.com/results?search_query=tj+%EB%85%B8%EB%9E%98%EB%B0%A9+"

@OptIn(ExperimentalStdlibApi::class)
suspend fun getSongList(keyword : String){
    val keywordList = keyword.split(" ")

    var keywordForQuery = ""

    for(i in keywordList){
        keywordForQuery += "$i+"
    }
    keywordForQuery = keywordForQuery.substring(0, keywordForQuery.length-1)

//    lateinit var thumbnailUri : String
//    lateinit var title : String
//    lateinit var contentUri : String

    Log.d("Search Result :","keyword : $keywordForQuery")

    try {
        Log.d("Search Result :","스크래핑 시작")
        val docs : Document = Jsoup.connect(baseUrl+keywordForQuery).get()

        val videoResults = docs.select("script")

        Log.d("Search Result :","스크래핑 성공")

        for(video in videoResults){
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

                for (j in 0..<contents.length()){
                    val content = contents.getJSONObject(j)

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

                        Log.d("Search Result :","thumbnailUrl: $thumbnailUrl")
                        Log.d("Search Result :", "title: $titleText")
                        Log.d("Search Result :","url: $url")
                    }catch(e: Exception){
                        println(e)
                        Log.d("Search Result :","$e")
                    }
                }
            }
        }


    } catch (
        e: Exception
    ){
//        println(e.message)
        Log.d("Search Result :","$e")
        return
    }

}
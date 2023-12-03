package kangparks.android.vostom.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kangparks.android.vostom.MainActivity
import kangparks.android.vostom.R
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.utils.networks.learning.getLearningState
import kangparks.android.vostom.utils.store.getAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//import java.util.Timer
//import java.util.TimerTask
class LearningStateCheckerService : Service() {

    private val TAG = "LearningStateCheckerService"
    val context = this
    private var currentInterval: Long = 1 * 60 * 1000

    private lateinit var foregroundHandler: Handler
    private lateinit var handlerThread: HandlerThread

//    private var timer: Timer? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    public override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        Log.d(TAG, "onCreate : start LearningStateCheckerService")

        handlerThread = HandlerThread("LearningStateCheckerService")
        handlerThread.start()
        foregroundHandler = Handler(handlerThread.looper)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand : start LearningStateCheckerService")

        startForeground(NOTIFICATION_ID, learningStateNotification())

        val userToken = getAccessToken(this)
        val intent = Intent("StateOfLearningState")

        foregroundHandler.postDelayed(object : Runnable {
            override fun run() {
                CoroutineScope(Dispatchers.IO).launch{
                    if(userToken != null){
                        val result = getLearningState(accessToken = userToken)
                        Log.d(TAG, "current State : ${result.state}")
                        if(result == LearningState.AfterLearning){
                            intent.putExtra("state", result.state)

                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

                            stopForeground(true)
                            stopSelf()
                        }
                    }
                }

                Log.d(TAG, "1분마다 확인 중")
                // 다음 실행을 위해 다시 postDelayed 호출
                foregroundHandler.postDelayed(this, 10000) // 1분마다 실행 (밀리초 단위)
            }
        }, 0)


//        return super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        handlerThread.quitSafely()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ){
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, completeLearningNotification())

        }

        super.onDestroy()
        Log.d(TAG, "onDestroy : stop LearningStateCheckerService")

    }

    private fun checkUserLearningState() {
//        timer = Timer()
//        timer!!.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                // 주기적으로 서버에 요청을 보내는 작업을 여기에 구현
//            }
//        }, 0, 60000)
    }



    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, "Vostom", importance)
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun learningStateNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Vostom")
            .setContentText("Vostom이 사용자의 목소리를 학습중 입니다.\n완료되면 알림을 통해 알려드릴게요!")
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }

    private fun completeLearningNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)


        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Vostom")
            .setContentText("Vostom 학습이 완료 되었습니다.🥳\n앱을 실행해서 확인해보세요!")
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }


    companion object {
        private const val CHANNEL_ID = "Vostom"
        private const val NOTIFICATION_ID = 1
    }


}
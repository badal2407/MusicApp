package com.das_develop.cloudpod

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NotificationReceiver:BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            ApplicationClass.PREVIOUS ->if(PlayerActivity.musicListPA.size > 1) prevNextSong(increment = false,context =context!!)
            ApplicationClass.PLAY ->
                if(PlayerActivity.isPlaying) pauseMusic() else playMusic()
            ApplicationClass.NEXT ->if(PlayerActivity.musicListPA.size > 1) prevNextSong(increment = true,context =context!!)
            ApplicationClass.EXIT -> {
                exitApplication()
            }

        }
    }

    private fun playMusic(){
        PlayerActivity.isPlaying = true
        PlayerActivity.musicService!!.mediaPlayer!!.start()
        PlayerActivity.musicService!!.showNotification(R.drawable.pause_icon)
        PlayerActivity.binding.playPauseBtnPA.setIconResource(R.drawable.pause_icon)
       try{ NowPlaying.binding.playPauseBtnNP.setIconResource(R.drawable.pause_icon)}catch (_:Exception){}
    }

    private fun pauseMusic(){
        PlayerActivity.isPlaying = false
        PlayerActivity.musicService!!.mediaPlayer!!.pause()
        PlayerActivity.musicService!!.showNotification(R.drawable.play_icon)
        PlayerActivity.binding.playPauseBtnPA.setIconResource(R.drawable.play_icon)
       try {
           NowPlaying.binding.playPauseBtnNP.setIconResource(R.drawable.play_icon)
       }catch (_:Exception){}
    }

    private fun prevNextSong(increment:Boolean , context: Context){
        setSongPosition(increment = increment)
       PlayerActivity.musicService!!.createMediaPlayer()
        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.logo_icon_slash_screen).centerCrop())
            .into(PlayerActivity.binding.songImgPA)

        PlayerActivity.binding.songNamePA.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title

        Glide.with(context)
            .load(PlayerActivity.musicListPA[PlayerActivity.songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.logo_icon_slash_screen).centerCrop())
            .into(NowPlaying.binding.songImageNP)

        NowPlaying.binding.songNameNP.text = PlayerActivity.musicListPA[PlayerActivity.songPosition].title
    playMusic()

        PlayerActivity.fIndex = favouriteChecker(PlayerActivity.musicListPA[PlayerActivity.songPosition].id)
        if(PlayerActivity.isFavourite) PlayerActivity.binding.favouriteBtnPA.setImageResource(R.drawable.favourite_icon)
        else PlayerActivity.binding.favouriteBtnPA.setImageResource(R.drawable.favorite_empty)
    }
}
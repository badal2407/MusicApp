package com.das_develop.cloudpod

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.das_develop.cloudpod.databinding.ActivityPlaylistBinding
import com.das_develop.cloudpod.databinding.AddPlaylistDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Locale

class PlaylistActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPlaylistBinding
    private lateinit var adapter: PlaylistViewAdapter

    companion object
    {
        var musicPlaylist: MusicPlaylist = MusicPlaylist()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.currentTheme[MainActivity.themeIndex])
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.playlistRV.setHasFixedSize(true)
        binding.playlistRV.setItemViewCacheSize(13)

        binding.playlistRV.layoutManager = GridLayoutManager(this,2)
        adapter = PlaylistViewAdapter(this, playlistList = musicPlaylist.ref)
        binding.playlistRV.adapter =adapter

        binding.backBtnPLA.setOnClickListener{finish()}

        binding.addPlaylistBtn.setOnClickListener {
            customAlertDialog()
        }
    }

    private fun customAlertDialog(){

        val customDialog = LayoutInflater.from(this).inflate(R.layout.add_playlist_dialog,binding.root,false)
       val binder =AddPlaylistDialogBinding.bind(customDialog)
        val builder = MaterialAlertDialogBuilder(this)
       val dialog =  builder.setView(customDialog)
            .setTitle("Playlist Details")
            .setPositiveButton("Add"){dialog, _ ->
            val playlistName = binder.playlistName.text
                val createdBy = binder.yourName.text
                if(playlistName != null && createdBy != null)
                    if(playlistName.isNotEmpty() && createdBy.isNotEmpty()){
                        addPlaylist(playlistName.toString() , createdBy.toString())
                    }
               dialog.dismiss()
            }.create()
        dialog.show()

    }

    private fun addPlaylist(name:String , createdBy: String){
        var playlistExists = false

        for(i in musicPlaylist.ref){
            if (name == i.name){
                playlistExists = true
                break
            }
        }

        if(playlistExists) Toast.makeText(this,"PlayList exist!!",Toast.LENGTH_LONG).show()
        else{
            val tempPlaylist = Playlist()
            tempPlaylist.name = name
            tempPlaylist.playlist = ArrayList()
            tempPlaylist.createdBy = createdBy
            val calender = java.util.Calendar.getInstance().time
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            tempPlaylist.createdOn = sdf.format(calender)
            musicPlaylist.ref.add(tempPlaylist)
            adapter.refreshPlaylist()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
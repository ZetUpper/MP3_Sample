package com.cookandroid.mp3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding: ActivityMainBinding get() = requireNotNull(_viewBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(viewBinding.fragmentCintainer.id, PlayerFragment.newInstance())
            .commit()
    }

    fun MusicEntity.mapper(id:Long):MusicModel =
        MusicModel(id=id, track, streamUrl, artist, coverUrl)

    fun MusicDto.mapper(): PlayerModel =
        PlayerModel(
            playMusicList = musics.mapIndexed { index, musicEntity ->
                musicEntity.mapper(index.toLong())
            }
        )

    private var player: SimpleExoplayer?= null
    private fun initPlayer() {
        context?.let{
            player = SimpleExopPlayer.Builder(it).build()
        }

        binding.playerView.player = player
    }

    private fun setMusicList(modelList: List<MusicModel>){
        player ?: return
        context?.let {
            player?.addMedialItems(modelList.map { musicModel ->
                MedialItem.Builder()
                    .setMediald(musicModel.id.toString())
                    .setUrl(musicModel.streamUrl)
                    .build()
            })
            player?.prepare()
        }

        player?.addListener(object : Player.EventListener) {
        }
        override fun onlsPlayingChanged(isPlaying: Boolean) {

        }

        override fun onIsPlayingChanged(isPlaying:Boolean) {
            super.onIsPlayingChanged(isPlaying)

            if(isPlaying) {
                binding.playControlImageView.setImageResource(R.drawble.ic_baseline_pause_48)
            }else{
                binding.playControlImageView.setImageResource(R.drawble.ic_baseline_play_arrow_24)
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean)
    }
}
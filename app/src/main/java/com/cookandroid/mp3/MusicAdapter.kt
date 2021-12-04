package com.cookandroid.mp3

import android.graphics.Color
import android.view.ViewGroup
import android.view.ViewParent

class MusicAdapter(private val callback: (MusicModel) -> Unit): ListAdapter<MusicModel, MusicAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val itemMusicBinding: ItemMusicBinding):RecyclerView.ViewHolder(itemMusicBinding.root){
        fun bind(music: MusicModel){
            itemMusicBinding.itemArtistTextView.text = music.artist
            itemMusicBinding.itemTrackTextView.text = music.track

            Glide.with(itemMusicBinding.itemCoverImageView.context)
                .load(music.coverUrl)
                .into(itemMusicBinding.itemCoverImageView)

            if(music.isPlaying){
                itemView.setBackgroundColor(Color.GRAY)
            }else {
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }

            itemView.setOnClickListener {
                callback(music)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<MusicModel>(){
            override fun areItemsTheSame(oldItem: MusicModel, newItem: MusicMode): Boolean {
                return oldItem.id == newitem.id
            }

            override fun areContentsTheSame(oldItem: MusicModel, newItem:MusicModel):Boolean {
                return oldItem == newItem
            }
        }
    }
}
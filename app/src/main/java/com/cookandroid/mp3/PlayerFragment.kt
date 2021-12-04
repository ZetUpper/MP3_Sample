package com.cookandroid.mp3

import androidx.fragment.app.Fragment

class PlayerFragment : Fragment(R.layout.fragment_player) {

    companion object {
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }
}
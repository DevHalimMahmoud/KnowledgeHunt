package com.abdelHalimMahmoud.knowledgehunt.domain.utils

import com.abdelHalimMahmoud.knowledgehunt.R
import com.amrdeveloper.reactbutton.Reaction


object FbReactions {
//    var defaultReact = Reaction(
//        ReactConstants.LIKE,
//        ReactConstants.DEFAULT,
//        ReactConstants.GRAY,
//        R.drawable.ic_gray_like
//    )
//
//    var reactions = arrayOf(
//        Reaction(ReactConstants.LIKE, ReactConstants.BLUE, R.drawable.like),
//        Reaction(ReactConstants.CELEBRATE, ReactConstants.RED_LOVE, R.drawable.celebrate),
//        Reaction(ReactConstants.SUPPORT, ReactConstants.YELLOW_WOW, R.drawable.support),
//        Reaction(ReactConstants.LOVE, ReactConstants.YELLOW_WOW, R.drawable.love),
//        Reaction(ReactConstants.INSIGHTFUL, ReactConstants.YELLOW_HAHA, R.drawable.insightful),
//        Reaction(ReactConstants.CURIOUS, ReactConstants.RED_ANGRY, R.drawable.curious)
//    )

    var defaultReact = Reaction(
        ReactConstants.LIKE,
        ReactConstants.DEFAULT,
        ReactConstants.GRAY,
        R.drawable.ic_gray_like
    )

    var reactions = arrayOf(
        Reaction(ReactConstants.LIKE, ReactConstants.BLUE, R.drawable.ic_like),
        Reaction(ReactConstants.LOVE, ReactConstants.RED_LOVE, R.drawable.ic_heart),
        Reaction(ReactConstants.SMILE, ReactConstants.YELLOW_WOW, R.drawable.ic_happy),
        Reaction(ReactConstants.WOW, ReactConstants.YELLOW_WOW, R.drawable.ic_surprise),
        Reaction(ReactConstants.SAD, ReactConstants.YELLOW_HAHA, R.drawable.ic_sad),
        Reaction(ReactConstants.ANGRY, ReactConstants.RED_ANGRY, R.drawable.ic_angry)
    )
}
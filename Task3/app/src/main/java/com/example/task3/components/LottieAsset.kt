package com.example.task3.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


/**
 *  Composable that loads a Lottie Animation by its [image] file identifier
 *  and displays it.
 *
 *  @param[modifier] the Modifier for this composable.
 *  @param[image] an Integer
 * */
@Composable
fun LottieAsset(modifier: Modifier, image:Int){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))
    LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = modifier)
}
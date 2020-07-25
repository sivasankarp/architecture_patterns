package com.teckudos.architecturepatterns.mvi.ui

import com.teckudos.architecturepatterns.mvi.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}
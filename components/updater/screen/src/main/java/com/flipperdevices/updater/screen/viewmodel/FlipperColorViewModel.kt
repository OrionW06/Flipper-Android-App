package com.flipperdevices.updater.screen.viewmodel

import androidx.datastore.core.DataStore
import com.flipperdevices.core.preference.pb.HardwareColor
import com.flipperdevices.core.preference.pb.PairSettings
import com.flipperdevices.core.ui.lifecycle.DecomposeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FlipperColorViewModel @Inject constructor(
    settings: DataStore<PairSettings>
) : DecomposeViewModel() {
    private val colorFlipperState = MutableStateFlow<HardwareColor>(HardwareColor.WHITE)

    init {
        settings.data.onEach {
            colorFlipperState.emit(it.hardware_color)
        }.launchIn(viewModelScope)
    }

    fun getFlipperColor(): StateFlow<HardwareColor> = colorFlipperState
}

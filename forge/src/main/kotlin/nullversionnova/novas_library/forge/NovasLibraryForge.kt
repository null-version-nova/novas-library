package nullversionnova.novas_library.forge

import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import nullversionnova.novas_library.NovasLibrary
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(NovasLibrary.MOD_ID)
object NovasLibraryForge {
    init {
        EventBuses.registerModEventBus(NovasLibrary.MOD_ID, MOD_BUS)
    }
}
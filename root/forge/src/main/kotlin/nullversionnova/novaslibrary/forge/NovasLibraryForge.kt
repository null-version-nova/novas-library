package nullversionnova.novaslibrary.forge

import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import nullversionnova.novaslibrary.NovasLibrary
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(NovasLibrary.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object NovasLibraryForge {
    init {
        EventBuses.registerModEventBus(NovasLibrary.MOD_ID, MOD_BUS)
        NovasLibrary.init()
    }
}
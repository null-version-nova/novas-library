package nullversionnova.novaslibrary.forge

import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.recipes.forge.ShapedRecipeProvider
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(NovasLibrary.MOD_ID)
object NovasLibraryForge {
    init {
        EventBuses.registerModEventBus(NovasLibrary.MOD_ID, MOD_BUS)
        NovasLibrary.init()
    }

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        event.generator.addProvider(
            event.includeServer(),
            ShapedRecipeProvider(event.generator.packOutput)
        )
    }
}
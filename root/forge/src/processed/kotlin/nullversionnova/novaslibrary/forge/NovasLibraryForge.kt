package nullversionnova.novaslibrary.forge

import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.datagen.forge.RecipeProcessor
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent

@Mod(NovasLibrary.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object NovasLibraryForge {
    init {
        EventBuses.registerModEventBus(NovasLibrary.MOD_ID, MOD_BUS)
        NovasLibrary.init()
    }

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        println("Gathering data...")
        event.generator.addProvider(
            RecipeProcessor(event.generator)
        )
    }
}

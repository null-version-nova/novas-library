package nullversionnova.novaslibrary.datagen.forge

import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
//#if MC >= 11903
import net.minecraftforge.data.event.GatherDataEvent
//#else
//$import net.minecraftforge.forge.event.lifecycle.GatherDataEvent
//$import nullversionnova.novaslibrary.datagen.forge.RecipeProcessor
//#endif

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object DatagenEvents {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        println("Gathering data...")
        event.generator.addProvider(
            //#if MC>=11903
            event.includeServer(),
            RecipeProcessor(event.generator.packOutput),
            //#else
            //$RecipeProcessor(event.generator)
            //#endif
        )
    }
}
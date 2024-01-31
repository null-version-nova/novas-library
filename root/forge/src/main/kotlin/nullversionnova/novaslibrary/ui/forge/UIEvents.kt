package nullversionnova.novaslibrary.ui.forge

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object UIEvents {
    val creativeTabItemBufferKeys = mutableListOf<Pair<() -> CreativeModeTab,() -> Item>>()
    private lateinit var creativeTabItemBuffer : MutableMap<CreativeModeTab,MutableList<Item>>
    @SubscribeEvent
    fun buildContents(event: BuildCreativeModeTabContentsEvent) {
        // Initial setup
        if (!::creativeTabItemBuffer.isInitialized) {
            creativeTabItemBuffer = mutableMapOf()
            creativeTabItemBufferKeys.forEach {
                if (creativeTabItemBuffer[it.first()] == null) creativeTabItemBuffer.put(it.first(), mutableListOf())
                creativeTabItemBuffer[it.first()]!!.add(it.second())
            }
        }
        if (event.tab in creativeTabItemBuffer.keys) {
            creativeTabItemBuffer[event.tab]!!.forEach {
                event.accept(it)
            }
        }
    }
}
//#excludeif MC<11902
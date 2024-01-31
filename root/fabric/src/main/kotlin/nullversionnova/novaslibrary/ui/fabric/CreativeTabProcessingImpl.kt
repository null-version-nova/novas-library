package nullversionnova.novaslibrary.ui.fabric

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.registries.BuiltInRegistries

object CreativeTabProcessingImpl {
    @JvmStatic
    fun addItemToTab(item: () -> Item, tab: () -> CreativeModeTab) {
        ItemGroupEvents.modifyEntriesEvent(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(tab()).get()).register { content ->
            content.accept(item)
        }
    }
}
//#excludeif MC<11902
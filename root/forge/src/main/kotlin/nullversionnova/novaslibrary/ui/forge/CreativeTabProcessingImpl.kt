package nullversionnova.novaslibrary.ui.forge

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item

object CreativeTabProcessingImpl {
    @JvmStatic
    fun addItemToTab(item: () -> Item, tab: () -> CreativeModeTab) {
        UIEvents.creativeTabItemBufferKeys.add(Pair(tab,item))
    }
}
//#excludeif MC<11902
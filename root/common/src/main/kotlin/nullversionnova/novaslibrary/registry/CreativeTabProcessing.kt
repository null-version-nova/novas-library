package nullversionnova.novaslibrary.registry

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import java.lang.AssertionError

object CreativeTabProcessing {
    @JvmStatic
    @ExpectPlatform
    fun addItemToTab(item: () -> Item, tab: () -> CreativeModeTab) {
        throw AssertionError()
    }
}
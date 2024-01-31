package nullversionnova.novaslibrary.ui

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item

fun CreativeModeTab.append(item: () -> Item) {
    CreativeTabProcessing.addItemToTab(item) { this }
}
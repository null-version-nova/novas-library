package nullversionnova.novaslibrary.registry

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import nullversionnova.novaslibrary.util.RegistryDelegate

class ItemRegistry(id: String, val tab: (() -> CreativeModeTab)? = null) : GenericRegistry<Item>(id,Registries.ITEM) {
    fun registerWithProperties(path : String, properties: () -> Item.Properties = {Item.Properties()}) : RegistryDelegate<Item> {
        return super.register(path) { Item(getProperties(properties())) }.also { if (tab != null) CreativeTabProcessing.addItemToTab({it.instance},tab) }
    }
    fun getProperties(properties: Item.Properties = Item.Properties()): Item.Properties {
        return properties
    }
}
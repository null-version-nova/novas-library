package nullversionnova.novaslibrary.registry

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import nullversionnova.novaslibrary.util.RegistryDelegate

class ItemRegistry(id: String, val tab: CreativeModeTab? = null) : GenericRegistry<Item>(id,Registries.ITEM) {
    fun registerWithProperties(path : String, properties: () -> Item.Properties = {Item.Properties()}) : RegistryDelegate<Item> {
        return register(path) { Item(getProperties(properties())) }
    }
    override fun <U : Item> register(path : String, constructor: () -> U) : RegistryDelegate<U> {
        return super.register(path, constructor).also { tab?.append { it.instance } }
    }
    fun getProperties(properties: Item.Properties = Item.Properties()): Item.Properties {
        return properties
    }
}
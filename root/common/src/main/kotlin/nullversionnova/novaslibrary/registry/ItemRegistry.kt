package nullversionnova.novaslibrary.registry

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import nullversionnova.novaslibrary.util.RegistryDelegate
//#if MC>=11902
import nullversionnova.novaslibrary.ui.CreativeTabProcessing
//#endif

class ItemRegistry(id: String, val tab: (() -> CreativeModeTab)? = null) : GenericRegistry<Item>(id,Registries.ITEM) {
    fun registerWithProperties(path : String, properties: () -> Item.Properties = {Item.Properties()}) : RegistryDelegate<Item> {
        return register(path) { Item(getProperties(properties())) }
    }
    override fun <U : Item> register(path : String, constructor: () -> U) : RegistryDelegate<U> {
        return super.register(path, constructor)
            //#if MC >=11903
            .also { if (tab != null) CreativeTabProcessing.addItemToTab({it.instance},tab)  }
            //#endif
    }
    fun getProperties(properties: Item.Properties = Item.Properties()): Item.Properties {
        //#if MC >=11903
        return properties
        //#else
        //$return if (tab == null) properties else properties.tab(tab.invoke())
        //#endif
    }
}
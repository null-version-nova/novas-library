package nullversionnova.novaslibrary.registry

//#if MC>=11902
import nullversionnova.novaslibrary.ui.CreativeTabProcessing
//#endif
import dev.architectury.event.events.common.LifecycleEvent
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Taggable
import nullversionnova.novaslibrary.items.BaseItem
import nullversionnova.novaslibrary.items.ModellableItem
import nullversionnova.novaslibrary.util.RegistryDelegate


open class ItemRegistry(id: String, val tab: (() -> CreativeModeTab)? = null) : GenericRegistry<Item>(id,Registries.ITEM) {
    open fun registerWithProperties(path : String, properties: () -> Item.Properties = {Item.Properties()}) : RegistryDelegate<Item> {
        return register(path) { BaseItem(getProperties(properties())) }
    }
    override fun <U : Item> register(path : String, constructor: () -> U) : RegistryDelegate<U> {
        return super.register(path, constructor)
            //#if MC >=11903
            .also { if (tab != null) CreativeTabProcessing.addItemToTab({it.instance},tab)  }
            //#endif
            .also {
                LifecycleEvent.SETUP.register {
                    val item = it.instance
                    if (item is ModellableItem) GeneralDataProcessing.registerItemModel(item.getModel())
                    if (item is Taggable<*>) GeneralDataProcessing.registerTags(item)
                }
            }
    }
    fun getProperties(properties: Item.Properties = Item.Properties()): Item.Properties {
        //#if MC >=11903
        return properties
        //#else
        //$return if (tab == null) properties else properties.tab(tab.invoke())
        //#endif
    }
}
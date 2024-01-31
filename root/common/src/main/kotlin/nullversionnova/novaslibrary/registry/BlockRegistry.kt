package nullversionnova.novaslibrary.registry

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.util.RegistryDelegate

open class BlockRegistry(id: String, val tab: (() -> CreativeModeTab)? = null) : GenericRegistry<Block>(id, Registries.BLOCK) {
    private val items = GenericRegistry(id, Registries.ITEM)

    fun <U : Block> registerWithItem(path : String, constructor : () -> U) : RegistryDelegate<U> {
        return registerWithItem(path,{if (tab == null)  Item.Properties()  else Item.Properties().`arch$tab`(tab!!())},constructor)
    }
    fun <U : Block> registerWithItem(path: String, properties: () -> Item.Properties, constructor: () -> U) : RegistryDelegate<U> {
        val block = register(path,constructor)
        items.register(path) { BlockItem(block.instance,if (tab == null) properties() else properties().`arch$tab`(tab!!())) }
        return block
    }

    override fun register() {
        beginRegister()
        items.register()
        finishRegister()
    }
}
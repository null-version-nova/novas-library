package nullversionnova.novaslibrary.registry

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.util.RegistryDelegate

open class BlockRegistry(id: String, tab: (() ->CreativeModeTab)? = null) : GenericRegistry<Block>(id, Registries.BLOCK) {
    private val items = ItemRegistry(id,tab)

    open fun <U : Block> registerWithItem(path : String, constructor : () -> U) : RegistryDelegate<U> {
        return registerWithItem(path,{items.getProperties()},constructor)
    }
    open fun <U : Block> registerWithItem(path: String, properties: () -> Item.Properties, constructor: () -> U) : RegistryDelegate<U> {
        val block = register(path,constructor)
        items.register(path) { BlockItem(block.instance,items.getProperties()) }
        return block
    }

    override fun register() {
        beginRegister()
        items.register()
        finishRegister()
    }
}
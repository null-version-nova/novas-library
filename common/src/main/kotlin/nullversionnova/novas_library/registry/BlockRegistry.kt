package nullversionnova.novas_library.registry

import net.minecraft.core.Registry
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import nullversionnova.novas_library.util.RegistryDelegate

open class BlockRegistry(id: String) : GenericRegistry<Block>(id, Registry.BLOCK_REGISTRY) {
    private val items = GenericRegistry<Item>(id,Registry.ITEM_REGISTRY)

    fun registerWithItem(path : String, constructor : () -> Block) : RegistryDelegate<out Block> {
        val block = register(path,constructor)
        items.register(path) { BlockItem(block.instance,Item.Properties()) }
        return block
    }

    override fun register() {
        beginRegister()
        items.register()
        finishRegister()
    }
}
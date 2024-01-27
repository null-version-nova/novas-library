package nullversionnova.novaslibrary.registry

import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.util.RegistryDelegate

open class BlockRegistry(id: String) : GenericRegistry<Block>(id, Registries.BLOCK) {
    private val items = GenericRegistry<Item>(id,Registries.ITEM)

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
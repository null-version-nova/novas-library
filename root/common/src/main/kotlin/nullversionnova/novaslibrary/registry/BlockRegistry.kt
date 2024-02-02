package nullversionnova.novaslibrary.registry

import dev.architectury.event.events.common.LifecycleEvent
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.blocks.LootableBlock
import nullversionnova.novaslibrary.blocks.ModellableBlock
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Taggable
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
    override fun <U: Block> register(path : String, constructor : () -> U) : RegistryDelegate<U> {
        return super.register(path, constructor).also {
            LifecycleEvent.SETUP.register {
                val block = it.instance
                if (block is ModellableBlock) GeneralDataProcessing.registerBlockModel(block.getModel())
                if (block is LootableBlock) GeneralDataProcessing.registerBlockLootTable(block.getLoot())
                if (block is Taggable<*>) GeneralDataProcessing.registerTags(block)
            }
        }
    }

    override fun register() {
        super.register()
        items.register()
    }
}
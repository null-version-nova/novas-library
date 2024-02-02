package nullversionnova.novaslibrary.blocks

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.world.level.block.Block
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import nullversionnova.novaslibrary.interfaces.Taggable

open class BaseBlock(properties: Properties) : Block(properties), ModellableBlock, LootableBlock {
    override fun getModel(): (BlockModelGenerators) -> Unit = {
        it.createTrivialCube(this)
    }

    override fun getLoot(): (BlockLootSubProvider) -> Unit = {
        it.dropSelf(this)
    }
}
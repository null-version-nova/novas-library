package nullversionnova.novaslibrary.blocks

import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.blocks.BaseBlock
import nullversionnova.novaslibrary.interfaces.Taggable

class BaseMineableBlock(properties: Properties, val level : Int = 0) : BaseBlock(properties), Taggable.TaggableBlock {
    override fun getTags(): Set<TagKey<Block>> {
        val set = mutableSetOf<TagKey<Block>>()
        set.add(BlockTags.MINEABLE_WITH_PICKAXE)
        when (level) {
            0 -> {}
            1 -> set.add(BlockTags.NEEDS_STONE_TOOL)
            2 -> set.add(BlockTags.NEEDS_IRON_TOOL)
            3 -> set.add(BlockTags.NEEDS_DIAMOND_TOOL)
        }
        return set.toSet()
    }
}
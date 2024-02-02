package nullversionnova.novaslibrary.interfaces

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

interface Taggable<T> {
    fun getTags() : Set<TagKey<T>> = setOf()
    interface TaggableBlock : Taggable<Block>
    interface TaggableItem : Taggable<Item>
}
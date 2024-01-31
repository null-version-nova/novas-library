package nullversionnova.novaslibrary.registry

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object Registries {
    val BLOCK: ResourceKey<Registry<Block>> =
        Registry.BLOCK_REGISTRY
    val ITEM: ResourceKey<Registry<Item>> =
        Registry.ITEM_REGISTRY
    val ENTITY_TYPE: ResourceKey<Registry<EntityType<*>>> =
        Registry.ENTITY_TYPE_REGISTRY
}
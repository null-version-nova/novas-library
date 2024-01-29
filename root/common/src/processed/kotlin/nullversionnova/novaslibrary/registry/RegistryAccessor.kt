package nullversionnova.novaslibrary.registry

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.core.registries.Registries

object RegistryAccessor {
    val BLOCK: ResourceKey<Registry<Block>> =
        Registries.BLOCK
    val ITEM: ResourceKey<Registry<Item>> =
        Registries.ITEM
    val ENTITY_TYPE: ResourceKey<Registry<EntityType<*>>> =
        Registries.ENTITY_TYPE
}

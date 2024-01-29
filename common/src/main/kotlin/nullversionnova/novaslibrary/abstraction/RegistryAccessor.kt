package nullversionnova.novaslibrary.abstraction

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
//#if MC>11903
import net.minecraft.core.registries.Registries
//#endif

object RegistryAccessor {
    val BLOCK: ResourceKey<Registry<Block>> =
        //#if MC>11903
        Registries.BLOCK
        //#else
        //$Registry.BLOCK_REGISTRY
        //#endif
    val ITEM: ResourceKey<Registry<Item>> =
        //#if MC>11903
        Registries.ITEM
        //#else
        //$Registry.ITEM_REGISTRY
        //#endif
    val ENTITY_TYPE: ResourceKey<Registry<EntityType<*>>> =
        //#if MC>11903
        Registries.ENTITY_TYPE
        //#else
        //$Registry.ENTITY_TYPE_REGISTRY
        //#endif
}
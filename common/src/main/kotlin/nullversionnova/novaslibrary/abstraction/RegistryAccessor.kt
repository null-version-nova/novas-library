package nullversionnova.novaslibrary.abstraction

//#if MC>11903
import net.minecraft.core.registries.Registries
//#else
//$import net.minecraft.core.Registry
//#endif

object RegistryAccessor {
    val BLOCK =
        //#if MC>11903
        Registries.BLOCK
        //#else
        //$Registry.BLOCK_REGISTRY
        //#endif
    val ITEM =
        //#if MC>11903
        Registries.ITEM
        //#else
        //$Registry.ITEM_REGISTRY
        //#endif
    val ENTITY_TYPE =
        //#if MC>11903
        Registries.ENTITY_TYPE
        //#else
        //$Registry.ENTITY_TYPE_REGISTRY
        //#endif
}
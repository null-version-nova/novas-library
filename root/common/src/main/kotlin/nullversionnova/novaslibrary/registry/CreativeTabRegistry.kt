package nullversionnova.novaslibrary.registry

import dev.architectury.registry.CreativeTabRegistry
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import nullversionnova.novaslibrary.util.RegistryDelegate

open class CreativeTabRegistry(id: String) : GenericRegistry<CreativeModeTab>(id,Registries.CREATIVE_MODE_TAB) {
    open fun registerWithIcon(path: String, constructor: () -> ItemLike) : RegistryDelegate<CreativeModeTab> {
        return register(path) {
            CreativeTabRegistry.create(Component.translatable(path)) {
                ItemStack(constructor())
            }
        }
    }
}
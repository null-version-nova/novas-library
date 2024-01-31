package nullversionnova.novaslibrary.registry

import dev.architectury.registry.CreativeTabRegistry
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import nullversionnova.novaslibrary.util.RegistryDelegate

open class CreativeTabRegistry(id: String) : GenericRegistry<CreativeModeTab>(id,Registries.CREATIVE_MODE_TAB) {
    @JvmName("creativeTabRegister")
    fun register(path: String, constructor: () -> ItemLike) : RegistryDelegate<CreativeModeTab> {
        return register<CreativeModeTab>(path) {
            CreativeTabRegistry.create(Component.translatable(path)) {
                ItemStack(constructor())
            }
        }
    }
}

fun CreativeModeTab.append(item: () -> Item) {
    CreativeTabProcessing.addItemToTab(item) { this }
}
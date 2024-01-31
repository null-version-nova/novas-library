package nullversionnova.novaslibrary.registry

import dev.architectury.registry.CreativeTabRegistry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import nullversionnova.novaslibrary.interfaces.RegistryInterface

/**
 * This isn't actually a registry yet. It will be later though.
 */
class CreativeTabRegistry(override val namespace: String) : RegistryInterface<CreativeModeTab> {
    private val tabs = mutableSetOf<CreativeModeTab>()
    /**
     * Not actually necessary in this version, it's just here for compatibility.
     */
    override fun register() {}

    /**
     * Getting it by lazy is not necessary at all, but it'll make the syntax for registering identical to later versions where it uses a RegistryDelegate instead.
     */
    fun register(path : String, constructor : ()->ItemLike) : Lazy<CreativeModeTab> {
        return lazy { CreativeTabRegistry.create(ResourceLocation(namespace,path)) { ItemStack(constructor()) } }
    }

    /**
     * Gets executed immediately.
     */
    override fun whenRegistered(callback: () -> Unit) {
        callback()
    }

    override fun isRegistered(): Boolean = true

    override fun iterator(): Iterator<CreativeModeTab> {
        return tabs.asIterable().iterator()
    }
}
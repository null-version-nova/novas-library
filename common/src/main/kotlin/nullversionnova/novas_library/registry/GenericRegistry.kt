package nullversionnova.novas_library.registry

import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import nullversionnova.novas_library.interfaces.RegistryInterface
import nullversionnova.novas_library.util.RegistryDelegate
import nullversionnova.novas_library.util.RegistryIterator


open class GenericRegistry<T>(id: String,key : ResourceKey<Registry<T>>) : RegistryInterface<T>, Iterable<T> {
    private val registry = DeferredRegister.create(id,key)
    private val callbacks = mutableListOf<()->Unit>()
    private var _registered = false

    // Properties
    override val namespace = id

    // Functions
    override fun register() {
        beginRegister()
        finishRegister()
    }
    override fun <U : T> register(path : String, constructor : ()->U) : RegistryDelegate<U> {
        return RegistryDelegate(registry.register(path) { constructor() })
    }
    override fun whenRegistered(callback : () -> Unit) {
        callbacks.add(callback)
    }

    override fun isRegistered(): Boolean {
        return _registered
    }
    override fun iterator(): Iterator<T> = RegistryIterator<T>(registry.iterator())

    // Utility
    protected fun beginRegister() { // These are here so that derived classes can do stuff between these two functions.
        registry.register()
    }
    protected fun finishRegister() {
        callbacks.forEach { it() }
        _registered = true
    }
}
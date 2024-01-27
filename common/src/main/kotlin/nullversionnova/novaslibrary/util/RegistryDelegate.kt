package nullversionnova.novaslibrary.util

import dev.architectury.registry.registries.RegistrySupplier
import kotlin.reflect.KProperty

class RegistryDelegate<T>(supplier: RegistrySupplier<T>) {
    val instance: T by lazy {
        if (supplier.isPresent) {
            supplier.get()
        } else {
            throw Exception("Accessed an uninitialized registry object at ${supplier.id}")
        }
    }
    operator fun getValue(thisRef: Any?, property: KProperty<*>) : T {
        return instance
    }
}
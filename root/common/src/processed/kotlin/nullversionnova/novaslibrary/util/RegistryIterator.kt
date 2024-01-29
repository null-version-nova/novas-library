package nullversionnova.novaslibrary.util

import dev.architectury.registry.registries.RegistrySupplier

class RegistryIterator<T>(private val iterator : Iterator<RegistrySupplier<T>>) : Iterator<T> {
    override fun hasNext(): Boolean = iterator.hasNext()
    override fun next(): T {
        return iterator.next().get()
    }

}

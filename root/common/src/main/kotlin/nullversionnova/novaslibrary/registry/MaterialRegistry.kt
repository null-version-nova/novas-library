package nullversionnova.novaslibrary.registry

import nullversionnova.novaslibrary.interfaces.Material

abstract class MaterialRegistry : Iterable<Material> {
    private val materials = mutableSetOf<Material>()
    private val callbacks = mutableListOf<()->Unit>()
    private val is_registered = false

    open fun register() {
        forEach { it.register() }
        callbacks.forEach { it() }
    }

    open fun <T : Material> registerMaterial(constructor: () -> T) : T {
        constructor().apply {
            materials.add(this)
            return this
        }
    }

    fun whenRegistered(callback: () -> Unit) {
        callbacks.add(callback)
    }

    fun isRegistered(): Boolean = is_registered

    override fun iterator(): Iterator<Material> = materials.iterator()
}
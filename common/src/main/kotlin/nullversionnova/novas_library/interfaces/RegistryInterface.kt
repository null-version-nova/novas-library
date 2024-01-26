package nullversionnova.novas_library.interfaces

import nullversionnova.novas_library.util.RegistryDelegate

interface RegistryInterface<T> : Iterable<T> {
    val namespace : String
    fun register()
    fun <U : T> register(path: String, constructor: () -> U) : RegistryDelegate<U>
    fun whenRegistered(callback : ()->Unit)
    fun isRegistered() : Boolean
}
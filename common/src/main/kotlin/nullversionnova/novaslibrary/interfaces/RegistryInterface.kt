package nullversionnova.novaslibrary.interfaces

import nullversionnova.novaslibrary.util.RegistryDelegate

interface RegistryInterface<T> : Iterable<T> {
    val namespace : String
    fun register()
    fun <U : T> register(path: String, constructor: () -> U) : RegistryDelegate<U>
    fun whenRegistered(callback : ()->Unit)
    fun isRegistered() : Boolean
}
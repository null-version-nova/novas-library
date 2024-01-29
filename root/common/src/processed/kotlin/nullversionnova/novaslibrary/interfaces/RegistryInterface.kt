package nullversionnova.novaslibrary.interfaces

interface RegistryInterface<T> : Iterable<T> {
    val namespace : String
    fun register()
    fun whenRegistered(callback : ()->Unit)
    fun isRegistered() : Boolean
}

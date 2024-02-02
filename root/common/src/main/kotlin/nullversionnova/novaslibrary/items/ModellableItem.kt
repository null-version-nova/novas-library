package nullversionnova.novaslibrary.items

import net.minecraft.data.models.ItemModelGenerators

interface ModellableItem {
    fun getModel() : (ItemModelGenerators) -> Unit
}
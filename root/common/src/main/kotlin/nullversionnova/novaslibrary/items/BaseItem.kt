package nullversionnova.novaslibrary.items

import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.world.item.Item

class BaseItem(properties: Properties) : Item(properties), ModellableItem {
    override fun getModel(): (ItemModelGenerators) -> Unit = {
        it.generateFlatItem(this, ModelTemplates.FLAT_ITEM)
    }
}
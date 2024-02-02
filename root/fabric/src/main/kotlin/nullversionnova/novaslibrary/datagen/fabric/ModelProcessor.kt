package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators

class ModelProcessor(output: FabricDataOutput?) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators?) {
        blockModels.forEach { it(blockStateModelGenerator!!) }
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerators?) {
        itemModels.forEach { it(itemModelGenerator!!) }
    }
    companion object {
        val blockModels = mutableSetOf<(BlockModelGenerators) -> Unit>()
        val itemModels = mutableSetOf<(ItemModelGenerators) -> Unit>()
    }
}
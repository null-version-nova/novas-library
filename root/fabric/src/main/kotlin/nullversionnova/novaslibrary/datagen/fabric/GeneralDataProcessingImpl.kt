package nullversionnova.novaslibrary.datagen.fabric

import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.interfaces.Taggable
import java.util.function.Consumer

object GeneralDataProcessingImpl {
    @JvmStatic
    fun registerRecipe(closure: (Consumer<FinishedRecipe>)->Unit) {
        RecipeProcessor.recipes.add(closure)
    }
    @JvmStatic
    fun registerBlockModel(model: (BlockModelGenerators) -> Unit) {
        ModelProcessor.blockModels.add(model)
    }
    @JvmStatic
    fun registerItemModel(model: (ItemModelGenerators) -> Unit) {
        ModelProcessor.itemModels.add(model)
    }
    @JvmStatic
    fun registerBlockLootTable(table: (BlockLootSubProvider) -> Unit) {
        LootTableProcessor.lootTables.add(table)
    }
    @JvmStatic
    fun registerTags(taggable: Taggable<*>) {
        when (taggable) {
            is Taggable.TaggableBlock -> if (taggable is Block) BlockTagProcessor.blocks.add(taggable)
            is Taggable.TaggableItem -> if (taggable is Item) ItemTagProcessor.items.add(taggable)
        }
    }
}
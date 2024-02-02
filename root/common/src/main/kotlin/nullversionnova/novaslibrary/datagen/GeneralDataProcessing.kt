package nullversionnova.novaslibrary.datagen

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.recipes.FinishedRecipe
import nullversionnova.novaslibrary.interfaces.Taggable
import java.lang.AssertionError
import java.util.function.Consumer

object GeneralDataProcessing {
    @JvmStatic
    @ExpectPlatform
    fun registerRecipe(consumer: (Consumer<FinishedRecipe>)->Unit) {
        throw AssertionError()
    }
    @JvmStatic
    @ExpectPlatform
    fun registerBlockModel(model: (BlockModelGenerators) -> Unit) {
        println("Model generation is not set up for Forge. Try it on Fabric and copy the files over!")
    }
    @JvmStatic
    @ExpectPlatform
    fun registerItemModel(consumer: (ItemModelGenerators) -> Unit) {
        println("Model generation is not set up for Forge. Try it on Fabric and copy the files over!")
    }
    @JvmStatic
    @ExpectPlatform
    fun registerBlockLootTable(table: (BlockLootSubProvider) -> Unit) {
        println("Loot table generation is not set up for Forge. Try it on Fabric and copy the files over!")
    }
    @ExpectPlatform
    @JvmStatic
    fun registerTags(taggable: Taggable<*>) {
        println("Tag generation is not set up for Forge. Try it on Fabric and copy the files over!")
    }
}
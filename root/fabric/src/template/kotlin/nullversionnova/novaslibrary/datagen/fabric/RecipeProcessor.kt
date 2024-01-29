package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer
//#if MC>=11903
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput

class RecipeProcessor(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun buildRecipes(exporter: Consumer<FinishedRecipe>) {
//#else
//$import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

//$class RecipeProcessor(output: FabricDataGenerator) : FabricRecipeProvider(output) {
//$    override fun generateRecipes(exporter: Consumer<FinishedRecipe>) {
//#endif
        recipes.forEach { it(exporter) }
    }
    companion object {
        val recipes = mutableSetOf<(Consumer<FinishedRecipe>)->Unit>()
    }
}
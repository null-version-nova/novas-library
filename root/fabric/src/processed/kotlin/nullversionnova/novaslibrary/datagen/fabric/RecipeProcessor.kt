package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class RecipeProcessor(output: FabricDataGenerator) : FabricRecipeProvider(output) {
    override fun generateRecipes(exporter: Consumer<FinishedRecipe>) {
        recipes.forEach { it(exporter) }
    }
    companion object {
        val recipes = mutableSetOf<(Consumer<FinishedRecipe>)->Unit>()
    }
}

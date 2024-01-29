package nullversionnova.novaslibrary.datagen.forge

import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import java.util.function.Consumer
class RecipeProcessor(arg: DataGenerator) : RecipeProvider(arg) {
    override fun buildCraftingRecipes(consumer: Consumer<FinishedRecipe>) {
        recipes.forEach { it(consumer) }
    }
    companion object {
        val recipes = mutableSetOf<(Consumer<FinishedRecipe>)->Unit>()
    }
}

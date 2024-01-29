package nullversionnova.novaslibrary.datagen.forge

import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import java.util.function.Consumer

class RecipeProcessor(arg: PackOutput) : RecipeProvider(arg) {
    override fun buildRecipes(consumer: Consumer<FinishedRecipe>) {
        recipes.forEach { it(consumer) }
    }
    companion object {
        val recipes = mutableSetOf<(Consumer<FinishedRecipe>)->Unit>()
    }
}
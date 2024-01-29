package nullversionnova.novaslibrary.datagen.forge

import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import java.util.function.Consumer
//#if MC >= 11903
import net.minecraft.data.PackOutput

class RecipeProcessor(arg: PackOutput) : RecipeProvider(arg) {
    override fun buildRecipes(consumer: Consumer<FinishedRecipe>) {
//#else
//$class RecipeProcessor(arg: DataGenerator) : RecipeProvider(arg) {
//$    override fun buildCraftingRecipes(consumer: Consumer<FinishedRecipe>) {
//#endif
        recipes.forEach { it(consumer) }
    }
    companion object {
        val recipes = mutableSetOf<(Consumer<FinishedRecipe>)->Unit>()
    }
}
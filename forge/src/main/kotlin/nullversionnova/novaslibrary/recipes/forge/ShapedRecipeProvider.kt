package nullversionnova.novaslibrary.recipes.forge

import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.resources.ResourceLocation
import nullversionnova.novaslibrary.recipes.CraftingRecipeBuilder
import java.util.function.Consumer

class ShapedRecipeProvider(arg: PackOutput) : RecipeProvider(arg) {
    override fun buildRecipes(consumer: Consumer<FinishedRecipe>) {
        println("Building recipes")
        recipes.forEach {
            val recipe = ShapedRecipeBuilder(RecipeCategory.MISC,it.output(),it.quantity)
            it.ingredients.forEach { map -> recipe.define(map.key,map.value()) }
            it.shape!!.split('\n').forEach { shape ->
                recipe.pattern(shape)
            }
            recipe.unlockedBy("InventoryChangeTrigger", inventoryTrigger())
            recipe.save(consumer, it.id)
        }
    }
    companion object {
        val recipes = mutableListOf<CraftingRecipeBuilder>()
        fun registerRecipe(recipe: CraftingRecipeBuilder) {
            println("Adding recipe at ${recipe.id.namespace}:${recipe.id.path}")
            recipes.add(recipe)
        }
    }
}
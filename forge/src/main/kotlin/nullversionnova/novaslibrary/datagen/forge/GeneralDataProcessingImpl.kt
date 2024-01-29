package nullversionnova.novaslibrary.datagen.forge

import net.minecraft.data.recipes.FinishedRecipe

object GeneralDataProcessingImpl {
    @JvmStatic
    fun registerRecipe(recipe: FinishedRecipe) {
        RecipeProcessor.recipes.add(recipe)
    }
}
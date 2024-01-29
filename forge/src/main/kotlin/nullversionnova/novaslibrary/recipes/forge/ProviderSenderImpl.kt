package nullversionnova.novaslibrary.recipes.forge

import nullversionnova.novaslibrary.recipes.CraftingRecipeBuilder

object ProviderSenderImpl {
    @JvmStatic
    fun sendShapedRecipe(builder: CraftingRecipeBuilder) {
        ShapedRecipeProvider.registerRecipe(builder)
    }
    @JvmStatic
    fun sendShapelessRecipe(builder: CraftingRecipeBuilder) {
        // @TODO
    }
}
package nullversionnova.novaslibrary.recipes

import dev.architectury.injectables.annotations.ExpectPlatform
import java.lang.AssertionError

object ProviderSender {
    @JvmStatic
    @ExpectPlatform
    fun sendShapedRecipe(builder: CraftingRecipeBuilder) {
        throw AssertionError()
    }
    @JvmStatic
    @ExpectPlatform
    fun sendShapelessRecipe(builder: CraftingRecipeBuilder) {
        throw AssertionError()
    }
}
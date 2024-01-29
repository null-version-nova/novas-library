package nullversionnova.novaslibrary.datagen.forge

import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer

object GeneralDataProcessingImpl {
    @JvmStatic
    fun registerRecipe(closure: (Consumer<FinishedRecipe>)->Unit) {
        RecipeProcessor.recipes.add(closure)
    }
}
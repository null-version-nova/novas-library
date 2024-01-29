package nullversionnova.novaslibrary.datagen

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.data.recipes.FinishedRecipe
import java.lang.AssertionError
import java.util.function.Consumer

object GeneralDataProcessing {
    @JvmStatic
    @ExpectPlatform
    fun registerRecipe(consumer: (Consumer<FinishedRecipe>)->Unit) {
        throw AssertionError()
    }
}

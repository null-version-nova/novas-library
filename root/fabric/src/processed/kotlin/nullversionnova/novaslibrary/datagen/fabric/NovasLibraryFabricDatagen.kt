package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object NovasLibraryFabricDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator?) {
        fabricDataGenerator!!.addProvider {
            RecipeProcessor(fabricDataGenerator)
        }
    }
}

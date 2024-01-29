package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
//#if MC>=11903
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
//#endif

object NovasLibraryFabricDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator?) {
        //#if MC>=11903
        fabricDataGenerator!!.createPack().addProvider { it : FabricDataOutput ->
            RecipeProcessor(it)
        //#else
        //$fabricDataGenerator!!.addProvider {
        //$    RecipeProcessor(fabricDataGenerator)
        //#endif
        }
    }
}
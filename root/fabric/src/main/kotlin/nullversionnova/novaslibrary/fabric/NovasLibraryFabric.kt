package nullversionnova.novaslibrary.fabric

import net.fabricmc.api.ModInitializer
import nullversionnova.novaslibrary.NovasLibrary

object NovasLibraryFabric : ModInitializer {
    override fun onInitialize() {
        NovasLibrary.init()
    }
}
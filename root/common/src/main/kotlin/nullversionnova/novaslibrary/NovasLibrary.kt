package nullversionnova.novaslibrary

import dev.architectury.platform.Platform
import net.minecraft.resources.ResourceLocation
import nullversionnova.novaslibrary.test.TestEntities
import nullversionnova.novaslibrary.test.TestMaterials
import nullversionnova.novaslibrary.test.TestTabs

object NovasLibrary {
    const val MOD_ID = "novas_library"

    fun init() {
        if (Platform.isDevelopmentEnvironment()) {
            TestTabs.register()
            TestEntities.register()
            TestMaterials.register()
        }
    }

    fun makeId(path: String) : ResourceLocation {
        return ResourceLocation(MOD_ID,path)
    }
}

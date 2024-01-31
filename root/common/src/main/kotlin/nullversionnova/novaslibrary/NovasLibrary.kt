package nullversionnova.novaslibrary

import dev.architectury.event.events.common.LifecycleEvent
import dev.architectury.platform.Platform
import net.minecraft.resources.ResourceLocation
import nullversionnova.novaslibrary.test.TestEntities
import nullversionnova.novaslibrary.test.TestMaterials
import nullversionnova.novaslibrary.test.TestTabs
import java.lang.Exception

object NovasLibrary {
    const val MOD_ID = "novas_library"

    fun init() {
        if (Platform.isDevelopmentEnvironment()) {
            try {
                TestEntities.register()
                TestMaterials.register()
                TestTabs.register()
                LifecycleEvent.SETUP.register {
                    TestTabs.TEST_TAB
                    TestMaterials.UNOBTAINUM.INGOT
                }
            } catch (e: Exception) {
                print("Entity loading failed with exception ${e.message}")
            }
        }
    }

    fun makeId(path: String) : ResourceLocation {
        return ResourceLocation(MOD_ID,path)
    }
}

package nullversionnova.novaslibrary

import dev.architectury.platform.Platform
import nullversionnova.novaslibrary.test.TestEntities
import java.lang.Exception

object NovasLibrary {
    const val MOD_ID = "novas_library"

    fun init() {
        if (Platform.isDevelopmentEnvironment()) {
            try {
                TestEntities.register()
            } catch (e: Exception) {
                print("Entity loading failed with exception ${e.message}")
            }
        }
    }
}

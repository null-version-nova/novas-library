package nullversionnova.novaslibrary.test

import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.registry.CreativeTabRegistry

object TestTabs : CreativeTabRegistry(NovasLibrary.MOD_ID) {
    val TEST_TAB by register("test_tab") { TestMaterials.UNOBTAINUM.INGOT }
}
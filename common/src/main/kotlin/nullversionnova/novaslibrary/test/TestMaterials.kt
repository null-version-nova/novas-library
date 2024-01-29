package nullversionnova.novaslibrary.test

import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.materials.RawOre
import nullversionnova.novaslibrary.registry.MaterialRegistry

object TestMaterials : MaterialRegistry() {
    val RAW_UNOBTANIUM = registerMaterial { RawOre(NovasLibrary.MOD_ID,"raw_unobtanium") }
}
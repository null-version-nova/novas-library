package nullversionnova.novaslibrary.test

import net.minecraft.resources.ResourceLocation
import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.materials.Metal
import nullversionnova.novaslibrary.materials.RawOre
import nullversionnova.novaslibrary.registry.MaterialRegistry

object TestMaterials : MaterialRegistry() {
    val RAW_UNOBTANIUM = registerMaterial { RawOre(NovasLibrary.MOD_ID,"raw_unobtanium") }
    val UNOBTAINUM = registerMaterial { Metal(ResourceLocation(NovasLibrary.MOD_ID,"unobtanium")) }
}
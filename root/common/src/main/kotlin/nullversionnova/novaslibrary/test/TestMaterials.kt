package nullversionnova.novaslibrary.test

import net.minecraft.resources.ResourceLocation
import nullversionnova.novaslibrary.NovasLibrary
import nullversionnova.novaslibrary.materials.Metal
import nullversionnova.novaslibrary.materials.RawOre
import nullversionnova.novaslibrary.registry.MaterialRegistry

object TestMaterials : MaterialRegistry() {
    val UNOBTAINUM = registerMaterial { Metal(NovasLibrary.makeId("unobtanium")).withRawOre() }
}
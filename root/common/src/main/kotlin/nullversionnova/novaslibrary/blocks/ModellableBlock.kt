package nullversionnova.novaslibrary.blocks

import net.minecraft.data.models.BlockModelGenerators

interface ModellableBlock {
    fun getModel() : (BlockModelGenerators) -> Unit
}
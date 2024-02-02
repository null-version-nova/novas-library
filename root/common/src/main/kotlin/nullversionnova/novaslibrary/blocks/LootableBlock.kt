package nullversionnova.novaslibrary.blocks

import net.minecraft.data.loot.BlockLootSubProvider

interface LootableBlock {
    fun getLoot() : (BlockLootSubProvider) -> Unit
}
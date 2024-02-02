package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.data.loot.BlockLootSubProvider

class LootTableProcessor(dataOutput: FabricDataOutput?) : FabricBlockLootTableProvider(dataOutput) {
    override fun generate() {
        lootTables.forEach { it(this) }
    }

    companion object {
        val lootTables = mutableListOf<(BlockLootSubProvider) -> Unit>()
    }
}
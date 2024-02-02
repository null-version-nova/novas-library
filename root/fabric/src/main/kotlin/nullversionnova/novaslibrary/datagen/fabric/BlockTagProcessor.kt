package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import nullversionnova.novaslibrary.interfaces.Taggable
import java.util.concurrent.CompletableFuture

class BlockTagProcessor(output: FabricDataOutput?,
                        registriesFuture: CompletableFuture<HolderLookup.Provider>?
) : BlockTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        blocks.forEach { block ->
            if (block is Taggable<*>) {
                block.getTags().forEach { tag ->
                    getOrCreateTagBuilder(tag as TagKey<Block>)
                        .add(block)
                }
            }
        }
    }

    companion object {
        val blocks = mutableListOf<Block>()
    }
}
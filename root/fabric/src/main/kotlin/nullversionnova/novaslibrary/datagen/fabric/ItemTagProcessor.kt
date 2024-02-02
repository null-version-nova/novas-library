package nullversionnova.novaslibrary.datagen.fabric

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import nullversionnova.novaslibrary.interfaces.Taggable
import java.util.concurrent.CompletableFuture

class ItemTagProcessor(output: FabricDataOutput?,
                        registriesFuture: CompletableFuture<HolderLookup.Provider>?
) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        items.forEach { item ->
            if (item is Taggable<*>) {
                item.getTags().forEach { tag ->
                    getOrCreateTagBuilder(tag as TagKey<Item>)
                        .add(item)
                }
            }
        }
    }

    companion object {
        val items = mutableListOf<Item>()
    }
}
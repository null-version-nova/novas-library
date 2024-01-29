package nullversionnova.novaslibrary.materials

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.GenericRegistry
import nullversionnova.novaslibrary.registry.RegistryAccessor


class Metal(val id: ResourceLocation) : Material {
    private val block_registry = BlockRegistry(id.namespace)
    private val item_registry = GenericRegistry(id.namespace, RegistryAccessor.ITEM)

    val INGOT by item_registry.register("${id.path}_ingot") { Item(Item.Properties()) }
    val NUGGET by item_registry.register("${id.path}_nugget") { Item(Item.Properties()) }
    val BLOCK by block_registry.registerWithItem("${id.path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)) }
    var RAW_ORE : RawOre? = null

    fun withRawOre() : Metal {
        RAW_ORE = RawOre(id.namespace,"raw_${id.path}")
        return this
    }

    override fun register() {
        block_registry.register()
        item_registry.register()
        RAW_ORE?.register()

        GeneralDataProcessing.registerRecipe {
            RecipeProvider.threeByThreePacker(it,RecipeCategory.MISC,INGOT,NUGGET)

            RecipeProvider.threeByThreePacker(it,RecipeCategory.MISC,BLOCK,INGOT)

            RecipeProvider.oneToOneConversionRecipe(it,NUGGET,INGOT,null,9)
            RecipeProvider.oneToOneConversionRecipe(it,INGOT,BLOCK,null,9)

            if (RAW_ORE != null) {
                RecipeProvider.oreSmelting(it, listOf(RAW_ORE!!.ORE),RecipeCategory.MISC,INGOT, 1.0F,200,"${id.path}_ingot")
                RecipeProvider.oreBlasting(it, listOf(RAW_ORE!!.ORE),RecipeCategory.MISC,INGOT, 1.0F,100,"${id.path}_ingot")
            }
        }
    }
}

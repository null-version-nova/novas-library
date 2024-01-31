package nullversionnova.novaslibrary.materials

import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.ItemRegistry
import nullversionnova.novaslibrary.test.TestTabs
import nullversionnova.novaslibrary.recipes.ShapelessRecipeBuilder
import nullversionnova.novaslibrary.recipes.ShapedRecipeBuilder
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory

//#endif

class Metal(val id: ResourceLocation) : Material {
    private val blockRegistry = BlockRegistry(id.namespace) { TestTabs.TEST_TAB }
    private val itemRegistry = ItemRegistry(id.namespace) { TestTabs.TEST_TAB }

    val INGOT : Item by itemRegistry.registerWithProperties("${id.path}_ingot")
    val NUGGET by itemRegistry.registerWithProperties("${id.path}_nugget")
    val BLOCK by blockRegistry.registerWithItem("${id.path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)) }
    var RAW_ORE : RawOre? = null

    fun withRawOre() : Metal {
        RAW_ORE = RawOre(id.namespace,"raw_${id.path}")
        return this
    }

    override fun register() {
        blockRegistry.register()
        itemRegistry.register()
        RAW_ORE?.register()

        GeneralDataProcessing.registerRecipe {
            ShapedRecipeBuilder.shaped(INGOT)
                .define('x',NUGGET)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .save(it,ResourceLocation(id.namespace,"${id.path}_nugget_to_ingot"))

            //#if MC>=11903
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,BLOCK)
            //#else
            //$ShapedRecipeBuilder.shaped(BLOCK)
            //#endif
                .define('x',INGOT)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .save(it,ResourceLocation(id.namespace,"${id.path}_ingot_to_block"))

            ShapelessRecipeBuilder.shapeless(NUGGET,9)
                .requires(BLOCK)
                .save(it, ResourceLocation(id.namespace,"${id.path}_block_to_ingot"))

            ShapelessRecipeBuilder.shapeless(INGOT,9)
                .requires(BLOCK)
                .save(it, ResourceLocation(id.namespace,"${id.path}_block_to_ingot"))

            if (RAW_ORE != null) {
                //#if MC>=11903
                RecipeProvider.oreSmelting(it, listOf(RAW_ORE!!.ORE), RecipeCategory.MISC,INGOT, 1.0F,200,"${id.path}_ingot")
                RecipeProvider.oreBlasting(it, listOf(RAW_ORE!!.ORE), RecipeCategory.MISC,INGOT, 1.0F,100,"${id.path}_ingot")
                //#else
                //$RecipeProvider.oreSmelting(it, listOf(RAW_ORE!!.ORE),INGOT,1.0F,200,"${id.path}_ingot")
                //$RecipeProvider.oreBlasting(it, listOf(RAW_ORE!!.ORE),INGOT,1.0F,100,"${id.path}_ingot")
                //#endif
            }
        }
    }
}

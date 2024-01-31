package nullversionnova.novaslibrary.materials

import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.recipes.ShapedRecipeAssembler
import nullversionnova.novaslibrary.recipes.ShapelessRecipeAssembler
import nullversionnova.novaslibrary.registry.BlockRegistry
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory
import nullversionnova.novaslibrary.registry.ItemRegistry
import nullversionnova.novaslibrary.test.TestTabs

//#endif

class Metal(val id: ResourceLocation) : Material {
    private val blockRegistry = BlockRegistry(id.namespace)
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
            ShapedRecipeAssembler(ResourceLocation(id.namespace,"${id.path}_nugget_to_ingot"),INGOT,1)
                .withIngredients('x' to NUGGET)
                .setShape("xxx\nxxxx\nxxx")
                .send(it)

            ShapedRecipeAssembler(ResourceLocation(id.namespace,"${id.path}_ingot_to_block"),BLOCK,1)
                .withIngredients('x' to INGOT)
                .setShape("xxx\nxxxx\nxxx")
                .send(it)

            ShapelessRecipeAssembler(ResourceLocation(id.namespace,"${id.path}_ingot_to_nugget"),NUGGET,9)
                .requires(INGOT)
                .send(it)

            ShapelessRecipeAssembler(ResourceLocation(id.namespace,"${id.path}_block_to_ingot"),INGOT,9)
                .requires(BLOCK)
                .send(it)

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

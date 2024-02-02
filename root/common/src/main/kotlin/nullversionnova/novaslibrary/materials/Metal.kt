package nullversionnova.novaslibrary.materials

//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory
//#endif
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.ItemRegistry
import nullversionnova.novaslibrary.recipes.ShapelessRecipeBuilder
import nullversionnova.novaslibrary.recipes.ShapedRecipeBuilder
import nullversionnova.novaslibrary.blocks.BaseMineableBlock

open class Metal(val id: ResourceLocation, val level: Int = 1) : Material {
    private val blockRegistry = BlockRegistry(id.namespace)
    private val itemRegistry = ItemRegistry(id.namespace)

    val INGOT : Item by itemRegistry.registerWithProperties("${id.path}_ingot")
    val NUGGET by itemRegistry.registerWithProperties("${id.path}_nugget")
    val BLOCK by blockRegistry.registerWithItem("${id.path}_block") { BaseMineableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK),level) }
    var RAW_ORE : RawOre? = null

    fun withRawOre() : Metal {
        RAW_ORE = RawOre(ResourceLocation(id.namespace,"raw_${id.path}"),level,this)
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
                .unlockedBy("Inventory Change",RecipeProvider.inventoryTrigger())
                .save(it,ResourceLocation(id.namespace,"${id.path}_ingot_to_block"))

            ShapelessRecipeBuilder.shapeless(NUGGET,9)
                .requires(INGOT)
                .unlockedBy("Inventory Change",RecipeProvider.inventoryTrigger())
                .save(it, ResourceLocation(id.namespace,"${id.path}_ingot_to_nugget"))

            ShapelessRecipeBuilder.shapeless(INGOT,9)
                .requires(BLOCK)
                .unlockedBy("Inventory Change",RecipeProvider.inventoryTrigger())
                .save(it, ResourceLocation(id.namespace,"${id.path}_block_to_ingot"))
        }
    }
}

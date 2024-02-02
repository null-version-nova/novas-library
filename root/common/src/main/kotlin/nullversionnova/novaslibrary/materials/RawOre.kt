package nullversionnova.novaslibrary.materials

//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory
//#endif
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.recipes.ShapedRecipeBuilder
import nullversionnova.novaslibrary.recipes.ShapelessRecipeBuilder
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.ItemRegistry
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.level.ItemLike
import nullversionnova.novaslibrary.blocks.BaseMineableBlock


open class RawOre(val id: ResourceLocation, val level: Int = 1) : Material {
    // Registries
    private val blocks = BlockRegistry(id.namespace)
    private val items = ItemRegistry(id.namespace)

    // Objects
    val ORE : Item by items.registerWithProperties(id.path)
    val BLOCK by blocks.registerWithItem("${id.path}_block") { BaseMineableBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK),level) }

    constructor(id: ResourceLocation, level: Int, smeltsInto: () -> ItemLike) : this(id,level) {
        GeneralDataProcessing.registerRecipe {
            //#if MC>=11903
            RecipeProvider.oreBlasting(it, listOf(ORE),RecipeCategory.MISC,smeltsInto(),1.0f,100, BuiltInRegistries.ITEM.getKey(smeltsInto().asItem()).path)
            RecipeProvider.oreSmelting(it, listOf(ORE),RecipeCategory.MISC,smeltsInto(),1.0f,200, BuiltInRegistries.ITEM.getKey(smeltsInto().asItem()).path)
            //#else
            //$RecipeProvider.oreBlasting(it, listOf(ORE),smeltsInto(),1.0f,100, BuiltInRegistries.ITEM.getKey(smeltsInto().asItem()).path)
            //$RecipeProvider.oreSmelting(it, listOf(ORE),smeltsInto(),1.0f,200, BuiltInRegistries.ITEM.getKey(smeltsInto().asItem()).path)
            //#endif
        }
    }
    constructor(id: ResourceLocation, level: Int, smeltsInto: Metal) : this(id,level) {
        GeneralDataProcessing.registerRecipe {
            //#if MC>=11903
            RecipeProvider.oreSmelting(it, listOf(ORE), RecipeCategory.MISC,smeltsInto.INGOT, 1.0F,200,"${smeltsInto.id.path}_ingot")
            RecipeProvider.oreBlasting(it, listOf(ORE), RecipeCategory.MISC,smeltsInto.INGOT, 1.0F,100,"${smeltsInto.id.path}_ingot")
            //#else
            //$RecipeProvider.oreSmelting(it, listOf(RAW_ORE!!.ORE),INGOT,1.0F,200,"${id.path}_ingot")
            //$RecipeProvider.oreBlasting(it, listOf(RAW_ORE!!.ORE),INGOT,1.0F,100,"${id.path}_ingot")
            //#endif
        }
    }

    override fun register() {
        blocks.register()
        items.register()

        GeneralDataProcessing.registerRecipe {
            //#if MC>=11903
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,BLOCK)
            //#else
            //$ShapedRecipeBuilder.shaped(BLOCK)
            //#endif
                .define('x',ORE)
                .pattern("xxx")
                .pattern("xxx")
                .pattern("xxx")
                .unlockedBy("Inventory Change", RecipeProvider.inventoryTrigger())
                .save(it,ResourceLocation(id.namespace,"${id.path}_to_${id.path}_block"))

            ShapelessRecipeBuilder.shapeless(ORE,9)
                .requires(BLOCK)
                .unlockedBy("Inventory Change",RecipeProvider.inventoryTrigger())
                .save(it,ResourceLocation(id.namespace,"${id.path}_block_to_${id.path}"))
        }
    }
}
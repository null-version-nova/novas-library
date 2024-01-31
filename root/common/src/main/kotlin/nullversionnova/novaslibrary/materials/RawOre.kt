package nullversionnova.novaslibrary.materials

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.recipes.ShapedRecipeBuilder
import nullversionnova.novaslibrary.recipes.ShapelessRecipeBuilder
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.ItemRegistry
import nullversionnova.novaslibrary.test.TestTabs
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory

//#endif

class RawOre(val id: ResourceLocation) : Material {
    constructor(namespace: String, path: String) : this(ResourceLocation(namespace,path))
    // Registries
    private val block_registry = BlockRegistry(id.namespace) { TestTabs.TEST_TAB }
    private val item_registry = ItemRegistry(id.namespace) { TestTabs.TEST_TAB }

    // Objects
    val ORE : Item by item_registry.registerWithProperties(id.path)
    val BLOCK by block_registry.registerWithItem("${id.path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)) }

    override fun register() {
        block_registry.register()
        item_registry.register()

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
                .save(it,ResourceLocation(id.namespace,"${id.path}_to_${id.path}_block"))

            ShapelessRecipeBuilder.shapeless(ORE,9)
                .requires(BLOCK)
                .save(it,ResourceLocation(id.namespace,"${id.path}_block_to_${id.path}"))
        }
    }
}
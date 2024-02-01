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
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory

//#endif

open class RawOre(val id: ResourceLocation) : Material {
    constructor(namespace: String, path: String) : this(ResourceLocation(namespace,path))
    // Registries
    private val blocks = BlockRegistry(id.namespace)
    private val items = ItemRegistry(id.namespace)

    // Objects
    val ORE : Item by items.registerWithProperties(id.path)
    val BLOCK by blocks.registerWithItem("${id.path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)) }

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
                .save(it,ResourceLocation(id.namespace,"${id.path}_to_${id.path}_block"))

            ShapelessRecipeBuilder.shapeless(ORE,9)
                .requires(BLOCK)
                .save(it,ResourceLocation(id.namespace,"${id.path}_block_to_${id.path}"))
        }
    }
}
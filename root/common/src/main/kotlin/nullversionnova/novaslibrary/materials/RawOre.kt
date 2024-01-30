package nullversionnova.novaslibrary.materials

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.datagen.GeneralDataProcessing
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.recipes.RecipeCategories
import nullversionnova.novaslibrary.recipes.ShapedRecipeAssembler
import nullversionnova.novaslibrary.recipes.ShapelessRecipeAssembler
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.GenericRegistry
import nullversionnova.novaslibrary.registry.RegistryAccessor

class RawOre(val id: ResourceLocation) : Material {
    constructor(namespace: String, path: String) : this(ResourceLocation(namespace,path))
    // Registries
    private val block_registry = BlockRegistry(id.namespace)
    private val item_registry = GenericRegistry(id.namespace, RegistryAccessor.ITEM)

    // Objects
    val ORE : Item by item_registry.register(id.path) { Item(Item.Properties()) }
    val BLOCK by block_registry.registerWithItem("${id.path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)) }

    override fun register() {
        block_registry.register()
        item_registry.register()

        GeneralDataProcessing.registerRecipe {
            ShapedRecipeAssembler(RecipeCategories.BUILDING_BLOCKS,id.namespace,"${id.path}_to_${id.path}_block",BLOCK)
                .withIngredients( 'x' to ORE )
                .setShape("xxx\nxxx\nxxx")
                .send(it)
            ShapelessRecipeAssembler(RecipeCategories.MISC,ResourceLocation(id.namespace,"${id.path}_block_to_${id.path}"),ORE,9)
                .requires(BLOCK)
                .send(it)
        }
    }
}
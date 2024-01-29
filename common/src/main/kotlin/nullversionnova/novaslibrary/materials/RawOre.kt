package nullversionnova.novaslibrary.materials

import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import nullversionnova.novaslibrary.abstraction.RegistryAccessor
import nullversionnova.novaslibrary.interfaces.Material
import nullversionnova.novaslibrary.recipes.CraftingRecipeBuilder
import nullversionnova.novaslibrary.registry.BlockRegistry
import nullversionnova.novaslibrary.registry.GenericRegistry

class RawOre(val namespace: String, val path: String) : Material {
    // Registries
    private val block_registry = BlockRegistry(namespace)
    private val item_registry = GenericRegistry(namespace,RegistryAccessor.ITEM)

    // Objects
    val ORE : Item by item_registry.register(path) { Item(Item.Properties()) }
    val BLOCK by block_registry.registerWithItem("${path}_block") { Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)) }

    override fun register() {
        block_registry.register()
        item_registry.register()

        CraftingRecipeBuilder(namespace,path,{BLOCK}).withIngredients('x' to {ORE}).setShape("xxx\nxxx\nxxx").build()
        CraftingRecipeBuilder(namespace,path,{ORE},9).withIngredients('x' to {BLOCK}).setList("x").build()
    }
}
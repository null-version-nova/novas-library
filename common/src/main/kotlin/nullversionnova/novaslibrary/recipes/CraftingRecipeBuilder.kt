package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider.inventoryTrigger
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.ItemLike
import java.util.function.Consumer
//#if MC>=11903
import net.minecraft.data.recipes.RecipeCategory
//#else
//$import net.minecraft.client.RecipeBookCategories
//#endif

class CraftingRecipeBuilder(val id: ResourceLocation, val output: ItemLike, val quantity: Int = 1) {
    constructor(namespace: String, path: String, output: ItemLike, quantity: Int = 1) : this(ResourceLocation(namespace,path),output,quantity)
    lateinit var ingredients : Map<Char,ItemLike>
    var has_shape : Boolean? = null
    var shape : String? = null
    var category = RecipeCategories.MISC
    fun withIngredients(vararg input: Pair<Char,ItemLike>) : CraftingRecipeBuilder {
        ingredients = mapOf(*input)
        return this
    }
    fun setShape(input: String) : CraftingRecipeBuilder {
        has_shape = true
        shape = input
        return this
    }
    fun setList(input: String) : CraftingRecipeBuilder {
        has_shape = false
        shape = input
        return this
    }
    //#if MC>=11903
    fun setCategory(input: RecipeCategory) : CraftingRecipeBuilder {
    //#else
    //$fun setCategory(input: RecipeBookCategories) : CraftingRecipeBuilder {
    //#endif
        category = input
        return this
    }
    fun send(closure: Consumer<FinishedRecipe>) {
        when(has_shape) {
            null -> return
            true -> {
                //#if MC>=11903
                val recipe = ShapedRecipeBuilder(RecipeCategories.MISC,output,quantity)
                //#else
                //$val recipe = ShapedRecipeBuilder(output,quantity)
                //#endif
                ingredients.forEach { map -> recipe.define(map.key,map.value) }
                shape!!.split('\n').forEach { shape ->
                    recipe.pattern(shape)
                }
                recipe.unlockedBy("InventoryChangeTrigger", inventoryTrigger())
                recipe.save(closure,id)
            }
            false -> {
                //#if MC>=11903
                val recipe = ShapelessRecipeBuilder(RecipeCategories.MISC,output,quantity)
                //#else
                //$val recipe = ShapelessRecipeBuilder(output,quantity)
                //#endif
                ingredients.keys.forEach { key ->
                    (0 until (shape!!.count {char -> char == key })).forEach { _ ->
                        recipe.requires(ingredients[key]!!)
                    }
                }
                recipe.unlockedBy("InventoryChangeTrigger", inventoryTrigger())
                recipe.save(closure,id)
            }
        }
    }
}
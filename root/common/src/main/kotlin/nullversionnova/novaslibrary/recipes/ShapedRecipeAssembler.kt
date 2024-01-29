package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider.inventoryTrigger
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.ItemLike
import java.util.function.Consumer

class ShapedRecipeAssembler(val category: RecipeCategory,val id: ResourceLocation, val output: ItemLike, val quantity: Int = 1) {
    constructor(category: RecipeCategory,namespace: String, path: String, output: ItemLike, quantity: Int = 1) : this(category,ResourceLocation(namespace,path),output,quantity)
    constructor(namespace: String,path: String,output: ItemLike,quantity: Int = 1) : this(RecipeCategories.MISC,namespace, path, output, quantity)
    constructor(id: ResourceLocation,output: ItemLike,quantity: Int = 1) : this(RecipeCategories.MISC,id, output, quantity)

    private lateinit var ingredients : Map<Char,ItemLike>
    var shape : String? = null
    fun withIngredients(vararg input: Pair<Char,ItemLike>) : ShapedRecipeAssembler {
        ingredients = mapOf(*input)
        return this
    }
    fun setShape(input: String) : ShapedRecipeAssembler {
        shape = input
        return this
    }
    fun send(closure: Consumer<FinishedRecipe>) {
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
}
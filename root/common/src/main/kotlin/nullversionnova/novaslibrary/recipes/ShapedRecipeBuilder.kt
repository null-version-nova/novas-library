package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.level.ItemLike
//#if MC >= 11903
import net.minecraft.data.recipes.RecipeCategory
//#endif

object ShapedRecipeBuilder {
    fun shaped(item: ItemLike, quantity: Int = 1) : ShapedRecipeBuilder {
        //#if MC>=11903
        return shaped(RecipeCategory.MISC,item,quantity)
        //#else
        //$return ShapedRecipeBuilder.shaped(item,quantity)
        //#endif
    }
    //#if MC>=11903
    fun shaped(category: RecipeCategory, item: ItemLike, quantity: Int = 1) : ShapedRecipeBuilder {
        return ShapedRecipeBuilder.shaped(category,item,quantity)
    }
    //#endif
}
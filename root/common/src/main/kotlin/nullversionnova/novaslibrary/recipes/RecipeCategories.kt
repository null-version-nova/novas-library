package nullversionnova.novaslibrary.recipes

import net.minecraft.data.recipes.RecipeCategory

object RecipeCategories {
    val MISC =
        //#if MC>=11903
        RecipeCategory.MISC
        //#else
        //$RecipeBookCategories.CRAFTING_MISC
        //#endif
}
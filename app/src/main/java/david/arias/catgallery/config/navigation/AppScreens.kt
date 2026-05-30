package david.arias.catgallery.config.navigation

sealed class AppScreens(val route: String) {
    data object BreedSelection : AppScreens("breed-selection")
    data object CatGallery : AppScreens("cat-gallery")
}
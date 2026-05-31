package david.arias.catgallery.infraestructure.repositories

import david.arias.catgallery.domain.entities.Breed
import david.arias.catgallery.domain.entities.CatImage
import david.arias.catgallery.domain.repositories.CatRepository
import david.arias.catgallery.infraestructure.mappers.CatMapper
import david.arias.catgallery.infraestructure.remote.apis.CatApiService
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val api: CatApiService
) : CatRepository {

    override suspend fun getBreeds(): List<Breed> {
        return api.getBreeds()
            .map(CatMapper::toBreed)
    }

    override suspend fun getImagesByBreed(
        breedId: String,
        limit: Int
    ): List<CatImage> {

        return api.getImagesByBreed(
            breedId = breedId,
            limit = limit
        ).take(limit).map(CatMapper::toCatImage)
    }
}
package david.arias.catgallery.infraestructure.mappers

import david.arias.catgallery.domain.entities.Breed
import david.arias.catgallery.domain.entities.CatImage
import david.arias.catgallery.infraestructure.remote.entities.BreedResponse
import david.arias.catgallery.infraestructure.remote.entities.CatImageResponse

object CatMapper {

    fun toBreed(
        response: BreedResponse
    ): Breed {
        return Breed(
            id = response.id,
            name = response.name
        )
    }

    fun toBreeds(
        responses: List<BreedResponse>
    ): List<Breed> {
        return responses.map(::toBreed)
    }

    fun toCatImage(
        response: CatImageResponse
    ): CatImage {
        return CatImage(
            id = response.id,
            url = response.url
        )
    }

    fun toCatImages(
        responses: List<CatImageResponse>
    ): List<CatImage> {
        return responses.map(::toCatImage)
    }
}
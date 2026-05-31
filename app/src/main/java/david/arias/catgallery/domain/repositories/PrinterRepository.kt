package david.arias.catgallery.domain.repositories

import david.arias.catgallery.domain.entities.CatImage

interface PrinterRepository {
    suspend fun printCat(cat: CatImage) : Result<Unit>
}
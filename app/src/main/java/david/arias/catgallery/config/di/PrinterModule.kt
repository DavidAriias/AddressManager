package david.arias.catgallery.config.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import david.arias.catgallery.domain.repositories.PrinterRepository
import david.arias.catgallery.infraestructure.repositories.UrovoPrinterImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrinterModule {

    @Binds
    @Singleton
    abstract fun bindPrinterRepository(
        impl: UrovoPrinterImpl
    ): PrinterRepository
}
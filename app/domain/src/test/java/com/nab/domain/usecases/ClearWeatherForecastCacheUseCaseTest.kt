package com.nab.domain.usecases

import com.nab.data.repositories.LocalForecastRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClearWeatherForecastCacheUseCaseTest {

    @Mock
    private lateinit var localRepository : LocalForecastRepository

    private lateinit var clearWeatherForecastCacheUseCase: ClearWeatherForecastCacheUseCase

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(ClearWeatherForecastCacheUseCaseTest::class)
        clearWeatherForecastCacheUseCase = ClearWeatherForecastCacheUseCaseImpl(localRepository)
    }

    @Test
    fun `verify repository will call to clear weather forecast cache`(){
        runBlocking {
            clearWeatherForecastCacheUseCase.clearAllWeatherForecastCaches()
            verify(localRepository).clearAllWeatherForecastCaches()
        }
    }

}
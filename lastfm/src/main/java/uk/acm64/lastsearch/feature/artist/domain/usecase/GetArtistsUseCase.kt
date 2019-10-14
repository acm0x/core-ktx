package uk.acm64.lastsearch.feature.artist.domain.usecase

import uk.acm64.core.Either
import uk.acm64.core.Failure
import uk.acm64.core.UseCase
import uk.acm64.lastsearch.feature.artist.domain.model.Artist
import uk.acm64.lastsearch.feature.artist.domain.repository.LastFmRepository
import javax.inject.Inject

class GetArtistsUseCase @Inject constructor(private val lastFmRepository: LastFmRepository) :
    UseCase<List<Artist>, GetArtistsUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<Artist>> {
        return try {
            val results = lastFmRepository.getArtists(params.query)
            Either.Right(results)
        } catch (exp: Exception) {
            Either.Left(GetArtistsFailure)
        }
    }

    data class Params(val query: String)
    object GetArtistsFailure : Failure.FeatureFailure()
}
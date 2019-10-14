package uk.acm64.lastsearch.feature.artist.domain.usecase

import uk.acm64.core.Either
import uk.acm64.core.Failure
import uk.acm64.core.UseCase
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo
import uk.acm64.lastsearch.feature.artist.domain.repository.LastFmRepository
import javax.inject.Inject

class GetArtistInfoUseCase @Inject constructor(private val lastFmRepository: LastFmRepository) :
    UseCase<ArtistInfo, GetArtistInfoUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, ArtistInfo> {
        return try {
            lastFmRepository.getArtistInfo(params.mbId).let {
                if(it != null) {
                    Either.Right(it)
                } else {
                    Either.Left(GetArtistInfoNxFailure)
                }
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
            Either.Left(GetArtistInfoFailure)
        }
    }

    data class Params(val mbId: String)
    object GetArtistInfoFailure : Failure.FeatureFailure()
    object GetArtistInfoNxFailure : Failure.FeatureFailure()
}
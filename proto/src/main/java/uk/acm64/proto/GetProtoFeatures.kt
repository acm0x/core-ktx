package uk.acm64.proto

import uk.acm64.core.Either
import uk.acm64.core.Failure
import uk.acm64.core.UseCase
import uk.acm64.core.UseCase.None
import uk.acm64.proto.listdetail.ListDetailsActivity
import javax.inject.Inject


class GetProtoFeatures @Inject constructor() : UseCase<List<ProtoFeature>, None>() {
    override suspend fun run(params: None): Either<Failure, List<ProtoFeature>> =
            Either.Right(listOf(ListDetailsActivity::class).map { ProtoFeature(it)})
}

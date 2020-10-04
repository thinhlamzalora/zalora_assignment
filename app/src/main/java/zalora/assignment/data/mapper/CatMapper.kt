package zalora.assignment.data.mapper

import zalora.assignment.data.model.CatRemote
import zalora.assignment.domain.model.Cat

object CatMapper {
    fun toDomain(catRemote: CatRemote): Cat {
        return Cat(
            id = catRemote.id,
            image = catRemote.url,
            heightRatio = catRemote.height.toDouble()/catRemote.width
        )
    }
}
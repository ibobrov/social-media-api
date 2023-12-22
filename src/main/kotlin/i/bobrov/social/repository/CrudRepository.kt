package i.bobrov.social.repository

import org.springframework.stereotype.Repository

interface CrudRepository<T> : BaseRepository<T> {
    fun findAll(): List<T>
}

@Repository
interface BaseRepository<T> {
    fun add(elm: T): Int

    fun update(id: Int, elm: T): Boolean

    fun delete(id: Int): Boolean

    fun findById(id: Int): T?
}

interface PaginationCrudRepository<T> : BaseRepository<T> {
    fun findAll(pageIndex: Int): List<T>
}

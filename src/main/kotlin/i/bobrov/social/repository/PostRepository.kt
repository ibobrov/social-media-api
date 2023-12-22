package i.bobrov.social.repository

import i.bobrov.social.model.Post

interface PostRepository : PaginationCrudRepository<Post>

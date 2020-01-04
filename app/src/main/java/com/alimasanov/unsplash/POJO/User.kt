package com.alimasanov.unsplash.POJO

data class User(
    var bio: String? = null,
    var id: String? = null,
    var location: String? = null,
    var name: String? = null,
    var portfolio_url: String? = null,
    var total_collections: Int? = null,
    var total_likes: Int? = null,
    var total_photos: Int? = null,
    var updated_at: String? = null,
    var username: String? = null,
    var links: UserLinks? = null
)
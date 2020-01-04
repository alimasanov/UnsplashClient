package com.alimasanov.unsplash.POJO

data class Photo() {
    var id: String? = null
    var created_at: String? = null
    var updated_at: String? = null
    var width: Int? = null
    var height: Int? = null
    var color: String? = null
    var downloads: Int? = null
    var likes: Int? = null
    var liked_by_user: Boolean? = null
    var description: String? = null

}
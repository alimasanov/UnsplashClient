package com.alimasanov.unsplash.server.pojo

data class Exif(
    val aperture: String? = null,
    val exposure_time: String? = null,
    val focal_length: String? = null,
    val iso: Int? = null,
    val make: String? = null,
    val model: String? = null
)
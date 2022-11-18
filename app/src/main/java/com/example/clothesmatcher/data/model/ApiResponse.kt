package com.example.clothesmatcher.data.model

data class ApiResponse(
    val is_json: Boolean?,
    val img_value_type: String?,
    val img_value: String?
)
data class ImageResponse(
    val returned_image: String?
)

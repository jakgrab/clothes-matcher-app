package com.example.clothesmatcher.data.model


data class ApiResponse(
    val images: ArrayList<ReturnedImage>
)
data class ReturnedImage(
    val returned_image: String?
)

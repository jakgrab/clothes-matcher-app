package com.example.clothesmatcher.data.model


data class ApiResponse(
    val hello: String,
    val images: ArrayList<ReturnedImage>
)
data class ReturnedImage(
    val image: String?
)

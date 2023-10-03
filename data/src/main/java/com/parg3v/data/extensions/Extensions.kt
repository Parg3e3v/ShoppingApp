package com.parg3v.data.extensions

import com.parg3v.data.model.ProductModel
import com.parg3v.domain.model.Product
import com.parg3v.domain.model.Rating

fun ProductModel.toProduct(): Product{
    return Product(id, title, price, description, category, image, Rating(rating.rate, rating.count))
}
package com.example.easyshop.view.selected_category_products

import androidx.lifecycle.ViewModel
import com.example.easyshop.repository.ProductsRepository

class SelectedProductsViewModel :ViewModel(){
    var categoryWiseProducts=ProductsRepository.selectedCategoryProducts

}
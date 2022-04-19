package com.example.knowledgehunt.domain.utils

import com.example.knowledgehunt.domain.models.MCQItemData


// This is NOT the best way to pass args when navigating from composable to another
// HOWEVER due to the limitation of the recommended way i was forced to do this may god forgive me
class MCQTestArguments private constructor() {
    var itemData: MCQItemData? = null

    fun destroy() {
        itemData = null
        instance = null
    }

    companion object {
        var instance: MCQTestArguments? = null
            get() {
                if (field == null) {
                    field = MCQTestArguments()
                }
                return field
            }
            private set
    }
}
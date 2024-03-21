//package com.example.easyshop.composables
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.autofill.AutofillType
//
//@Composable
//fun AutofillField (){
//
//}
//fun Modifier.autofill(
//    autofillTypes: List<AutofillType>,
//    onFill: ((String) -> Unit),
//) = composed {
//    val autofill = LocalAutofill.current
//    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = autofillTypes)
//    LocalAutofillTree.current += autofillNode
//
//    this.onGloballyPositioned {
//        autofillNode.boundingBox = it.boundsInWindow()
//    }.onFocusChanged { focusState ->
//        autofill?.run {
//            if (focusState.isFocused) {
//                requestAutofillForNode(autofillNode)
//            } else {
//                cancelAutofillForNode(autofillNode)
//            }
//        }
//    }
//}

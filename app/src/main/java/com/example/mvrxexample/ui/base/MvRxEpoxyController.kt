package com.example.mvrxexample.ui.base

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState

open class MvRxEpoxyController(
    val buildModelsCallback: EpoxyController.() -> Unit = {}
) : AsyncEpoxyController() {

    override fun buildModels() {
        buildModelsCallback()
    }
}

fun BaseEpoxyFragment.simpleController(
    buildModels: EpoxyController.() -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    buildModels()
}

fun <S : MvRxState, A : BaseViewModel<S>> BaseEpoxyFragment.simpleController(
    viewModel: A,
    buildModels: EpoxyController.(state: S) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    com.airbnb.mvrx.withState(viewModel) { state ->
        buildModels(state)
    }
}

fun <A : BaseMvRxViewModel<B>, B : MvRxState, C : BaseMvRxViewModel<D>, D : MvRxState> BaseEpoxyFragment.simpleController(
    viewModel1: A,
    viewModel2: C,
    buildModels: EpoxyController.(state1: B, state2: D) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    com.airbnb.mvrx.withState(viewModel1, viewModel2) { state1, state2 ->
        buildModels(state1, state2)
    }
}

fun <A : BaseMvRxViewModel<B>, B : MvRxState, C : BaseMvRxViewModel<D>, D : MvRxState, E : BaseMvRxViewModel<F>, F : MvRxState> BaseEpoxyFragment.simpleController(
    viewModel1: A,
    viewModel2: C,
    viewModel3: E,
    buildModels: EpoxyController.(state1: B, state2: D, state3: F) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    com.airbnb.mvrx.withState(viewModel1, viewModel2, viewModel3) { state1, state2, state3 ->
        buildModels(state1, state2, state3)
    }
}
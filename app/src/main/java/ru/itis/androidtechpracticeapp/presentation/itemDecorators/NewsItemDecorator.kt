package ru.itis.androidtechpracticeapp.presentation.itemDecorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NewsItemDecorator(
    private val halfSpace: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.paddingLeft != halfSpace) {
            parent.setPadding(0, halfSpace, 0, halfSpace)
            parent.clipToPadding = false
        }

        outRect.top = halfSpace
        outRect.right = halfSpace
        outRect.left = halfSpace
        outRect.bottom = halfSpace
    }
}
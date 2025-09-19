package com.naulian.composable.scc.shapes

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class VerticalTicketShape(
    private val cornerRadius: Dp = 16.dp,
    private val cutoutRadius: Dp = 16.dp,
    /**
     * Fraction of the height where the cutout center will be placed.
     * Example: 0.5f = middle, 0.25f = upper quarter.
     */
    private val cutoutHeightFraction: Float = 0.80f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        with(density) {
            val cornerRadiusPx = cornerRadius.toPx()
            val cutoutRadiusPx = cutoutRadius.toPx()

            // Base ticket rectangle with rounded corners
            val ticketPath = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(0f, 0f, size.width, size.height),
                        cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
                    )
                )
            }

            // Calculate vertical center for cutouts
            val cutoutCenterY = size.height * cutoutHeightFraction

            // Cutout on the left edge
            val leftCutout = Path().apply {
                addOval(
                    Rect(
                        left = -cutoutRadiusPx,
                        top = cutoutCenterY - cutoutRadiusPx,
                        right = cutoutRadiusPx,
                        bottom = cutoutCenterY + cutoutRadiusPx
                    )
                )
            }

            // Cutout on the right edge
            val rightCutout = Path().apply {
                addOval(
                    Rect(
                        left = size.width - cutoutRadiusPx,
                        top = cutoutCenterY - cutoutRadiusPx,
                        right = size.width + cutoutRadiusPx,
                        bottom = cutoutCenterY + cutoutRadiusPx
                    )
                )
            }

            // Subtract cutouts from base shape
            val finalPath = Path().apply {
                op(ticketPath, leftCutout, PathOperation.Difference)
                op(this, rightCutout, PathOperation.Difference)
            }

            return Outline.Generic(finalPath)
        }
    }
}
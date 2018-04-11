package live.senya.garrely.ui.common

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareLayout : FrameLayout {
    
    constructor(@NonNull context: Context) : super(context) 

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(context, attrs, 0)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}
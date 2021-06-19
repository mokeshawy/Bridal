package custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class bridbutton(context: Context, attr : AttributeSet): AppCompatEditText(context , attr) {
    init {
        applyFont()
    }
    fun applyFont (){
        val typeface : Typeface =
            Typeface.createFromAsset(context.assets , "CelistyneDemoRegular.ttf")
        setTypeface(typeface) }

}



package akshay.kumar.srijannet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        var textview=findViewById(R.id.textview)as TextView

    }

}
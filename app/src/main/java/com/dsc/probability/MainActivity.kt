import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dsc.probability.R
import com.dsc.probability.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{
            calculateProb() }
    }
    fun calculateProb(){
        val stringInTextField = binding.yearYouLive.text.toString()
        val liveYear = stringInTextField.toDouble()
        val selectedId = binding.peopleOptions.checkedRadioButtonId
        val meetPeople = when (selectedId) {
            R.id.option_two_hundred -> 200
            R.id.option_four_hundred -> 400
            R.id.option_six_hundred -> 600
            R.id.option_eight_hundred -> 800
            else -> 1000}
        val worldPop = binding.worldPopulation.text.toString().toDoubleOrNull()
        if(worldPop==null || liveYear==null){return}
        val prob:Double = ((liveYear*365)*meetPeople) / worldPop
        val magicSwitch = binding.roundUpSwitch.isChecked
        if(magicSwitch){
            val probResult = BigDecimal(prob).setScale(6, BigDecimal.ROUND_HALF_UP)
            Log.d("Prob","Round Up:${probResult.toString()}")
            binding.probResult.text = probResult.toString()
        }else{
            Log.d("Prob","Normal:${prob.toString()}")
            binding.probResult.text = prob.toString()
        }


    }

}


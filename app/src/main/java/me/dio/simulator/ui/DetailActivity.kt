package me.dio.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import me.dio.simulator.databinding.ActivityDetailBinding
import me.dio.simulator.databinding.ActivityMainBinding
import me.dio.simulator.domain.Match

class DetailActivity : AppCompatActivity() {

    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            supportActionBar?.title = it.place.name
            binding.tvDescription.text = it.description

            binding.tvHomeTeamName.text = it.homeTeam.name
            Glide.with(this).load(it.homeTeam.image).circleCrop().into(binding.ivHomeTeam)
            binding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()
            if (it.homeTeam.score != null) {
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }

            binding.tvAwayTeamName.text = it.homeAway.name
            Glide.with(this).load(it.homeAway.image).circleCrop().into(binding.ivAwayTeam)
            binding.rbAwayTeamStars.rating = it.homeAway.stars.toFloat()
            if (it.homeAway.score != null) {
                binding.tvAwayTeamScore.text = it.homeAway.score.toString()
            }
        }
    }
}
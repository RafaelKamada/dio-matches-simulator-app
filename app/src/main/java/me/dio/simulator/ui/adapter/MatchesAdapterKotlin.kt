package me.dio.simulator.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import me.dio.simulator.databinding.MatchItemBinding
import me.dio.simulator.domain.Match
import me.dio.simulator.ui.DetailActivity

class MatchesAdapterKotlin(
    private val matches: List<Match>
) : RecyclerView.Adapter<MatchesAdapterKotlin.ViewHolder>() {

    fun getMatches(): List<Match> = matches


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding: MatchItemBinding = MatchItemBinding.inflate(layoutInflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var context: Context  = viewHolder.itemView.context
        var match = matches[position]

        //Adapta os dados da partida (recuparada da API) para nosso layout
        Glide.with(context).load(match.homeTeam.image).circleCrop().into(viewHolder.binding.ivHomeTeam)
        viewHolder.binding.tvHomeTeamName.text = match.homeTeam.name
        if (match.homeTeam.score != null) {
            viewHolder.binding.tvHomeTeamScore.text = match.homeTeam.score.toString();
        }

        Glide.with(context).load(match.homeAway.image).circleCrop().into(viewHolder.binding.ivAwayTeam)
        viewHolder.binding.tvAwayTeamName.text = match.homeAway.name
        if (match.homeAway.score != null) {
            viewHolder.binding.tvAwayTeamScore.text = match.homeAway.score.toString();
        }

        viewHolder.itemView.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.Extras.MATCH, match)
            context.startActivity(intent)
        })
    }

    override fun getItemCount() = matches.size

    class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root)
}

package com.omarhawari.starwarstrivia.presentation.character_detail

import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omarhawari.starwarstrivia.R
import com.omarhawari.starwarstrivia.common.components.starOffsets
import com.omarhawari.starwarstrivia.databinding.ActivityCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModels()

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)
        binding.viewModel = viewModel
        binding.executePendingBindings()


        setSupportActionBar(binding.appBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.surface.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                val canvas: Canvas = holder.lockCanvas()
                val paint
                        : Paint

                val nightModeFlags: Int = resources.configuration.uiMode and
                        Configuration.UI_MODE_NIGHT_MASK
                when (nightModeFlags) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        paint = Paint()
                        paint.style = Paint.Style.FILL
                        paint.color =
                            resources.getColor(R.color.black)

                        canvas.drawPaint(paint)
                        paint.style = Paint.Style.STROKE
                        paint.color =
                            resources.getColor(R.color.white)

                    }
                    else -> {
                        paint = Paint()
                        paint.style = Paint.Style.FILL
                        paint.color =
                            resources.getColor(R.color.white)

                        canvas.drawPaint(paint)

                        paint.style = Paint.Style.STROKE
                        paint.color =
                            resources.getColor(R.color.black)

                    }
                }

                starOffsets.forEach {

                    val left = it.first.value * 3
                    val top = it.second.value * 3
                    canvas.drawOval(
                        left,
                        top,
                        left + 3,
                        top + 3,
                        paint
                    )
                }
                holder.unlockCanvasAndPost(canvas)

            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {

            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }


}
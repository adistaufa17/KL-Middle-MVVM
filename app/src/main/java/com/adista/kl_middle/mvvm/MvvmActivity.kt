package com.adista.kl_middle.mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adista.kl_middle.databinding.ActivityMvvmBinding
import kotlinx.coroutines.launch

class MvvmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmBinding

    private lateinit var viewModel: MvvmViewModel

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MvvmViewModel.Factory)[MvvmViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.counter.collect {
                        counter = it
                        binding.tvCount.text = it.toString()
                    }
                }
            }
        }

        binding.tvCount.text = counter.toString()

        binding.btnMin.setOnClickListener {
            lifecycleScope.launch {
                viewModel.decrease(counter)
            }
        }

        binding.btnPlus.setOnClickListener {
            lifecycleScope.launch {
                viewModel.increase(counter)
            }
        }


    }
}


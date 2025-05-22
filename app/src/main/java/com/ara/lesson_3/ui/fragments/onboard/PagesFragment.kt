package com.ara.lesson_3.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ara.lesson_3.R
import com.ara.lesson_3.databinding.FragmentPagesBinding

class PagesFragment : Fragment() {
    private lateinit var binding: FragmentPagesBinding
    companion object{
        const val ARG_ONBOARD_POSITION = "onBoardPos"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                title.text = "Удобство"
                paragraph.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                animationLotti.setAnimation(R.raw.animation_2)
            }
            1 -> {
                title.text = "Организация"
                paragraph.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                animationLotti.setAnimation(R.raw.animation_1)
            }
            2 -> {
                title.text = "Синхронизация"
                paragraph.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                animationLotti.setAnimation(R.raw.animation_3)
            }
        }
    }
}
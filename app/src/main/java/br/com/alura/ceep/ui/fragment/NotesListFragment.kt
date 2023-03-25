package br.com.alura.ceep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.alura.ceep.databinding.FragmentNotesListBinding
import br.com.alura.ceep.ui.recyclerview.adapter.NotesListAdapter
import br.com.alura.ceep.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesListFragment : Fragment() {

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels() // para injetar com hilt ao inves de usar @Inject. É assim que se injeta viewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentNotesListBinding.inflate(
        inflater,
        container,
        false
    ).let {
        _binding = it
        it.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        configureAdapter()
    }

    private fun configureAdapter() {
        NotesListAdapter(requireContext()).let { adapter ->
            binding.fragmentNotesListRecyclerview.adapter = adapter
            lifecycleScope.launchWhenResumed {

                val data = viewModel.findAll()
                adapter.update(data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
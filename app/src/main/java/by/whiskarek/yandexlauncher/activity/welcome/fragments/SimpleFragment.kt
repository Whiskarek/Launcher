package by.whiskarek.yandexlauncher.activity.welcome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SimpleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = arguments!!.getInt(ARG_LAYOUT_ID)
        return inflater.inflate(layoutId, container, false)
    }

    companion object {
        private const val ARG_LAYOUT_ID = "LAYOUT_ID"

        fun instance(layoutId: Int): SimpleFragment {
            val fragment = SimpleFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_ID, layoutId)
            fragment.arguments = args
            return fragment
        }
    }
}

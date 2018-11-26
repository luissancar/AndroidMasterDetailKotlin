package com.example.luissancar.androidmasterdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.luissancar.androidmasterdetail.R.id.container
import com.example.luissancar.androidmasterdetail.amplificador.Amplificador
import com.example.luissancar.androidmasterdetail.amplificador.AmplificadorContenido
import com.example.luissancar.androidmasterdetail.dummy.DummyContent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_amplificador_detail.*
import kotlinx.android.synthetic.main.activity_amplificador_detail.view.*
import kotlinx.android.synthetic.main.amplificador_detail.*
import kotlinx.android.synthetic.main.amplificador_detail.view.*
import kotlinx.android.synthetic.main.amplificador_list_content.*

/**
 * A fragment representing a single amplificador detail screen.
 * This fragment is either contained in a [amplificadorListActivity]
 * in two-pane mode (on tablets) or a [amplificadorDetailActivity]
 * on handsets.
 */
class amplificadorDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
   // private var mItem: DummyContent.DummyItem? = null
    private var mItem: Amplificador? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var amp = AmplificadorContenido()
        amp.loadAmplificadorList()   // cargamos lista amplificadores
        if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
           // mItem = DummyContent.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]

           // mItem = amp.getIdAmplificador(arguments.getString(ARG_ITEM_ID))
            activity.imageViewGeneral.loadUrl("http://vinculando.org/wp-content/uploads/imagenes-con-energia-positiva.jpg")
            print(arguments.getString(ARG_ITEM_ID))
            mItem = amp.getIdAmplificador(arguments.getString(ARG_ITEM_ID))    // cargamos el amplificador seleccionado
            mItem?.let {
                activity.toolbar_layout?.title = "uno"+it.urlImagen
                //activity.imageView.loadUrl("https://git-scm.com/figures/18333fig0330-tn.png")
               // activity.imageView2.loadUrl(it.urlImagen)


            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.amplificador_detail, container, false)

        // Show the dummy content as text in a TextView.

        // cargamos datos detail

        mItem?.let {
            rootView.amplificador_detail.text = it.urlImagen
           // rootView.imageView.loadUrl("https://git-scm.com/figures/18333fig0330-tn.png")
            rootView.imageView2.loadUrl(it.urlImagen)
        }

        return rootView
    }
    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}

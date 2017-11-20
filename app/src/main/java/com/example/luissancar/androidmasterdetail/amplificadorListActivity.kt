package com.example.luissancar.androidmasterdetail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.luissancar.androidmasterdetail.amplificador.Amplificador
import com.example.luissancar.androidmasterdetail.amplificador.AmplificadorContenido

import com.example.luissancar.androidmasterdetail.dummy.DummyContent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_amplificador_list.*
import kotlinx.android.synthetic.main.amplificador_list_content.view.*

import kotlinx.android.synthetic.main.amplificador_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [amplificadorDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class amplificadorListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    var ampliss=AmplificadorContenido() ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ampliss.loadAmplificadorList()
        setContentView(R.layout.activity_amplificador_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (amplificador_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        setupRecyclerView(amplificador_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, ampliss.getAmplificadorList(), mTwoPane) //mm
    }

    class SimpleItemRecyclerViewAdapter(private val mParentActivity: amplificadorListActivity,
                                        private val mValues: List< Amplificador>,
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
              //  val item = v.tag as DummyContent.DummyItem
                val item = v.tag as Amplificador
                if (mTwoPane) {
                    val fragment = amplificadorDetailFragment().apply {
                        arguments = Bundle()
                        println(item.id)
                        arguments.putString(amplificadorDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.amplificador_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, amplificadorDetailActivity::class.java).apply {
                        putExtra(amplificadorDetailFragment.ARG_ITEM_ID, item.id)






                    }

                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.amplificador_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mIdView.text = item.marca                    //
            holder.mContentView.text = item.modelo              //
            holder.miImage.loadUrl(item.urlImagen)



            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.id_text
            val mContentView: TextView = mView.content
            val miImage: ImageView = mView.imageView3
        }
    }
}

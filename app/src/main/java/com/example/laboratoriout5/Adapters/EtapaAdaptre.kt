package com.example.laboratoriout5.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.laboratoriout5.R
import com.example.laboratoriout5.models.Etapa

class EtapaAdaptre (
    private val context: Context,
    private val listaEtapas:List<Etapa>

) : BaseAdapter(){

    override fun getCount(): Int {
        return listaEtapas.size

    }

    override fun getItem(position: Int): Any? {
        return listaEtapas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        val holder:ViewHolder

        if(convertView==null)
        {
        view = LayoutInflater.from(context).inflate(R.layout.item_etapa,parent,false)
        holder = ViewHolder()
        holder.ImgTerreno=view.findViewById(R.id.imgTerreno)
        holder.txtNombre = view.findViewById(R.id.txtNombre)
        holder.txtPeligrosidad = view.findViewById(R.id.txtPeligrosidad)
        view.tag = holder
        }else{
          view=convertView
          holder=view.tag as ViewHolder
        }
        val etapa = listaEtapas[position]
        holder.txtNombre?.text = etapa.nombre
        holder.txtPeligrosidad?.text = "Peligrosidad: ${etapa.peligrosidad}"
        holder.ImgTerreno?.setImageResource(etapa.imagenResId)

        return view
    }

    private class ViewHolder{
        var ImgTerreno: ImageView?=null
        var txtNombre: TextView?=null
        var txtPeligrosidad: TextView?=null
    }

}
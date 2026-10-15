package com.example.laboratoriout5

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.laboratoriout5.Adapters.EtapaAdaptre
import com.example.laboratoriout5.models.Etapa

class MainActivity : AppCompatActivity() {
    private lateinit var listaEtapas: List<Etapa>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        listaEtapas=listOf(
            Etapa("Cataratas de Rauros", "Baja", android.R.drawable.ic_menu_gallery),
            Etapa("Bordes de Fangorn", "Media", android.R.drawable.ic_menu_gallery),
            Etapa("Ciénaga de los Muertos", "Alta", android.R.drawable.ic_menu_gallery),
            Etapa("La Puerta Negra", "Extrema", android.R.drawable.ic_menu_gallery)
        )

        val lvEtapas = findViewById<ListView>(R.id.lvEtapas)
        val adaptador= EtapaAdaptre(this,listaEtapas)
        lvEtapas.adapter=adaptador

        registerForContextMenu(lvEtapas)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_superior_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_abandonar->{
                Toast.makeText(this, "Saliendo de la ruta de forma segura", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Acciones de Exploraciones")
        menuInflater.inflate(R.menu.menu_contextual_personaje,menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info =item.menuInfo as? AdapterView.AdapterContextMenuInfo

        return when (item.itemId){
            R.id.ctx_inspeccionar->{
                if(info!=null){
                    val etapaSelecionada=listaEtapas[info.position]
                    val intentDetalle= Intent(this, DetalleActivity::class.java).apply {
                        putExtra("NOMBRE_ETAPA", etapaSelecionada.nombre)
                        putExtra("PELIGROSIDAD_ETAPA", etapaSelecionada.peligrosidad)

                    }
                    startActivity(intentDetalle)
                }
                true
            }
            else-> super.onContextItemSelected(item)
        }
    }

}
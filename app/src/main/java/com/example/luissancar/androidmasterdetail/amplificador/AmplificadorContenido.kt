package com.example.luissancar.androidmasterdetail.amplificador

/**
 * Created by luissancar on 03/11/2017.
 */
class AmplificadorContenido {


    private  val amplificadorList: MutableList<Amplificador> = ArrayList()


    public  fun getAmplificadorList(): MutableList<Amplificador> {
        return amplificadorList
    }
    public fun getIdAmplificador(id:Int): Amplificador{
        for (obbj in amplificadorList) {
            if (obbj.id == id) {
                return obbj
            }
        }
        return amplificadorList.get(0)
    }

    public fun loadAmplificadorList() {
        var ampli = Amplificador(1,"Fender","Bassman","http://media.musiciansfriend.com/is/image/MMGS7/Vintage-Reissue-59-Bassman-LTD-4X10-Guitar-Combo/480726000000000-00-500x500.jpg")
        amplificadorList.add(ampli)
        ampli = Amplificador(2,"Marshall","JCM 800","http://www.emmett.cl/admin/cargadedatos/imgproductos/20140726-940185-JCM_800.jpg")
        amplificadorList.add(ampli)
        ampli = Amplificador(3,"Vox","AC30","http://cdn.tonegeek.com/wp-content/uploads/P1010658.jpg")
        amplificadorList.add(ampli)
    }
}
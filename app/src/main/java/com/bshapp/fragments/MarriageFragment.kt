package com.bshapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.bshapp.R
import com.bshapp.adapter.MatrimonyRecyclerAdapter
import com.bshapp.classes.PrefConfig
import com.bshapp.classes.URLs
import com.bshapp.model.MatrimonyNewsModel
import org.json.JSONArray
import org.w3c.dom.Text
import java.util.ArrayList


class MarriageFragment : Fragment() {
    private lateinit var list: ArrayList<MatrimonyNewsModel>
    lateinit var model: MatrimonyNewsModel
    private lateinit var matrimonyRecycler: RecyclerView
    lateinit var adapter: MatrimonyRecyclerAdapter
    private lateinit var nodata:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_marriage, container, false)
        setupSlider(view)
        getdata()
        list = ArrayList<MatrimonyNewsModel>()
        matrimonyRecycler = view.findViewById<RecyclerView>(R.id.jobrecycler)
        nodata = view.findViewById<TextView>(R.id.nodata)
        matrimonyRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = MatrimonyRecyclerAdapter(list, requireContext())
        matrimonyRecycler.adapter = adapter
        return view
    }
    private fun getdata() {
        val jobparam:String = "?s=VerifiedMatrimony"
        val queue = Volley.newRequestQueue(requireContext())
        val selectedState: String? = context?.let { PrefConfig.loadSelectedState(it) }
        var url: String = URLs.DATA_URL+jobparam
        if(selectedState != null){
            url = URLs.DATA_URL + jobparam+"&state="+selectedState
        }
        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
                { response ->
                    try {
//                    val respObj = JSONObject(response)
//                    val success = respObj.getInt("status")
//                    val jsonArray = respObj.getJSONArray("data")
                        val jsonArray= JSONArray(response)


                        for (i in 0 until jsonArray.length()) {
                            Log.d("Hello", "hello")
                            val item = jsonArray.getJSONObject(i)
                            val name = item.getString("name")
                            val gender = item.getString("gender")
                            val dateofbirth = item.getString("dob")
                            val height = item.getString("height")
                            val state = item.getString("state")
                            val city = item.getString("city")
                            val caste = item.getString("caste")
                            val education = item.getString("education")
                            val phone = item.getString("mobileNumber")
                            val imagename = item.getString("imageName")



                            model = MatrimonyNewsModel(
                                    id,
                                    name,
                                    gender,
                                    dateofbirth,
                                    height,
                                    caste,
                                    city,
                                    state,
                                    education,
                                    phone,
                                    imagename
                            )
                            list.add(model)
                        }
                        Log.d("Hello", list.size.toString())
                        adapter.setData(list)
                        if(list.size == 0){
                            nodata.visibility = View.VISIBLE
                        } else {
                            nodata.visibility = View.GONE
                        }


                    } catch (e: Exception) {
                        Log.d("Job", e.toString())
                    }

                },
                {
                    Log.d("API", "that didn't work")
                })
        queue.add(stringReq)
    }
    private fun setupSlider(view: View) {
        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(
                SlideModel(
                        R.drawable.sliderimage
                )
        )
        imageList.add(
                SlideModel(
                        R.drawable.sliderimage1
                )
        )
        imageList.add(SlideModel(R.drawable.sliderimage2))
        imageList.add(SlideModel(R.drawable.sliderimage3))
        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
    }

}
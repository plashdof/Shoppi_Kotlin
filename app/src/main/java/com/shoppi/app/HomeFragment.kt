package com.shoppi.app

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject
import org.w3c.dom.Text

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_product_detail)
        }

        val assetLoader = AssetLoader()
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")

        var iconUrl = ""
        var text = ""

        if(!homeData.isNullOrEmpty()){
            val jsonObject = JSONObject(homeData)
            val title = jsonObject.getJSONObject("title")
            text = title.getString("text")
            iconUrl = title.getString("icon_url")
            val titleValue = Title(text, iconUrl)
        }

        val textview = getView()?.findViewById<TextView>(R.id.home_toolbar_text)
        textview?.setText(text)

        val imgview = getView()?.findViewById<ImageView>(R.id.home_toolbar_image)

        Glide.with(this).load(iconUrl).into(imgview)

    }



}
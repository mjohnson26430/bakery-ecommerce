package com.example.gundamgalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.gundamgalaxy.adapters.GundamAdapter;
import com.example.gundamgalaxy.models.GundamModel;

import java.util.ArrayList;
import java.util.List;


public class GundamRv extends AppCompatActivity implements OnItemClickListener{

    RecyclerView recyclerView;
    com.example.gundamgalaxy.adapters.GundamAdapter GundamAdapter;
    RecyclerView.LayoutManager layoutManager;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gundam_rv);
        id=getIntent().getStringExtra("id");
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<GundamModel> gundamModelList = getData(id);
        GundamAdapter = new GundamAdapter(this, gundamModelList, this);
        recyclerView.setAdapter(GundamAdapter);
    }

    private List<GundamModel> getData(String id) {
        List<GundamModel> gundamModelList = new ArrayList<>();
        if ("anime".equals(id)) {
            gundamModelList.add(new GundamModel("Mobile Suit Gundam 00", "2007", "The series is set on a futuristic Earth and is centered on the exploits of the fictional paramilitary organization Celestial Being and its efforts to rid the world of war and conflict with a series of unique and extremely advanced mecha mobile suits known as \"Gundams\"", 15.99, R.drawable.ani1));
            gundamModelList.add(new GundamModel("Gundam Build Divers", "2018", "Three middle-school students join an online game that lets them battle robots in a virtual world, where they meet a mysterious girl named Sarah.", 20.25, R.drawable.ani2));
            gundamModelList.add(new GundamModel("Iron-Blooded Orphans", "2015", "Roughly 300 years after the conclusion of a great conflict known as the Calamity War, the Earth Sphere has lost its previous governing structure, and a new system of government has created a new world.", 32.59, R.drawable.ibo));
            gundamModelList.add(new GundamModel("Mobile Suit Gundam Wing", "1995", "Five powerful weapons known as Gundams are sent to Earth to destroy OZ and free the Space Colonies.", 17.50, R.drawable.ani3));
            gundamModelList.add(new GundamModel("The Witch from Mercury", "2022", "A compassionate girl enrolls herself in the Asticassia School of Technology in Planet Mercury that is run by the Beneritt Group that creates robotic mobile suits.", 38.20, R.drawable.ani4));
            gundamModelList.add(new GundamModel("Gunbuster", "1988", "The daughter of a famous dead space captain is enlisted to pilot a colossal robot key to the defence of Earth from invasion by giant aliens.", 45.75, R.drawable.ani5));
        } else if ("kits".equals(id)) {
            gundamModelList.add(new GundamModel("1/100 MGEX Strike Freedom Gundam (Gundam Seed Destiny)","2022","MGEX, the high-end brand renowned for its extreme expression in bringing MS Gundam to life, expands its line with the new Strike Freedom Gundam.",112.44,R.drawable.sfg));
            gundamModelList.add(new GundamModel("RX-78-2 Gundam", "2020", "OverviewCelebrating the 40th anniversary of GunPla, is the arrival of the HG Gundam RX-78-02 (The Origin Ver.)! Featuring the latest injection molding techniques, the Gundam contains swappable parts to create either the Early or Mid timeline version of the RX-78-02 as it makes its journey during the One Year War.", 31.50, R.drawable.kits));
            gundamModelList.add(new GundamModel("MGSD Freedom Gundam (Gundam Seed)","2023","Bandai's taken the incredible plamo technology of the Master Grade (MG) series and condensed it into the body of an SD Gundam!",31.32,R.drawable.zgmf));
            gundamModelList.add(new GundamModel("1/100 MG Mobile Ginn","2021","Cosmic Era's mass-produced Mobile Ginn from \"Mobile Suit Gundam Seed' is now available as an MG model kit from Bandai! It features the MG series' internal frame configuration, and the mechanical design of its characteristic backpack is impossible to miss.",34.54,R.drawable.ginn));
            gundamModelList.add(new GundamModel("1/60 PG Unicorn Gundam","2014","Piloted by Banagher Links, the PG Unicorn is the first Perfect Grade Bandai kit in four years, and the most impressive to date.",152.64,R.drawable.unicorn));
            gundamModelList.add(new GundamModel("1/60 PG Gundam Astray Red Frame","2009","The highly anticipated Perfect Grade Gundam Astray Red Frame has finally arrived! After a long hiatus without any brand-new entries in the Perfect Grade line since Perfect Grade Strike Gundam in 2004, Gundam modelers around the world now get to build a spectacular 1/60-scale kit of the popular mecha piloted by Lowe Guele in \"Mobile Suit Gundam Seed Astray\"!",144.61,R.drawable.astray));
        } else if ("games".equals(id)) {
            gundamModelList.add(new GundamModel("Gundam Evolution", "2020", "Gundam Evolution was a 2022 online first-person team shooter developed by Bandai Namco Online and published by Bandai Namco Entertainment.", 0.00, R.drawable.games));
            gundamModelList.add(new GundamModel("SD Gundam Battle Alliance", "2022", "SD GUNDAM BATTLE ALLIANCE is an action RPG that allows players to battle Super Deformed (SD) versions of Mobile Suits from Gundam series. The story takes place in G: Universe, a world where Gundam canon twists and turns in ways no one can predict.", 59.99, R.drawable.alliance));
            gundamModelList.add(new GundamModel("SD GUNDAM G GENERATION CROSS RAYS", "2019", "Beyond creation, four eras come together as one! Discover the latest entry in this iconic Tactical RPG series. Form your own team of SD Gundams and deploy for battle -- with units from Mobile Suit Gundam Wing, SEED, 00, and Iron-Blooded Orphans!", 39.99, R.drawable.crossrays));
            gundamModelList.add(new GundamModel("Gundam Battle Universe", "2008", "Gundam Battle Universe is a Mech Sim game, developed by Artdink and published by Bandai Namco Games, which was released in Asia in 2009.", 41.01, R.drawable.buniverse));
            gundamModelList.add(new GundamModel("New Gundam Breaker", "2018", "Collect Gunpla parts with friends, and make the ultimate Gundam! Defeat your enemies with your Gunpla model, collect their parts, and use them to make and battle with your own custom Gundam in this all-new Gunpla action game!", 49.99, R.drawable.gbreak));
            gundamModelList.add(new GundamModel("Dynasty Warriors: Gundam 3", "2007", "Dynasty Warriors: Gundam 3 for PlayStation 3 combines the hack and slash Dynasty Warriors gameplay with mobile suits and characters from the Gundam universe.", 86.99, R.drawable.reborn));
        } else if ("wearables".equals(id)) {
            gundamModelList.add(new GundamModel("Gundam RX-78-2 Helmet 3D Model", "2021", "These 3D models have been test printed before uploading in order to ensure 100% quality guarantee.", 30.00, R.drawable.helmet));
            gundamModelList.add(new GundamModel("THE FULL SUIT RX-78-2 GUNDAM COSPLAY SUIT MORE THAN 8 FEET TALL", "2021", "The suit is completely painted, and it includes all the decals. height: 8’8‘’. The gun is not included( we can not ship it)the shield is optional by purchasing Gundam Basic, you will get the full cosplay suit, the bodysuit, and a voice changer ( same as the bumblebee)", 2849, R.drawable.gunsuit1));
            gundamModelList.add(new GundamModel("Mobile Suit Gundam Zaku II Anime TV Series Japanese Men's Short Sleeve T Shirt Graphic Tees", "2023", "ANIME LEGEND! Classic Mobile Suit Gundam apparel. Comfortable and cool tees celebrating the legendary anime series.", 22.00, R.drawable.tees));
            gundamModelList.add(new GundamModel("HUF x Mobile Suite Gundam Endless Waltz Split Fleece Hoodie - Heather Grey", "2023", "100% Cotton, Lace Up closure, Machine Wash", 97.94, R.drawable.hoodie));
            gundamModelList.add(new GundamModel("Gundam Athletic Crew Socks", "2020", "CUSTOM DESIGN - The custom artwork featuRed on these Gundam crew socks make them one-of-a-kind collectibles.", 13.95, R.drawable.socks));
            gundamModelList.add(new GundamModel("Mobile Suit Gundam RX-78-2 Lanyard ID Badge Holder", "2020", "THIS LANYARD IS AN OFFICIALLY LICENSED GUNDAM PRODUCT -: These lanyards are designed by Bioworld, one of the leading pop culture apparel, headwear, and accessories distributors in the world just like this authentic Gundam product!", 9.95, R.drawable.holder));
        }
        return gundamModelList;
    }

    @Override
    public void onItemClicked(int position) {
        GundamModel gundamModel = GundamAdapter.getItem(position);
            Intent intent = new Intent(GundamRv.this, AnimeDetailsActivity.class);
            intent.putExtra("anime_name", gundamModel.getName());
            intent.putExtra("release_year", gundamModel.getReleaseYear());
            intent.putExtra("synopsis", gundamModel.getSynopsis());
            intent.putExtra("image", gundamModel.getImage());
            intent.putExtra("animePrice", gundamModel.getPrice());
            startActivity(intent);
        }
    }



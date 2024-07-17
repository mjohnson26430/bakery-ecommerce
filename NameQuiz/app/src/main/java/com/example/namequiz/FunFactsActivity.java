package com.example.namequiz;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.namequiz.Activities.MainMenu;

public class FunFactsActivity extends AppCompatActivity {

    private TextView countryTextView, factTextView;
    private Button nextButton;
    private DatabaseHelper dbHelper;
    private Cursor cursor;
    private int countryColumnIndex, factColumnIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fun_facts);

        ImageButton setButtonImage = findViewById(R.id.close_button);
        countryTextView = findViewById(R.id.countryTextView);
        factTextView = findViewById(R.id.factTextView);
        nextButton = findViewById(R.id.nextButton);

        setButtonImage.setOnClickListener(v -> {
            Intent intent = new Intent(FunFactsActivity.this, MainMenu.class);
            startActivity(intent);
        });

        dbHelper = new DatabaseHelper(this);
        cursor = dbHelper.getFacts();

        if (cursor != null && cursor.moveToFirst()) {
            countryColumnIndex = cursor.getColumnIndex("country");
            factColumnIndex = cursor.getColumnIndex("fact");
            updateUI(cursor.getString(countryColumnIndex), cursor.getString(factColumnIndex));
        } else {
            updateUI("No country", "No fact available");
        }

        nextButton.setOnClickListener(v -> {
            if (cursor != null && cursor.moveToNext()) {
                updateUI(cursor.getString(countryColumnIndex), cursor.getString(factColumnIndex));
            } else if (cursor != null) {
                cursor.moveToFirst();
                updateUI(cursor.getString(countryColumnIndex), cursor.getString(factColumnIndex));
            }
        });
    }

    private void updateUI(String country, String fact) {
        countryTextView.setText(!TextUtils.isEmpty(country) ? country : "No country");
        factTextView.setText(!TextUtils.isEmpty(fact) ? fact : "No fact available");
        animateTextChange(countryTextView);
        animateTextChange(factTextView);
    }

    private void animateTextChange(View view) {
        view.setAlpha(0f);
        view.animate().alpha(1f).setDuration(500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) {
            cursor.close();
        }
        dbHelper.close();
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "funfacts.db";
        private static final int DATABASE_VERSION = 2; // Ensure this is the latest version
        private static final String TABLE_NAME = "facts";
        private static final String COLUMN_COUNTRY = "country";
        private static final String COLUMN_FACT = "fact";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_COUNTRY + " TEXT, " +
                    COLUMN_FACT + " TEXT)";
            db.execSQL(createTable);
            populateDatabaseWithInitialData(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            }
        }

        private void populateDatabaseWithInitialData(SQLiteDatabase db) {
            if (isTableEmpty(db)) {
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Australia', 'Australia is home to 21 of the worlds 25 most venomous snakes.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Turkey', 'The ancient underground city of Derinkuyu in Turkey was capable of housing up to 20,000 people and was used as a refuge during times of war and religious persecution. Its an eerie labyrinth of tunnels and rooms deep below the surface.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Mexico', 'La Isla de las Muñecas (Island of the Dolls) is located in the canals of Xochimilco. The island is covered with decaying dolls hanging from trees and buildings, placed there by the islands former caretaker to appease the spirit of a drowned girl.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('India', 'Bhangarh Fort in Rajasthan is considered one of the most haunted places in India. According to legend, the entire town was cursed by a wizard, leading to its abandonment.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Bolivia', 'The North Yungas Road, also known as \"Death Road,\" is considered one of the most dangerous roads in the world. The narrow, winding path has steep cliffs and is often shrouded in fog.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Iceland', 'Icelanders have a deep-rooted belief in the \"Huldufólk\" or \"hidden people,\" which are supernatural beings similar to elves. Many road construction projects have been altered or halted to avoid disturbing their supposed habitats.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Jamaica', 'Rose Hall is a famous plantation house in Jamaica, known for the legend of the White Witch, Annie Palmer. According to the tale, she was a cruel mistress who practiced voodoo and killed several husbands and slaves before being murdered herself. Her ghost is said to haunt the estate.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Switzerland', 'CERNs Large Hadron Collider is the worlds largest and most powerful particle accelerator. Its located near Geneva and has sparked numerous conspiracy theories about its potential to create black holes or other catastrophic events.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('United States', 'In the 1800s, the practice of \"grave robbing\" for medical dissection was so rampant that cemeteries had to build \"mortsafes,\" iron cages placed over graves to deter body snatchers.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Germany', 'During the 16th and 17th centuries, Germany experienced the \"Dancing Plague,\" a phenomenon where people danced uncontrollably for days or weeks, sometimes leading to death from exhaustion or heart attacks.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Japan', 'The Aokigahara Forest, also known as the \"Suicide Forest,\" at the base of Mount Fuji, is notorious for the high number of suicides that occur there. The dense forest is eerily quiet, with signs urging visitors to reconsider their actions and seek help.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Romania', 'Transylvania, known for its association with the Dracula legend, is home to Hoia Forest, considered one of the most haunted forests in the world. It is known for its paranormal activity, UFO sightings, and eerie atmosphere.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Italy', 'Poveglia Island, located between Venice and Lido, was used as a quarantine station for plague victims and later as an insane asylum. It is now abandoned and is considered one of the most haunted places in the world.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('South Africa', 'The Cango Caves in the Western Cape are a series of limestone chambers with dark legends of explorers getting lost and never being found. The deepest parts are not accessible to the public due to safety concerns.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Indonesia', 'The Kawah Ijen volcano in East Java is famous for its electric blue flames, caused by the combustion of sulfuric gases. The site is both beautiful and dangerous, with toxic fumes that pose a threat to visitors.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Vietnam', 'The Sơn Đoòng Cave, the largest cave in the world, is so vast that it has its own river, jungle, and climate. It was only discovered in the early 1990s and remains largely unexplored.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Kazakhstan', 'The Aral Sea, once one of the worlds largest lakes, has largely dried up due to Soviet irrigation projects. The desolate landscape is now littered with the rusting hulks of abandoned ships.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Iran', 'The Lut Desert, known as Dasht-e Lut, is one of the hottest places on Earth. In 2005, NASA measured ground temperatures there at a staggering 159.3°F (70.7°C), making it one of the most extreme environments on the planet.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('China', 'The Terracotta Army, a collection of life-sized sculptures depicting the armies of the first Emperor of China, Qin Shi Huang, was buried with the emperor in 210–209 BC. The figures were meant to protect him in the afterlife.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Egypt', 'In Cairo, there is a large necropolis called the \"City of the Dead\" where thousands of people live among the tombs and mausoleums. This unique community has existed for centuries, blending life with the resting places of the deceased.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('United Kingdom', 'In 1858, the River Thames in London became so polluted with sewage that the stench became unbearable, leading to the closure of the House of Commons. This event prompted the construction of London’s modern sewer system.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Thailand', 'During World War II, Allied prisoners of war and Asian laborers were forced by the Japanese to construct the Burma Railway. The harsh conditions led to the deaths of thousands, giving it the nickname \"Death Railway.\"')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Ethiopia', 'In 1896, Ethiopia became the only African nation to successfully resist European colonization by defeating Italian forces at the Battle of Adwa. This victory secured Ethiopias sovereignty and is celebrated annually.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('New Zealand', 'Traditional Māori facial tattoos, known as tā moko, were carved into the skin using chisels. These tattoos represented social status, family heritage, and personal achievements. The process was painful and intricate.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Czech Republic', 'Sedlec Ossuary: Also known as the \"Bone Church,\" this small chapel in Kutná Hora is decorated with the bones of approximately 40,000 people. The macabre decorations include a chandelier made entirely of bones.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Ireland', 'The Irish Potato Famine (1845-1852) led to the death of approximately one million people and the emigration of another million. The failure of the potato crop, a staple food, caused widespread starvation and disease.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Ghana', 'Cape Coast Castle: This castle was one of the major centers for the transatlantic slave trade. Enslaved Africans were held in dungeons before being shipped across the Atlantic. The site is now a museum and a powerful reminder of this dark history.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('South Korea', 'In May 1980, the city of Gwangju saw a mass protest against the military dictatorship, which was brutally suppressed by the government. Hundreds, possibly thousands, were killed, and the event remains a significant moment in South Koreas fight for democracy.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Sudan', 'In the late 19th century, the Mahdist forces in Sudan waged a successful revolt against Egyptian and British rule, leading to the establishment of an Islamic state. The war was marked by brutal battles and significant loss of life.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('France', 'The Eiffel Tower was originally intended to be dismantled and sold as scrap after 20 years. It was also once painted yellow.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Nigeria', 'The Nok civilization, which flourished in Nigeria from around 1000 BC to 300 AD, is known for its advanced terracotta sculptures, which are among the oldest in Africa.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Russia', 'Lake Baikal in Siberia is the deepest and oldest freshwater lake in the world, holding about 20% of the worlds fresh surface water.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Hungary', 'The Rubik’s Cube was invented in 1974 by Hungarian architect Ernő Rubik. It became a global craze and remains one of the best-selling puzzles of all time.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Morocco', 'The University of Al Quaraouiyine in Fez, Morocco, founded in 859 AD, is considered the oldest continuously operating, degree-granting university in the world.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Senegal', 'Gorée Island, off the coast of Senegal, was a significant center for the Atlantic slave trade. The House of Slaves (Maison des Esclaves) on the island is now a museum and memorial to the victims of the slave trade.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Burkina Faso', 'Wagadou Empire: Also known as the Ghana Empire (not to be confused with modern Ghana), this medieval West African state was one of the earliest trading empires, thriving from the 6th to 13th centuries due to its control of gold mines and trade routes.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Lesotho', 'Lesotho is unique as the only independent state in the world that lies entirely above 1,000 meters (3,281 feet) in elevation, earning it the nickname \"The Kingdom in the Sky.\"')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Haiti', 'Haiti became the first independent black republic in the world and the second independent nation in the Americas after the United States, following a successful slave revolt led by Toussaint Louverture and Jean-Jacques Dessalines.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Cuba', 'In 1961, the Bay of Pigs Invasion was a failed CIA-led operation to overthrow Cuban leader Fidel Castro. It was a major Cold War confrontation and remains a significant event in Cuban history.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Dominica', 'Dominica is home to the world’s second-largest boiling lake, located in Morne Trois Pitons National Park, a UNESCO World Heritage site. The lake is a flooded fumarole, and its water is heated by volcanic activity.')");
                db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('Nepal', 'Mount Everest, the worlds highest peak at 8,848 meters (29,029 feet), is located in the Himalayas on the border between Nepal and China (Tibet). It was first summited by Sir Edmund Hillary and Tenzing Norgay in 1953.')");
            }
        }

        private boolean isTableEmpty(SQLiteDatabase db) {
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
            boolean isEmpty = false;
            if (cursor != null && cursor.moveToFirst()) {
                isEmpty = cursor.getInt(0) == 0;
            }
            if (cursor != null) {
                cursor.close();
            }
            return isEmpty;
        }

        public Cursor getFacts() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.query(TABLE_NAME, null, null, null, null, null, null);
        }
    }
}

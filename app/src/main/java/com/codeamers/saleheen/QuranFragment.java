package com.codeamers.saleheen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuranFragment extends Fragment {

    ListView QuranList;

    String[] pdfFilesNames = {
            "Holy Quran",
            "1\t\t Surah Al-Faatiha",
            "2\t\t Surah Al-Baqara",
            "3\t\t Surah Aal-i-Imraan",
            "4\t\t Surah An-Nisaa",
            "5\t\t Surah Al-Maaida",
            "6\t\t Surah Al-An'aam",
            "7\t\t Surah Al-A'raaf",
            "8\t\t Surah Al-Anfaal",
            "9\t\t Surah At-Tawba",
            "10\t\t Surah Yunus",
            "11\t\t Surah Hud",
            "12\t\t Surah Yusuf",
            "13\t\t Surah Ar-Ra'd",
            "14\t\t Surah Ibrahim",
            "15\t\t Surah Al-Hijr",
            "16\t\t Surah An-Nahl",
            "17\t\t Surah Al-Israa",
            "18\t\t Surah Al-Kahf",
            "19\t\t Surah Maryam",
            "20\t\t Surah Taa-Haa",
            "21\t\t Surah Al-Anbiyaa",
            "22\t\t Surah Al-Hajj",
            "23\t\t Surah Al-Muminoon",
            "24\t\t Surah An-Noor",
            "25\t\t Surah Al-Furqaan",
            "26\t\t Surah Ash-Shu'araa",
            "27\t\t Surah An-Naml",
            "28\t\t Surah Al-Qasas",
            "29\t\t Surah Al-Ankaboot",
            "30\t\t Surah Ar-Room",
            "31\t\t Surah Luqman",
            "32\t\t Surah As-Sajda",
            "33\t\t Surah Al-Ahzaab",
            "34\t\t Surah Saba",
            "35\t\t Surah Faatir",
            "36\t\t Surah Yaseen",
            "37\t\t Surah As-Saaffaat",
            "38\t\t Surah Saad",
            "39\t\t Surah Az-Zumar",
            "40\t\t Surah Ghafir",
            "41\t\t Surah Fussilat",
            "42\t\t Surah Ash-Shura",
            "43\t\t Surah Az-Zukhruf",
            "44\t\t Surah Ad-Dukhaan",
            "45\t\t Surah Al-Jaathiya",
            "46\t\t Surah Al-Ahqaf",
            "47\t\t Surah Muhammad",
            "48\t\t Surah Al-Fath",
            "49\t\t Surah Al-Hujuraat",
            "50\t\t Surah Qaaf",
            "51\t\t Surah Adh-Dhaariyat",
            "52\t\t Surah At-Tur",
            "53\t\t Surah An-Najm",
            "54\t\t Surah Al-Qamar",
            "55\t\t Surah Ar-Rahmaan",
            "56\t\t Surah Al-Waaqia",
            "57\t\t Surah Al-Hadid",
            "58\t\t Surah Al-Mujaadila",
            "59\t\t Surah Al-Hashr",
            "60\t\t Surah Al-Mumtahana",
            "61\t\t Surah As-Saff",
            "62\t\t Surah Al-Jumu'a",
            "63\t\t Surah Al-Munaafiqoon",
            "64\t\t Surah At-Taghaabun",
            "65\t\t Surah At-Talaaq",
            "66\t\t Surah At-Tahrim",
            "67\t\t Surah Al-Mulk",
            "68\t\t Surah Al-Qalam",
            "69\t\t Surah Al-Haaqqa",
            "70\t\t Surah Al-Ma'aarij",
            "71\t\t Surah Nooh",
            "72\t\t Surah Al-Jinn",
            "73\t\t Surah Al-Muzzammil",
            "74\t\t Surah Al-Muddaththir",
            "75\t\t Surah Al-Qiyaama",
            "76\t\t Surah Al-Insaan",
            "77\t\t Surah Al-Mursalaat",
            "78\t\t Surah An-Naba",
            "79\t\t Surah An-Naazi'aat",
            "80\t\t Surah Abasa",
            "81\t\t Surah At-Takwir",
            "82\t\t Surah Al-Infitaar",
            "83\t\t Surah Al-Mutaffifin",
            "84\t\t Surah Al-Inshiqaaq",
            "85\t\t Surah Al-Burooj",
            "86\t\t Surah At-Taariq",
            "87\t\t Surah Al-A'laa",
            "88\t\t Surah Al-Ghaashiya",
            "89\t\t Surah Al-Fajr",
            "90\t\t Surah Al-Balad",
            "91\t\t Surah Ash-Shams",
            "92\t\t Surah Al-Lail",
            "93\t\t Surah Ad-Dhuhaa",
            "94\t\t Surah Ash-Sharh",
            "95\t\t Surah At-Tin",
            "96\t\t Surah Al-Alaq",
            "97\t\t Surah Al-Qadr",
            "98\t\t Surah Al-Bayyina",
            "99\t\t Surah Az-Zalzala",
            "100\t\t Surah Al-Aadiyaat",
            "101\t\t Surah Al-Qaari'a",
            "102\t\t Surah At-Takaathur",
            "103\t\t Surah Al-Asr",
            "104\t\t Surah Al-Humaza",
            "105\t\t Surah Al-Fil",
            "106\t\t Surah Quraish",
            "107\t\t Surah Al-Maa'un",
            "108\t\t Surah Al-Kawthar",
            "109\t\t Surah Al-Kaafiroon",
            "110\t\t Surah An-Nasr",
            "111\t\t Surah Al-Masad",
            "112\t\t Surah Al-Ikhlaas",
            "113\t\t Surah Al-Falaq",
            "114\t\t Surah An-Naas"
    };

    public QuranFragment() {}

    public static QuranFragment newInstance() {
        return new QuranFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quran, container, false);
        QuranList = root.findViewById(R.id.surahNames);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,pdfFilesNames)
        {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView myText = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };
        QuranList.setAdapter(adapter);

//        onclick List Item to Its Pdf
        QuranList.setOnItemClickListener((parent, view, position, id) -> {
            String items = QuranList.getItemAtPosition(position).toString();
            SharedPreferences sp = getActivity().getSharedPreferences("QURAN" , Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("FILENAME" , items);
            editor.apply();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContent,new SelectedQuranFragment())
                    .addToBackStack(null)
                    .commit();
        });


        return root;
    }
}
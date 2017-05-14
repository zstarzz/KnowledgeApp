package com.example.zain.knowledgetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "knowledge.db";
    public static final String TABLE_NAME = "location_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "POSTCODE";
    public static final String COL_5 = "TYPE";
    public static final String COL_6 = "IMG_NAME";
    public static final int ROWID = 0;
    public static final int ROWNAME = 1;
    public static final int ROWADDRESS = 2;
    public static final int ROWPOSTCODE = 3;
    public static final int ROWTYPE = 4;
    public static final int ROWIMG = 5;


    public static final String TABLE_1_NAME = "type_table";
    public static final String COL_1_1 = "_id";
    public static final String COL_1_2 = "TYPE";

    public static final String TABLE_2_NAME = "postcode_table";
    public static final String COL_2_1 = "_id";
    public static final String COL_2_2 = "POSTCODE";
    public static final String COL_2_3 = "INFO";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, POSTCODE TEXT, TYPE TEXT, IMG_NAME TEXT)");
        db.execSQL("create table " + TABLE_1_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT)");
        db.execSQL("create table " + TABLE_2_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, POSTCODE TEXT, INFO TEXT)");

        ContentValues contentV = new ContentValues();

        contentV.put(COL_1_2, "Select Type");
        db.insert(TABLE_1_NAME, null, contentV);

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_2, "Select Postcode");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "EC1");
        contentValues.put(COL_2_3, "Barbican, Clerkenwell, Finsbury");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "EC2");
        contentValues.put(COL_2_3, "Moorgate, Liverpool Street");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "EC3");
        contentValues.put(COL_2_3, "Aldgate, Monument, Tower Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "EC4");
        contentValues.put(COL_2_3, "Fleet Street, St Paul's");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "WC1");
        contentValues.put(COL_2_3, "Bloomsbury, Gray's Inn");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "WC2");
        contentValues.put(COL_2_3, "Covent Garden, Holborn, Strand");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "E1");
        contentValues.put(COL_2_3, "Mile End, Stepney, Whitechapel");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E2");
        contentValues.put(COL_2_3, "Bethnal Green, Shoreditch");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E3");
        contentValues.put(COL_2_3, "Bow, Bromley-by-Bow");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E4");
        contentValues.put(COL_2_3, "Chingford, Highams Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E5");
        contentValues.put(COL_2_3, "Clapton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E6");
        contentValues.put(COL_2_3, "East Ham, Beckton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E7");
        contentValues.put(COL_2_3, "Forest Gate, Upton Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E8");
        contentValues.put(COL_2_3, "Hackney, Dalston");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E9");
        contentValues.put(COL_2_3, "Hackney, Homerton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E10");
        contentValues.put(COL_2_3, "Leyton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E11");
        contentValues.put(COL_2_3, "Leytonstone");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E12");
        contentValues.put(COL_2_3, "Manor Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E13");
        contentValues.put(COL_2_3, "Plaistow");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E14");
        contentValues.put(COL_2_3, "Isle of Dogs, Millwall, Poplar");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E15");
        contentValues.put(COL_2_3, "Stratford, West Ham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E16");
        contentValues.put(COL_2_3, "Canning Town, North Woolwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E17");
        contentValues.put(COL_2_3, "Walthamstow");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E18");
        contentValues.put(COL_2_3, "South Woodford");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E20");
        contentValues.put(COL_2_3, "Olympic Park, Stratford");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "N1");
        contentValues.put(COL_2_3, "Barnsbury, Canonbury, Islington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N2");
        contentValues.put(COL_2_3, "East Finchley");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N3");
        contentValues.put(COL_2_3, "Finchley Central");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N4");
        contentValues.put(COL_2_3, "Finsbury Park, Manor House");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N5");
        contentValues.put(COL_2_3, "Highbury");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N6");
        contentValues.put(COL_2_3, "Highgate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N7");
        contentValues.put(COL_2_3, "Holloway");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N8");
        contentValues.put(COL_2_3, "Crouch End, Hornsey");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N9");
        contentValues.put(COL_2_3, "Lower Edmonton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N10");
        contentValues.put(COL_2_3, "Muswell Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N11");
        contentValues.put(COL_2_3, "Friern Barnet, New Southgate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N12");
        contentValues.put(COL_2_3, "North Finchley, Woodside Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N13");
        contentValues.put(COL_2_3, "Palmers Green");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N14");
        contentValues.put(COL_2_3, "Southgate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N15");
        contentValues.put(COL_2_3, "Seven Sisters");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N16");
        contentValues.put(COL_2_3, "Stamford Hill, Stoke Newington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N17");
        contentValues.put(COL_2_3, "Tottenham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N18");
        contentValues.put(COL_2_3, "Upper Edmonton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N19");
        contentValues.put(COL_2_3, "Archway, Tufnell Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N20");
        contentValues.put(COL_2_3, "Totteridge, Whetstone");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N21");
        contentValues.put(COL_2_3, "Winchmore Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N22");
        contentValues.put(COL_2_3, "Alexandra Palace, Wood Green");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "NW1");
        contentValues.put(COL_2_3, "Camden Town, Regent's Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW2");
        contentValues.put(COL_2_3, "Cricklewood, Neasden");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW3");
        contentValues.put(COL_2_3, "Hampstead, Swiss Cottage");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW4");
        contentValues.put(COL_2_3, "Brent Cross, Hendon");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW5");
        contentValues.put(COL_2_3, "Kentish Town");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW6");
        contentValues.put(COL_2_3, "Kilburn, Queens Park, West Hampstead");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW7");
        contentValues.put(COL_2_3, "Mill Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW8");
        contentValues.put(COL_2_3, "St John's Wood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW9");
        contentValues.put(COL_2_3, "Colindale, Kingsbury");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW10");
        contentValues.put(COL_2_3, "Harlesden, Kensal Green, Willesden");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW11");
        contentValues.put(COL_2_3, "Golders Green, Hampstead Garden Suburb");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "SE1");
        contentValues.put(COL_2_3, "Bermondsey, Borough, Southwark, Waterloo");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE2");
        contentValues.put(COL_2_3, "Abbey Wood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE3");
        contentValues.put(COL_2_3, "Blackheath, Westcombe Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE4");
        contentValues.put(COL_2_3, "Brockley, Crofton Park, Honor Oak Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE5");
        contentValues.put(COL_2_3, "Camberwell");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE6");
        contentValues.put(COL_2_3, "Bellingham, Catford, Hither Green");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE7");
        contentValues.put(COL_2_3, "Charlton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE8");
        contentValues.put(COL_2_3, "Deptford");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE9");
        contentValues.put(COL_2_3, "Eltham, Mottingham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE10");
        contentValues.put(COL_2_3, "Greenwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE11");
        contentValues.put(COL_2_3, "Lambeth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE12");
        contentValues.put(COL_2_3, "Grove Park, Lee");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE13");
        contentValues.put(COL_2_3, "Hither Green, Lewisham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE14");
        contentValues.put(COL_2_3, "New Cross, New Cross Gate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE15");
        contentValues.put(COL_2_3, "Nunhead, Peckham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE16");
        contentValues.put(COL_2_3, "Rotherhithe, South Bermondsey, Surrey Docks");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE17");
        contentValues.put(COL_2_3, "Elephant & Castle, Walworth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE18");
        contentValues.put(COL_2_3, "Plumstead, Woolwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE19");
        contentValues.put(COL_2_3, "Crystal Palace, Upper Norwood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE20");
        contentValues.put(COL_2_3, "Anerley, Penge");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE21");
        contentValues.put(COL_2_3, "Dulwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE22");
        contentValues.put(COL_2_3, "East Dulwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE23");
        contentValues.put(COL_2_3, "Forest Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE24");
        contentValues.put(COL_2_3, "Herne Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE25");
        contentValues.put(COL_2_3, "South Norwood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE26");
        contentValues.put(COL_2_3, "Sydenham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE27");
        contentValues.put(COL_2_3, "Tulse Hill, West Norwood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE28");
        contentValues.put(COL_2_3, "Thamesmead");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "SW1");
        contentValues.put(COL_2_3, "Belgravia, Pimlico, Westminster");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW2");
        contentValues.put(COL_2_3, "Brixton, Streatham Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW3");
        contentValues.put(COL_2_3, "Brompton, Chelsea");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW4");
        contentValues.put(COL_2_3, "Clapham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW5");
        contentValues.put(COL_2_3, "Earl's Court");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW6");
        contentValues.put(COL_2_3, "Fulham, Parson's Green");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW7");
        contentValues.put(COL_2_3, "South Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW8");
        contentValues.put(COL_2_3, "Nine Elms, South Lambeth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW9");
        contentValues.put(COL_2_3, "Brixton, Stockwell");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW10");
        contentValues.put(COL_2_3, "West Brompton, World's End");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW11");
        contentValues.put(COL_2_3, "Battersea, Clapham Junction");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW12");
        contentValues.put(COL_2_3, "Balham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW13");
        contentValues.put(COL_2_3, "Barnes, Castelnau");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW14");
        contentValues.put(COL_2_3, "East Sheen, Mortlake");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW15");
        contentValues.put(COL_2_3, "Putney, Roehampton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW16");
        contentValues.put(COL_2_3, "Norbury, Streatham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW17");
        contentValues.put(COL_2_3, "Tooting");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW18");
        contentValues.put(COL_2_3, "Earlsfield, Wandsworth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW19");
        contentValues.put(COL_2_3, "Merton, Wimbledon");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW20");
        contentValues.put(COL_2_3, "Raynes Park, South Wimbledon");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "W1");
        contentValues.put(COL_2_3, "Marylebone, Mayfair, Soho");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W2");
        contentValues.put(COL_2_3, "Bayswater, Paddington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W3");
        contentValues.put(COL_2_3, "Acton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W4");
        contentValues.put(COL_2_3, "Chiswick");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W5");
        contentValues.put(COL_2_3, "Ealing");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W6");
        contentValues.put(COL_2_3, "Hammersmith");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W7");
        contentValues.put(COL_2_3, "Hanwell");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W8");
        contentValues.put(COL_2_3, "Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W9");
        contentValues.put(COL_2_3, "Maida Vale, Warwick Avenue");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W10");
        contentValues.put(COL_2_3, "Ladbroke Grove, North Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W11");
        contentValues.put(COL_2_3, "Holland Park, Notting Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W12");
        contentValues.put(COL_2_3, "Shepherd's Bush");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W13");
        contentValues.put(COL_2_3, "West Ealing");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W14");
        contentValues.put(COL_2_3, "West Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2_NAME);
        onCreate(db);
    }

    public Cursor ViewAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String name, String address, String postcode, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, postcode);
        contentValues.put(COL_5, type);
        db.update(TABLE_NAME, contentValues, "_id = ?", new String[]{id});
        return true;
    }

    public boolean updateMainTypeCol(String typeOld, String typeNew) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, typeNew);
        db.update(TABLE_NAME, contentValues, "TYPE = ?", new String[]{typeOld});
        return true;
    }

    public boolean updateType(String typeOld, String typeNew) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_2, typeNew);
        db.update(TABLE_1_NAME, contentValues, "TYPE = ?", new String[]{typeOld});
        return true;
    }

    public Cursor getImgPath() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToPosition(5);
        return res;
    }

    public Cursor GetRow(long RowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where _id = '" + RowId + "'", null);
        return res;
    }

    public Cursor GetRowT(long RowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_1_NAME + " where _id = '" + RowId + "'", null);
        return res;
    }


    public Cursor Search(String searchName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where NAME like '" + searchName + "%' or ADDRESS like '" + searchName + "%' or POSTCODE = '" + searchName + "' or TYPE = '" + searchName + "'", null);
        return res;
    }

    public Cursor SearchByPostcode(String searchPostcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where POSTCODE = '" + searchPostcode + "'", null);
        return res;
    }

    public Cursor SearchByType(String searchType) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where TYPE = '" + searchType + "'", null);
        return res;
    }


    public boolean insertData(String name, String address, String postcode, String type, String img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, postcode);
        contentValues.put(COL_5, type);
        contentValues.put(COL_6, img);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean insertImg(String id, String imgName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + TABLE_NAME + " set IMG_NAME = IMG_NAME || " + "' " + imgName + "' where _id =" + id);
        return true;
    }


    public Integer deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

    public boolean deleteImg(String id, String img) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE location_table SET IMG_NAME = trim(replace(IMG_NAME||' ','" + img + " ','')) WHERE _id = '" + id + "'");
        return true;
    }

    public Integer deleteTypeAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_1_NAME, null, null);
    }

    public Integer deleteRow(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{id});
    }

    public Integer deleteRowT(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_1_NAME, "_id = ?", new String[]{id});
    }


    public boolean insertType(String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_2, type);
        long result = db.insert(TABLE_1_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public int checkAttachedType(String type) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*) from " + TABLE_NAME + " where " + COL_5 + " ='" + type + "'", null);
        //Cursor res = db.rawQuery("select count(*) from location_table where TYPE ='"+type+"'", null);
        //Long numRows = DatabaseUtils.queryNumEntries(db, TABLE_NAME, "TYPE=?", new String[] {type});
        res.moveToFirst();
        int count = res.getInt(0);
        res.close();
        return count;
    }

    public int checkDuplicateType(String type) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select count(*) from " + TABLE_1_NAME + " where " + COL_1_2 + " ='" + type + "'", null);
        res.moveToFirst();
        int count = res.getInt(0);
        res.close();
        return count;
    }

    public Cursor ViewAllType() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_1_NAME + " where " + COL_1_2 + " != 'Select' order by " + COL_1_2 + " COLLATE NOCASE; ", null);
        return res;
    }

    public Cursor getPostcodeDb() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_2_NAME + " where POSTCODE != 'Select Postcode'", null);
        return res;
    }

    public List<String> getAllTypes() {
        List<String> labels = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_1_NAME + " order by " + COL_1_2 + " COLLATE NOCASE;", null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return labels;
    }

    public List<String> getAllPostcodes() {
        List<String> labels = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_2_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return labels;
    }

    public boolean dropPostcode() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("drop table if exists postcode_table");
        return true;
    }

    public boolean insertPostcode() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("create table " + TABLE_2_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, POSTCODE TEXT, INFO TEXT)");


        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_2, "Select");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "EC1");
        contentValues.put(COL_2_3, "Barbican, Clerkenwell, Finsbury");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "EC2");
        contentValues.put(COL_2_3, "Moorgate, Liverpool Street");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "EC3");
        contentValues.put(COL_2_3, "Aldgate, Monument, Tower Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "EC4");
        contentValues.put(COL_2_3, "Fleet Street, St Paul's");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "WC1");
        contentValues.put(COL_2_3, "Bloomsbury, Gray's Inn");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "WC2");
        contentValues.put(COL_2_3, "Covent Garden, Holborn, Strand");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "E1");
        contentValues.put(COL_2_3, "Mile End, Stepney, Whitechapel");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E2");
        contentValues.put(COL_2_3, "Bethnal Green, Shoreditch");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E3");
        contentValues.put(COL_2_3, "Bow, Bromley-by-Bow");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E4");
        contentValues.put(COL_2_3, "Chingford, Highams Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E5");
        contentValues.put(COL_2_3, "Clapton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E6");
        contentValues.put(COL_2_3, "East Ham, Beckton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E7");
        contentValues.put(COL_2_3, "Forest Gate, Upton Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E8");
        contentValues.put(COL_2_3, "Hackney, Dalston");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E9");
        contentValues.put(COL_2_3, "Hackney, Homerton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E10");
        contentValues.put(COL_2_3, "Leyton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E11");
        contentValues.put(COL_2_3, "Leytonstone");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E12");
        contentValues.put(COL_2_3, "Manor Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E13");
        contentValues.put(COL_2_3, "Plaistow");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E14");
        contentValues.put(COL_2_3, "Isle of Dogs, Millwall, Poplar");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E15");
        contentValues.put(COL_2_3, "Stratford, West Ham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E16");
        contentValues.put(COL_2_3, "Canning Town, North Woolwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E17");
        contentValues.put(COL_2_3, "Walthamstow");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E18");
        contentValues.put(COL_2_3, "South Woodford");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "E20");
        contentValues.put(COL_2_3, "Olympic Park, Stratford");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "N1");
        contentValues.put(COL_2_3, "Barnsbury, Canonbury, Islington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N2");
        contentValues.put(COL_2_3, "East Finchley");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N3");
        contentValues.put(COL_2_3, "Finchley Central");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N4");
        contentValues.put(COL_2_3, "Finsbury Park, Manor House");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N5");
        contentValues.put(COL_2_3, "Highbury");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N6");
        contentValues.put(COL_2_3, "Highgate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N7");
        contentValues.put(COL_2_3, "Holloway");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N8");
        contentValues.put(COL_2_3, "Crouch End, Hornsey");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N9");
        contentValues.put(COL_2_3, "Lower Edmonton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N10");
        contentValues.put(COL_2_3, "Muswell Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N11");
        contentValues.put(COL_2_3, "Friern Barnet, New Southgate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N12");
        contentValues.put(COL_2_3, "North Finchley, Woodside Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N13");
        contentValues.put(COL_2_3, "Palmers Green");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N14");
        contentValues.put(COL_2_3, "Southgate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N15");
        contentValues.put(COL_2_3, "Seven Sisters");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N16");
        contentValues.put(COL_2_3, "Stamford Hill, Stoke Newington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N17");
        contentValues.put(COL_2_3, "Tottenham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N18");
        contentValues.put(COL_2_3, "Upper Edmonton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N19");
        contentValues.put(COL_2_3, "Archway, Tufnell Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N20");
        contentValues.put(COL_2_3, "Totteridge, Whetstone");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N21");
        contentValues.put(COL_2_3, "Winchmore Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "N22");
        contentValues.put(COL_2_3, "Alexandra Palace, Wood Green");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "NW1");
        contentValues.put(COL_2_3, "Camden Town, Regent's Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW2");
        contentValues.put(COL_2_3, "Cricklewood, Neasden");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW3");
        contentValues.put(COL_2_3, "Hampstead, Swiss Cottage");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW4");
        contentValues.put(COL_2_3, "Brent Cross, Hendon");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW5");
        contentValues.put(COL_2_3, "Kentish Town");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW6");
        contentValues.put(COL_2_3, "Kilburn, Queens Park, West Hampstead");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW7");
        contentValues.put(COL_2_3, "Mill Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW8");
        contentValues.put(COL_2_3, "St John's Wood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW9");
        contentValues.put(COL_2_3, "Colindale, Kingsbury");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW10");
        contentValues.put(COL_2_3, "Harlesden, Kensal Green, Willesden");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "NW11");
        contentValues.put(COL_2_3, "Golders Green, Hampstead Garden Suburb");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "SE1");
        contentValues.put(COL_2_3, "Bermondsey, Borough, Southwark, Waterloo");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE2");
        contentValues.put(COL_2_3, "Abbey Wood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE3");
        contentValues.put(COL_2_3, "Blackheath, Westcombe Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE4");
        contentValues.put(COL_2_3, "Brockley, Crofton Park, Honor Oak Park");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE5");
        contentValues.put(COL_2_3, "Camberwell");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE6");
        contentValues.put(COL_2_3, "Bellingham, Catford, Hither Green");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE7");
        contentValues.put(COL_2_3, "Charlton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE8");
        contentValues.put(COL_2_3, "Deptford");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE9");
        contentValues.put(COL_2_3, "Eltham, Mottingham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE10");
        contentValues.put(COL_2_3, "Greenwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE11");
        contentValues.put(COL_2_3, "Lambeth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE12");
        contentValues.put(COL_2_3, "Grove Park, Lee");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE13");
        contentValues.put(COL_2_3, "Hither Green, Lewisham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE14");
        contentValues.put(COL_2_3, "New Cross, New Cross Gate");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE15");
        contentValues.put(COL_2_3, "Nunhead, Peckham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE16");
        contentValues.put(COL_2_3, "Rotherhithe, South Bermondsey, Surrey Docks");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE17");
        contentValues.put(COL_2_3, "Elephant & Castle, Walworth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE18");
        contentValues.put(COL_2_3, "Plumstead, Woolwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE19");
        contentValues.put(COL_2_3, "Crystal Palace, Upper Norwood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE20");
        contentValues.put(COL_2_3, "Anerley, Penge");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE21");
        contentValues.put(COL_2_3, "Dulwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE22");
        contentValues.put(COL_2_3, "East Dulwich");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE23");
        contentValues.put(COL_2_3, "Forest Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE24");
        contentValues.put(COL_2_3, "Herne Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE25");
        contentValues.put(COL_2_3, "South Norwood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE26");
        contentValues.put(COL_2_3, "Sydenham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE27");
        contentValues.put(COL_2_3, "Tulse Hill, West Norwood");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SE28");
        contentValues.put(COL_2_3, "Thamesmead");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "SW1");
        contentValues.put(COL_2_3, "Belgravia, Pimlico, Westminster");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW2");
        contentValues.put(COL_2_3, "Brixton, Streatham Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW3");
        contentValues.put(COL_2_3, "Brompton, Chelsea");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW4");
        contentValues.put(COL_2_3, "Clapham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW5");
        contentValues.put(COL_2_3, "Earl's Court");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW6");
        contentValues.put(COL_2_3, "Fulham, Parson's Green");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW7");
        contentValues.put(COL_2_3, "South Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW8");
        contentValues.put(COL_2_3, "Nine Elms, South Lambeth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW9");
        contentValues.put(COL_2_3, "Brixton, Stockwell");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW10");
        contentValues.put(COL_2_3, "West Brompton, World's End");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW11");
        contentValues.put(COL_2_3, "Battersea, Clapham Junction");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW12");
        contentValues.put(COL_2_3, "Balham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW13");
        contentValues.put(COL_2_3, "Barnes, Castelnau");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW14");
        contentValues.put(COL_2_3, "East Sheen, Mortlake");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW15");
        contentValues.put(COL_2_3, "Putney, Roehampton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW16");
        contentValues.put(COL_2_3, "Norbury, Streatham");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW17");
        contentValues.put(COL_2_3, "Tooting");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW18");
        contentValues.put(COL_2_3, "Earlsfield, Wandsworth");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW19");
        contentValues.put(COL_2_3, "Merton, Wimbledon");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "SW20");
        contentValues.put(COL_2_3, "Raynes Park, South Wimbledon");
        db.insert(TABLE_2_NAME, null, contentValues);


        contentValues.put(COL_2_2, "W1");
        contentValues.put(COL_2_3, "Marylebone, Mayfair, Soho");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W2");
        contentValues.put(COL_2_3, "Bayswater, Paddington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W3");
        contentValues.put(COL_2_3, "Acton");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W4");
        contentValues.put(COL_2_3, "Chiswick");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W5");
        contentValues.put(COL_2_3, "Ealing");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W6");
        contentValues.put(COL_2_3, "Hammersmith");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W7");
        contentValues.put(COL_2_3, "Hanwell");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W8");
        contentValues.put(COL_2_3, "Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W9");
        contentValues.put(COL_2_3, "Maida Vale, Warwick Avenue");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W10");
        contentValues.put(COL_2_3, "Ladbroke Grove, North Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W11");
        contentValues.put(COL_2_3, "Holland Park, Notting Hill");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W12");
        contentValues.put(COL_2_3, "Shepherd's Bush");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W13");
        contentValues.put(COL_2_3, "West Ealing");
        db.insert(TABLE_2_NAME, null, contentValues);

        contentValues.put(COL_2_2, "W14");
        contentValues.put(COL_2_3, "West Kensington");
        db.insert(TABLE_2_NAME, null, contentValues);


        return true;

    }
}

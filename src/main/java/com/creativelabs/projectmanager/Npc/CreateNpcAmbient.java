package com.creativelabs.projectmanager.Npc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateNpcAmbient {

    public static ArrayList<String> startupEntriesList(File obj) {

        Scanner myReader = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            list.add(data);
        }
        myReader.close();

        return list;
    }

    public void createNPC(int amount, int npcId) {


        for (int n = 1; n <= amount; n++) {

            String waypoint = "BANDITCAMP064";
            String npcGuild = "BDT";
            String npcName = npcGuild + "_" + npcId + "_Bandit_L";
            String name = "NAME_BANDIT";
            int SetAttributesToChapter = 4;
            int voice = 5 + n;
            String fight_tactic = "MASTER"; // MASTER / STRONG / COWARD
            String weapon = "ItMw_1h_Sld_Axe"; //ItMw_2h_Sld_Axe iron_mastersword
            String armor = "ITAR_BDT_M"; //ITAR_BDT_H ITAR_BDT_M ItAr_Leather_L itar_prisoner
            String Mdl_ApplyOverlayMds = "Arrogance"; // Tired / Militia / Mage / Arrogance / Relaxed
            int FightSkills = 45;
            String routine = "TA_Sit_Bench"; //TA_Smalltalk TA_Practice_Sword

            String npcScript = "\n" +
                    "instance " + npcName + " (Npc_Default)\n" +
                    "{\n" +
                    "\t// ------ NSC ------\n" +
                    "\tname \t\t= "+ name + ";\n" +
                    "\tguild \t\t= GIL_" + npcGuild + ";\n" +
                    "\tid \t\t\t= " + npcId + ";\n" +
                    "\tvoice \t\t= " + voice + ";\n" +
                    "\tflags      \t= 0;\n" +
                    "\tnpctype\t\t= NPCTYPE_AMBIENT;\n" +
                    "\t\n" +
                    "\t//---aivars-----\n" +
                    "\t//aivar[AIV_NewsOverride] = TRUE;\n" +
                    "\t\n" +
                    "\t// ------ Attribute ------\n" +
                    "\tB_SetAttributesToChapter (self, " + SetAttributesToChapter + ");\n" +
                    "\t\n" +
                    "\t// ------ Kampf-Taktik ------\n" +
                    "\tfight_tactic = FAI_HUMAN_" + fight_tactic + ";\t\n" +
                    "\t\n" +
                    "\t// ------ Equippte Waffen ------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\tEquipItem (self, " + weapon + ");\n" +
                    "\t\n" +
                    "\t// ------ Inventory ------\n" +
                    "\tB_CreateAmbientInv (self); \n" +
                    "\t\n" +
                    "\t// ------ visuals ------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\tB_SetNpcVisual \t\t(self, MALE, \"Hum_Head_Bald\", Face_L_ToughBart_Quentin, BodyTex_L, " + armor + ");\t\n" +
                    "\tMdl_SetModelFatness\t(self, 0.5);\n" +
                    "\tMdl_ApplyOverlayMds\t(self, \"Humans_" + Mdl_ApplyOverlayMds + ".mds\"); \n" +
                    "\n" +
                    "\t// ------ NSC-relevante Talente vergeben ------\n" +
                    "\tB_GiveNpcTalents (self);\n" +
                    "\t\n" +
                    "\t// ------ Kampf-Talente ------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\tB_SetFightSkills (self, " + FightSkills + "); \n" +
                    "\n" +
                    "\t// ------ TA anmelden ------\n" +
                    "\tdaily_routine \t= Rtn_Start_" + npcId + ";\n" +
                    "};\n" +
                    "\n" +
                    "FUNC VOID Rtn_Start_" + npcId + " ()\n" +
                    "{\n" +
                    "  \t" + routine + "     (08,00,12,00,\"" + waypoint.toUpperCase() + "\");\n" +
                    "    " + routine + "     (12,00,08,00,\"" + waypoint.toUpperCase() + "\");\t\t\n" +
                    "};";

            String startupEntry = "Wld_InsertNpc \t\t(" + npcName + ", \"" + waypoint.toUpperCase() + "\");";


            try {
                //create npc
                String npcPath = "D:/Program Files/JoWood/Gothic2ZlotaEdycja/_Work/data/Scripts/Content/" +
                        "Story/NPC/SAM_" + npcName + ".d";
                FileWriter myWriter = new FileWriter(npcPath);
                myWriter.write(npcScript);

                myWriter.close();


                //create npc entry in startup
                String startupEntryPath = "src/main/resources/files/startupEntries.txt";
                File startupEntryPath2 = new File("src/main/resources/files/startupEntries.txt");

                ArrayList<String> startupEntriesList = startupEntriesList(startupEntryPath2);
                startupEntriesList.add(startupEntry);
                FileWriter myWriterStartup = new FileWriter(startupEntryPath);

                for (String entry : startupEntriesList) {
                    myWriterStartup.write(entry + "\n");
                    //System.out.println(entry);
                }
                myWriterStartup.close();

                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            npcId = npcId + 1;
            //System.out.println(npcId);
        }

    }


    public static void main(String[] args) {

        CreateNpcAmbient createNpcAmbient = new CreateNpcAmbient();
        createNpcAmbient.createNPC(1,770);

    }
}

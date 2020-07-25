package com.creativelabs.projectmanager.Npc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CreateNpc {

    public void printNPC() {

    }


    public static void main(String[] args) {


        String npcName = "Estea";
        String npcGuild = "BDT";
        int npcId = 753;
        int SetAttributesToChapter = 4;
        String fight_tactic = "STRONG"; // MASTER / STRONG / COWARD
        String weapon = "iron_longsword"; //ItMw_2h_Sld_Axe iron_mastersword
        String armor = "ITAR_VLK_M"; //ITAR_BDT_H ITAR_BDT_M ItAr_Leather_L itar_prisoner
        String Mdl_ApplyOverlayMds = "Militia"; // Tired / Militia / Mage / Arrogance / Relaxed
        int FightSkills = 45;


        String npcScript = "\n" +
                "instance " + npcName + " (Npc_Default)\n" +
                "{\n" +
                "\t// ------ NSC ------\n" +
                "\tname \t\t= \""+ npcName + "\";\n" +
                "\tguild \t\t= GIL_" + npcGuild + ";\n" +
                "\tid \t\t\t= " + npcId + ";\n" +
                "\tvoice \t\t= 7;\n" +
                "\tflags      \t= NPC_FLAG_IMMORTAL;\n" +
                "\tnpctype\t\t= NPCTYPE_MAIN;\n" +
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
                "\tB_SetNpcVisual \t\t(self, MALE, \"Hum_Head_Bald\", Face_L_ToughBald01, BodyTex_L, " + armor + ");\t\n" +
                "\tMdl_SetModelFatness\t(self, 0.0);\n" +
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
                "  \tTA_Stand_Guarding     (08,00,12,00,\"" + npcName.toUpperCase() + "\");\n" +
                "    TA_Stand_Guarding     (12,00,08,00,\"" + npcName.toUpperCase() + "\");\t\t\n" +
                "};";

        String startupEntry = "Wld_InsertNpc \t\t(" + npcName + ", \"" + npcName.toUpperCase() + "\");";

        try {
            String npcPath = "D:/Program Files/JoWood/Gothic2ZlotaEdycja/_Work/data/Scripts/Content/" +
                    "Story/NPC/SAM_" + npcGuild + "_" + npcId  + "_" + npcName +".d";
            FileWriter myWriter = new FileWriter(npcPath);
            myWriter.write(npcScript);

            myWriter.close();


            String startupEntryPath = "src/main/resources/files/startupEntries.txt";
            FileWriter myWriterStartup = new FileWriter(startupEntryPath);
            myWriterStartup.write(startupEntry);
            myWriterStartup.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(npcScript);




    }
}
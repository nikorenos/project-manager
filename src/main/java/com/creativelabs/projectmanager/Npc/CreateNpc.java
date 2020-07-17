package com.creativelabs.projectmanager.Npc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CreateNpc {

    public void printNPC() {

    }


    public static void main(String[] args) {


        String npcName = "Gorstap";
        String npcGuild = "BDT";
        int npcId = 732;
        int attributesToChapter;


        String npcScript = "instance " + npcName + " (Npc_Default)\n" +
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
                "\taivar[AIV_NewsOverride] = TRUE;\n" +
                "\t\n" +
                "\t// ------ Attribute ------\n" +
                "\tB_SetAttributesToChapter (self, 6);\n" +
                "\t\n" +
                "\t// ------ Kampf-Taktik ------\n" +
                "\tfight_tactic = FAI_HUMAN_MASTER;\t\n" +
                "\t\n" +
                "\t// ------ Equippte Waffen ------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\tEquipItem (self, ItMw_2h_Sld_Axe);\n" +
                "\t\n" +
                "\t// ------ Inventory ------\n" +
                "\tB_CreateAmbientInv (self); \n" +
                "\t\n" +
                "\t// ------ visuals ------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\tB_SetNpcVisual \t\t(self, MALE, \"Hum_Head_Bald\", Face_L_ToughBald01, BodyTex_L, ITAR_Bloodwyn_Addon);\t\n" +
                "\tMdl_SetModelFatness\t(self, 0.5);\n" +
                "\tMdl_ApplyOverlayMds\t(self, \"Humans_Militia.mds\"); \n" +
                "\n" +
                "\t// ------ NSC-relevante Talente vergeben ------\n" +
                "\tB_GiveNpcTalents (self);\n" +
                "\t\n" +
                "\t// ------ Kampf-Talente ------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\tB_SetFightSkills (self, 55); \n" +
                "\n" +
                "\t// ------ TA anmelden ------\n" +
                "\tdaily_routine \t= Rtn_Start_1071;\n" +
                "};\n" +
                "\n" +
                "FUNC VOID Rtn_Start_1071 ()\n" +
                "{\n" +
                "  \tTA_Guard_Passage     (09,00,21,00,\"BL_ENTRANCE_04\");\n" +
                "    TA_Guard_Passage     (21,00,09,00,\"BL_ENTRANCE_04\");\t\t\n" +
                "};";

        try {
            FileWriter myWriter = new FileWriter("src/main/resources/files/npc.d");

            /*List<String> temporaryList = list.getUsersList().stream()
                    .map(s -> s.getUsername() + " " + s.getPassword() + " " + s.getEmail())
                    .collect(Collectors.toList());
            for (String userName : temporaryList) {
                myWriter.write(userName + "\n");
            }*/
            myWriter.write(npcScript);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(npcScript);




    }
}

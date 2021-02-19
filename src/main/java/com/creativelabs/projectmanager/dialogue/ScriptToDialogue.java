package com.creativelabs.projectmanager.dialogue;

public class ScriptToDialogue {

    public void convertScriptIntoDialogue(String script) {

    }

    public static void main(String[] args) {

        String script = "///////////////////////////////////////////////////////\n" +
                "//////////////  Morris trip\n" +
                "///////////////////////////////////////////////////////\n" +
                "\n" +
                "instance Dia_Alrik_Trip (C_INFO)\n" +
                "{\n" +
                "  npc          =  Alrik;\n" +
                "  nr           =  100;\n" +
                "  condition    =  Dia_Alrik_Trip_condition;\n" +
                "  information  =  Dia_Alrik_Trip_info;\n" +
                "  important    =  TRUE;\n" +
                "  permanent    =  FALSE;\n" +
                "};\n" +
                "\n" +
                "func int Dia_Alrik_Trip_condition ()\n" +
                "{\n" +
                "if Alrik_Go && (Npc_GetDistToWP (self, \"S040\") < 400)\n" +
                "\n" +
                "  { return TRUE; };\n" +
                "};\n" +
                "\n" +
                "func void Dia_Alrik_Trip_info ()\n" +
                "{\n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_00\"); //Jedna rzecz nie daje mi spokoju.\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_01\"); //Tak?\n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_02\"); //Kiedy opuszczasz naszą wyspę?\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_04\"); //Po zakończeniu Święta Łowów.\n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_05\"); //Ciekawe czy Gamaliel się zgodzi.\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_06\"); //Zrobi wszystko jeśli wręczę mu mieszek wypchany denarami.\n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_07\"); //Skąd weźmiesz taką sumę?\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_08\"); //Zapomniałeś o podziale łupów podczas Święta Łowów?\n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_09\"); //Racja, na pewno wpadnie ci co nieco do sakiewki. A co z Olsą?\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_11\"); //Nie mam ochoty na rozmowy na jej temat.  \n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_12\"); //Jak to? Zawsze tak chętnie o niej opowiadałeś...\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_14\"); //Tak, to prawda. Ale już nie ma o czym mówić. Koniec z nami.\n" +
                "  AI_Output(self,other,\"Alrik_Trip_12_15\"); //Poprzednio też tak mówiłeś.\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_16\"); //To jest jeden z powodów, dla których wypływam.\n" +
                "  AI_Output(other,self,\"Alrik_Trip_15_18\"); //Nareszcie nadejdzie chwila, kiedy się od niej uwolnię. \n" +
                "    \t\n" +
                "\tAI_StopProcessInfos (self);\n" +
                "\tNpc_ExchangeRoutine (self, \"TOSLAV\");\n" +
                "\n" +
                "};";

        int lastIndex = 0;
        int previousIndex = 0;
        int count = 0;
        String findStr = "instance ";
        String textInput = script;
        String npcName = "";
        String dialogueName = "";

        while (lastIndex != -1) {
            lastIndex = textInput.indexOf(findStr, lastIndex);



            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
                npcName = textInput.substring(lastIndex + 4, lastIndex + 9);
                System.out.println("A: " + npcName + " D: " + dialogueName + ":");
                }
            }
            previousIndex = lastIndex;
        }

        /*
        A: Olsa D: Bandits:
        H: Co słychać?
        N: Chce załatwić kilku bandytów.
        H: Co to za jedni?
        N: Później ci wyjaśnię. Pomożesz mi?

        Działanie:
        1. Znajduje tekst "instance " następnie dodaje 4 znaki i zaczyna pobierać imię aż znajdzie "_"
        2. Zapisuje imię i pobiera nazwę dialogu aż znajdzie " (C_INFO)".
        3. Znajduje "AI_Output(self" lub "AI_Output(other"  zaczyna zapiysywać dialogi

        */

}


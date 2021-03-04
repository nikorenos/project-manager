package com.creativelabs.projectmanager.dialogue;

public class ScriptToDialogue {

    public void convertScriptIntoDialogue(String script) {

    }

    public static void main(String[] args) {

        String script = "\n" +
                "///////////////////////////////////////////////////////\n" +
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

        int startDialogueNameIndex = 0;
        int endDialogueNameIndex = 0;
        int startAI_OutputIndex = 0;
        int startAI_OutputIndexPrevious = 0;
        int startDialogueIndex = 0;
        int startDialogueIndexPrevious = 0;
        int emptyLineIndex = 0;
        int count = 0;
        int count2 = 0;
        String findStrInstance = "instance ";
        String findStrC_INFO = " (C_INFO)";
        String findStrAI_Output = "AI_Output(";
        String findStrDialogueStart = "); //";
        String findStrEmptyLine = "    \t\n";
        String textInput = script + findStrAI_Output;
        String dialogueName = "";
        String speaker = "";
        String text = "";
        String emptyLine = "";

        while (startDialogueNameIndex != -1) {
            startDialogueNameIndex = textInput.indexOf(findStrInstance, startDialogueNameIndex);
            //System.out.println("Znalazłem instance w indeksie: " + startDialogueNameIndex);

            endDialogueNameIndex = textInput.indexOf(findStrC_INFO, endDialogueNameIndex);
            //System.out.println("Znalazłem (C_INFO) w indeksie: " + endDialogueNameIndex);





            if(startDialogueNameIndex != -1){
                count ++;
                startDialogueNameIndex += findStrInstance.length();
                //System.out.println("Ustawiam startDialogueNameIndex na: " + startDialogueNameIndex);
                endDialogueNameIndex += findStrC_INFO.length();
                /*endDialogueNameIndex = textInput.indexOf(findStrC_INFO, endDialogueNameIndex);
                endDialogueNameIndex -= findStrC_INFO.length();*/
                dialogueName = textInput.substring(startDialogueNameIndex + 4, endDialogueNameIndex - findStrC_INFO.length());
                //dialogueName = textInput.substring(endDialogueNameIndex -5, endDialogueNameIndex + 15);

                String[] dialogueparts = dialogueName.split("_");
                System.out.println();
                System.out.print("///////////////////////////////////////////////////////////////////////" + "\n");
                System.out.print("////////////////  " + dialogueparts[0] + " " + dialogueparts[1]  + "\n");
                System.out.println("///////////////////////////////////////////////////////////////////////" + "\n");
                System.out.println("A: " + dialogueparts[0] + " D: " + dialogueparts[1] + ":");
                System.out.println();
                }

                while (startAI_OutputIndex != -1) {
                    startAI_OutputIndex = textInput.indexOf(findStrAI_Output, startAI_OutputIndex);
                    startDialogueIndex = textInput.indexOf(findStrDialogueStart, startDialogueIndex);
                    //emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);

                    if (startAI_OutputIndex != -1) {
                        count2++;
                        startAI_OutputIndex += findStrAI_Output.length();
                        startDialogueIndex += findStrDialogueStart.length();
                        //emptyLineIndex += findStrEmptyLine.length();

                        if (count2 == 1) {

                        } else {
                            speaker = textInput.substring(startAI_OutputIndexPrevious, startAI_OutputIndexPrevious + 5);
                            text = textInput.substring(startDialogueIndexPrevious, startAI_OutputIndex -13);
                            //emptyLine = textInput.substring(emptyLineIndex, emptyLineIndex + 5);

                            if (speaker.equals("other")) {
                                System.out.print("H: ");
                            } else {
                                System.out.print("N: ");
                            }

                            System.out.println("!" + text + "!");
                            //System.out.println("<" + emptyLine + ">");
                        }
                    }
                    startAI_OutputIndexPrevious = startAI_OutputIndex;
                    startDialogueIndexPrevious = startDialogueIndex;
                }
            }
            endDialogueNameIndex = startAI_OutputIndex;
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


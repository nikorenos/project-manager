package com.creativelabs.projectmanager.Npc;

public class DialogueToScript {

    public void convertDialogueToScript () {

    }

    public static void main(String[] args){

        String text = "-Horn - Test- (dialog gdy zrobimy 3 zadania przed świętem łowów)\n" +
                "\n" +
                "M: Zrobiłem wszystko, co chciałeś.\n" +
                "H: Nie wszystko.\n" +
                "M: Jak to?\n" +
                "H: Pojawiła się nowa rzecz.\n" +
                "M: Co znowu?\n" +
                "\n";

        String findStr = ": ";
        String findStr2 = "-";
        String npcName = "";
        String diagoueName = "";
        String textInput = text + findStr;
        int lastIndex = 0;
        int previousIndex = 0;
        int count = 0;
        int count2 = 0;
        String script1 = "\n" +
                "///////////////////////////////////////////////////////////////////////\n" +
                "//\tHagen Hallo\n" +
                "///////////////////////////////////////////////////////////////////////\n" +
                "INSTANCE DIA_Hagen_Hallo (C_INFO)\n" +
                "{\n" +
                "\tnpc\t\t\t = \tPAL_200_Hagen;\n" +
                "\tnr\t\t\t = \t2;\n" +
                "\tcondition\t = \tDIA_Hagen_Hallo_Condition;\n" +
                "\tinformation\t = \tDIA_Hagen_Hallo_Info;\n" +
                "\timportant\t = \tFALSE;\n" +
                "\tpermanent\t = \tFALSE;\n" +
                "\tdescription\t =  \"(to do)\";\n" +
                "};\n" +
                "func int DIA_Hagen_Hallo_Condition ()\n" +
                "{\t\n" +
                "\tif (Npc_KnowsInfo (other, DIA_Hagen_Armee))\n" +
                "\t{ return TRUE; };\t\n" +
                "};\n" +
                "func void DIA_Hagen_Hallo_Info ()\n" +
                "{\n";



        while(lastIndex != -1){

            lastIndex = textInput.indexOf(findStr2,lastIndex);

            if(lastIndex != -1){

                count ++;
                lastIndex += findStr2.length();
                if (count == 2) {
                    npcName = textInput.substring(previousIndex, lastIndex-2);
                    //System.out.print("1" + npcName + "1");
                }
                if (count == 3) {
                    diagoueName = textInput.substring(previousIndex+1, lastIndex-1);
                    //System.out.println("1" + diagoueName+ "1");
                }

            }
            previousIndex = lastIndex;
        }
        //System.out.println(script1);
        System.out.println("///////////////////////////////////////////////////////////////////////");
        System.out.println("// " + npcName + " " + diagoueName );
        System.out.println("///////////////////////////////////////////////////////////////////////");
        System.out.println("INSTANCE DIA_" + npcName + "_" + diagoueName + " (C_INFO)");
        System.out.println("{");
        System.out.println("\tnpc\t\t\t = \t" + npcName+ ";");
        System.out.println("\tnr\t\t\t = \t2;");
        System.out.println("\tcondition\t = \tDIA_" + npcName + "_" + diagoueName + "_Condition;");
        System.out.println("\tinformation\t = \tDIA_" + npcName + "_" + diagoueName + "_Info;");
        System.out.println("\timportant\t = \tFALSE;");
        System.out.println("\tpermanent\t = \tFALSE;");
        System.out.println("\tdescription\t = \t\"(to do)\";");
        System.out.println("};");
        System.out.println("func int DIA_" + npcName + "_" + diagoueName + "_Condition ()");
        System.out.println("{");
        System.out.println("if (Npc_KnowsInfo (other, Dia_Alrik_Hello))");
        System.out.println("\t{ return TRUE; };\t");
        System.out.println("};");
        System.out.println("func void DIA_" + npcName + "_" + diagoueName + "_Info ()");
        System.out.println("{");








        lastIndex = 0;
        previousIndex = 0;
        count = 0;
        while(lastIndex != -1){

            lastIndex = textInput.indexOf(findStr,lastIndex);

            if(lastIndex != -1){

                count ++;
                lastIndex += findStr.length();

                if (count > 1) {

                    for (int i = 0; i < textInput.length(); i++) {

                    }
                    if (text.charAt(previousIndex-3) == 'M') {
                        System.out.print("\tAI_Output (other, self, \"DIA_" + npcName + "_" + diagoueName + "_15_0" + (count-2) + "\"); //" + textInput.substring(previousIndex, lastIndex - 3));
                    } else {
                        System.out.print("\tAI_Output (self, other, \"DIA_" + npcName + "_" + diagoueName + "_12_0" + (count-2) + "\"); //" + textInput.substring(previousIndex, lastIndex - 3));
                    }

                }
                previousIndex = lastIndex;
            }

        }
        System.out.println();
        System.out.println("};");
        System.out.println();

    }
}
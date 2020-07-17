instance Gorstap (Npc_Default)
{
	// ------ NSC ------
	name 		= "Gorstap";
	guild 		= GIL_BDT;
	id 			= 732;
	voice 		= 7;
	flags      	= NPC_FLAG_IMMORTAL;
	npctype		= NPCTYPE_MAIN;
	
	//---aivars-----
	aivar[AIV_NewsOverride] = TRUE;
	
	// ------ Attribute ------
	B_SetAttributesToChapter (self, 6);
	
	// ------ Kampf-Taktik ------
	fight_tactic = FAI_HUMAN_MASTER;	
	
	// ------ Equippte Waffen ------																	
	EquipItem (self, ItMw_2h_Sld_Axe);
	
	// ------ Inventory ------
	B_CreateAmbientInv (self); 
	
	// ------ visuals ------																		
	B_SetNpcVisual 		(self, MALE, "Hum_Head_Bald", Face_L_ToughBald01, BodyTex_L, ITAR_Bloodwyn_Addon);	
	Mdl_SetModelFatness	(self, 0.5);
	Mdl_ApplyOverlayMds	(self, "Humans_Militia.mds"); 

	// ------ NSC-relevante Talente vergeben ------
	B_GiveNpcTalents (self);
	
	// ------ Kampf-Talente ------																		
	B_SetFightSkills (self, 55); 

	// ------ TA anmelden ------
	daily_routine 	= Rtn_Start_1071;
};

FUNC VOID Rtn_Start_1071 ()
{
  	TA_Guard_Passage     (09,00,21,00,"BL_ENTRANCE_04");
    TA_Guard_Passage     (21,00,09,00,"BL_ENTRANCE_04");		
};
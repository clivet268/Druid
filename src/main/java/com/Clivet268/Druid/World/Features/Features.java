package com.Clivet268.Druid.World.Features;

import com.Clivet268.Druid.Druid;
import com.Clivet268.Druid.World.Features.Structures.DruidVillagePieces;
import com.Clivet268.Druid.World.Features.Structures.DruidVillageStructure;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Locale;

public class Features {
    public static Structure<NoFeatureConfig> DRUID_VILLAGE = new DruidVillageStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType tree_house2 = DruidVillagePieces.Piece::new;


	/*
	 * Registers the features and structures. Normal Features will be registered here too.
	 */
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
		IForgeRegistry<Feature<?>> registry = event.getRegistry();

		/* Registers the structure itself and sets what its path is. In this case,
		 * the structure will have the resourcelocation of structure_tutorial:run_down_house .
		 *
		 * It is always a good idea to register your regular features too so that other mods
		 * can use them too directly from the Forge Registry. It great for mod compatibility.
		 */
		Druid.register(registry, DRUID_VILLAGE, "druid_village");
		register(tree_house2, "tree_house2");
	}


	/*
	 * Registers the structures pieces themselves. If you don't do this part, Forge will complain to you in the Console.
	 */
	static IStructurePieceType register(IStructurePieceType structurePiece, String key)
	{
		return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), structurePiece);
	}
}

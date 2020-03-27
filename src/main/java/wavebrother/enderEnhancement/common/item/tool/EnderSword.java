package wavebrother.enderEnhancement.common.item.tool;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wavebrother.enderEnhancement.EnderEnhancement;
import wavebrother.enderEnhancement.common.item.IEnderItem;
import wavebrother.enderEnhancement.common.util.EnderTier;

@EventBusSubscriber
public class EnderSword extends ItemSword implements IEnderItem {

	private static final EnderToolsUtil tool = EnderToolsUtil.SWORD;

	public static final String hitTag = "HitWithEnderSword";

	static {
		MinecraftForge.EVENT_BUS.register(EnderSword.class);
	}

	public EnderSword(EnderTier material, String name) {
		super(material.toolTier.material());
		setCreativeTab(EnderEnhancement.CREATIVE_TAB);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tier = material;
		// TODO Auto-generated constructor stub
	}

	private final EnderTier tier;

	@Override
	public EnderTier getEnderTier() {
		return tier;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.getEntityData().setString(hitTag, tier.name());
		target.addTag(hitTag);
		target.addTag(getTagFromTier());
		return super.hitEntity(stack, target, attacker);
	}

	@SubscribeEvent
	public static void onEnderTeleport(EnderTeleportEvent event) {
		if (event.getEntity().getEntityData().hasKey(hitTag) || event.getEntity().getTags().contains(hitTag)) {
			event.setCanceled(true);
		}
	}

	public String getTagFromTier() {
		return hitTag + ":" + tier.name();
	}

}

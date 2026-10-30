package de.mal0o.voxyaccountcache.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(targets = "me.cortex.voxy.client.VoxyClientInstance")
public abstract class VoxyClientInstanceMixin {
    @Inject(method = "getBasePath", at = @At("RETURN"), cancellable = true)
    private static void voxyAccountCache$separateByProfile(CallbackInfoReturnable<Path> cir) {
        Path basePath = cir.getReturnValue();
        String profileId = Minecraft.getInstance().getUser().getProfileId().toString();

        cir.setReturnValue(basePath.resolve("accounts").resolve(profileId));
    }
}

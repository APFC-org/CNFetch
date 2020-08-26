package site.jihuayu.cnfetch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.LanguageMap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Mod("cnfetch")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CNFetch {

    public static final Logger LOGGER = LogManager.getLogger();
    public CNFetch(){
        if (FMLEnvironment.dist == Dist.CLIENT) {
            Minecraft.getInstance().getResourcePackList().addPackFinder(CNFetchPackFinder.RESOUCE);
        }
    }
    @SubscribeEvent
    public static void onClientStarting(FMLClientSetupEvent event) {
        File file0 = new File(System.getProperty("user.dir") + "/.cnfetch/pack.mcmeta");
        if(!file0.exists()){
            new FileDownloadManager("https://cdn.jsdelivr.net/gh/APFC-org/weblateTrans@0.0.8/pack.mcmeta", "pack.mcmeta", System.getProperty("user.dir")+"/.cnfetch").start("dl mcmeta");
        }
        File file1 = new File(System.getProperty("user.dir") + "/.cnfetch/pack.png");
        if(!file1.exists()){
            new FileDownloadManager("https://cdn.jsdelivr.net/gh/APFC-org/weblateTrans@0.0.8/pack.png", "pack.png", System.getProperty("user.dir")+"/.cnfetch").start("dl png");
        }
        ModList.get().getMods().forEach(i -> {
            File file = new File(System.getProperty("user.dir") + "/.cnfetch/assets/"+i.getModId()+"/lang/zh_cn.json");
            if(file.exists()){
                return;
            }
            FileDownloadManager ip = new FileDownloadManager("https://cdn.jsdelivr.net/gh/APFC-org/weblateTrans@0.0.8/assets/" + i.getModId() + "/lang/zh_cn.json", "zh_cn.json", System.getProperty("user.dir")+"/.cnfetch/assets/"+i.getModId()+"/lang");
            ip.start("dl " + i.getModId());
        });
    }

}

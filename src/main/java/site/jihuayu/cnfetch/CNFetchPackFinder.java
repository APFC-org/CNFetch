package site.jihuayu.cnfetch;

import net.minecraft.resources.FolderPack;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackInfo.IFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;

public final class CNFetchPackFinder implements IPackFinder {

    public static final CNFetchPackFinder RESOUCE = new CNFetchPackFinder("Resource Pack", new File(System.getProperty("user.home") + "/.cnfetch"));

    private final String type;
    private final File loaderDirectory;

    private CNFetchPackFinder(String type, File loaderDirectory) {

        this.type = type;
        this.loaderDirectory = loaderDirectory;
        try {

            Files.createDirectories(loaderDirectory.toPath());
        } catch (final IOException e) {
            CNFetch.LOGGER.error("Failed to initialize loader.", e);
        }
    }


    @Override
    public <T extends ResourcePackInfo> void func_230230_a_(Consumer<T> packs, IFactory<T> factory) {


        final String packName = "cnfetch";
        final T packInfo = ResourcePackInfo.createResourcePack(packName, true, () -> new FolderPack(this.loaderDirectory), factory, ResourcePackInfo.Priority.TOP, IPackNameDecorator.field_232625_a_);
        System.out.println(packInfo);
        System.out.println("++++++++++++++++++");
        if (packInfo != null) {

            packs.accept(packInfo);
        }
    }

}
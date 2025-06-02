package dog.comp.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.ColumnPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.text.Text;

public class NetherBorder {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("netherborder")
                .requires(source -> source.hasPermissionLevel(2)) // Only operators (permission level 2+) can use
                .then(CommandManager.argument("pos", ColumnPosArgumentType.columnPos())
                        .executes(context -> {
                            ColumnPos pos = ColumnPosArgumentType.getColumnPos(context, "pos");

                            double x = pos.x() * 8.0;
                            double z = pos.z() * 8.0;

                            ServerWorld overworld = context.getSource().getServer().getOverworld();
                            WorldBorder border = overworld.getWorldBorder();

                            border.setCenter(x, z);

                            context.getSource().sendFeedback(
                                    () -> Text.literal("World border center set to (" + x + ", " + z + ")"),
                                    true);
                            return 1;
                        })));
    }
}

package net.mrquba.calculatormod;

import static net.minecraft.server.command.CommandManager.*;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorMod implements ModInitializer {
	public static final String MOD_ID = "calculator_mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("add")
				.then(argument("first number", IntegerArgumentType.integer())
						.executes(context -> {
							final int firstNumber = IntegerArgumentType.getInteger(context, "first number");
							final int result = firstNumber + 0;
							context.getSource().sendFeedback(() -> Text.literal("%s + %s = %s".formatted(firstNumber, firstNumber, result)), false);
							return result;
						})
						.then(argument("second number", IntegerArgumentType.integer())
								.executes(context -> {
									final int firstNumber = IntegerArgumentType.getInteger(context, "first number");
									final int secondNumber = IntegerArgumentType.getInteger(context, "second number");
									final int result = firstNumber + secondNumber;
									context.getSource().sendFeedback(() -> Text.literal("%s + %s = %s".formatted(firstNumber, secondNumber, result)), false);
									return result;
								})
						))
		));
	}

}
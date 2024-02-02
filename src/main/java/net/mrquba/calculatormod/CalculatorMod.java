package net.mrquba.calculatormod;

import static net.minecraft.server.command.CommandManager.*;
import static net.minecraft.util.math.MathHelper.ceil;

import com.mojang.brigadier.arguments.DoubleArgumentType;
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
	public double calculator_add(double a, double b){
		return a + b;
	}
	public double calculator_multiply(double a, double b){
		return a * b;
	}
	public double calculator_divide(double a, double b){
		return a * (1/b);
	}
	public double calculator_power(double base, int degree){
		double result = 1;
		for(int iteration = degree - 1; iteration > 0; iteration--){
			result *= base * base;
		}
		return  result;
	}
	public double calculator_area_of_circle(double radius){
		return 3.14 * calculator_multiply(radius, radius);
	}
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("add")
				.then(argument("first number", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_add(DoubleArgumentType.getDouble(context, "first number"), 0);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("second number", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_add(DoubleArgumentType.getDouble(context, "first number"), DoubleArgumentType.getDouble(context, "second number"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
						))
		));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("subtract")
				.then(argument("first number", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_add(DoubleArgumentType.getDouble(context, "first number"), 0);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("second number", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_add(DoubleArgumentType.getDouble(context, "first number"), -DoubleArgumentType.getDouble(context, "second number"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
						))
		));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("multiply")
				.then(argument("first number", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_multiply(DoubleArgumentType.getDouble(context, "first number"), 1);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("second number", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_multiply(DoubleArgumentType.getDouble(context, "first number"), DoubleArgumentType.getDouble(context, "second number"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
						))
		));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("divide")
				.then(argument("first number", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_divide(DoubleArgumentType.getDouble(context, "first number"), 1);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("second number", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_divide(DoubleArgumentType.getDouble(context, "first number"), DoubleArgumentType.getDouble(context, "second number"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
						))
		));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("square")
				.then(argument("first number", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double a = DoubleArgumentType.getDouble(context, "first number");
							final double result = calculator_multiply(a, a);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
				)));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("squareroot")
				.then(argument("first number", DoubleArgumentType.doubleArg())
						.executes(context -> {


							final double a = DoubleArgumentType.getDouble(context, "first number");
							final double result = calculator_power(a, 1/2);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
				)));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("areaofsquare")
				.then(argument("length of side", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double a = DoubleArgumentType.getDouble(context, "length of side");
							final double result = calculator_multiply(a, a);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
				)));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("power")
				.then(argument("base", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_power(DoubleArgumentType.getDouble(context, "base"), 1);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("degree", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_power(DoubleArgumentType.getDouble(context, "base"), IntegerArgumentType.getInteger(context, "degree"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
						))));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("root")
				.then(argument("base", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_power(DoubleArgumentType.getDouble(context, "base"), 1/1);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("degree", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_power(DoubleArgumentType.getDouble(context, "base"), 1/IntegerArgumentType.getInteger(context, "degree"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
						))));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("areaofcircle")
				.then(argument("radius", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_area_of_circle(DoubleArgumentType.getDouble(context, "radius"));
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
				)));
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("volumeofcylinder")
				.then(argument("radius", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_area_of_circle(DoubleArgumentType.getDouble(context, "radius")) * 1;
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("height", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_area_of_circle(DoubleArgumentType.getDouble(context, "radius")) * calculator_area_of_circle(DoubleArgumentType.getDouble(context, "height"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
				))));;
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("volumeofcuboid")
				.then(argument("a", DoubleArgumentType.doubleArg())
						.executes(context -> {
							final double result = calculator_multiply(DoubleArgumentType.getDouble(context, "a"), 1);
							context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
							return ceil(result);
						})
						.then(argument("b", DoubleArgumentType.doubleArg())
								.executes(context -> {
									final double result = calculator_multiply(DoubleArgumentType.getDouble(context, "a"), DoubleArgumentType.getDouble(context, "a"));
									context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
									return ceil(result);
								})
								.then(argument("c", DoubleArgumentType.doubleArg())
										.executes(context -> {
											final double result = calculator_multiply(DoubleArgumentType.getDouble(context, "a"), DoubleArgumentType.getDouble(context, "a")) *DoubleArgumentType.getDouble(context, "c");
											context.getSource().sendFeedback(() -> Text.literal("%s".formatted(result)), false);
											return ceil(result);
										})
						))
		)));

	}


}
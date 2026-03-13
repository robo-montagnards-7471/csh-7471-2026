package frc.robot;

// FIXME: Est ce que c'est bon comme ça ou on ferait une instance de la classe ?
public class Config {
    // Controller settings
    public static int controller_port = 0;
    public static double bumper_sensitivity = 0.5; // 0 is not pressed and 1 is fully pressed

    // Motors configuration
    // Launcher
    public static double shooter_output_power = 1; // 1 is full speed, -1 is full speed other direction
    public static double shooter_input_power = 0.75; // 1 is full speed, -1 is full speed other direction

    // CAN BUS port
    // Launcher
    public static int shooter_output_leader = 13;
    public static int shooter_output_follower = 12;
    public static int shooter_input = 9;
}
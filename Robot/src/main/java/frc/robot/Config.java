package frc.robot;

// FIXME: Est ce que c'est bon comme ça ou on ferait une instance de la classe ?
public class Config {
    // Controller settings
    public static int controller_port = 0;
    public static double bumper_sensitivity = 0.5; // 0 is not pressed and 1 is fully pressed

    // Motors configuration
    // PID
    public static double P = 1.17;
    public static double I = 1.03;
    public static double D = 0.1;
    // Intake
    public static double in = 1;
    public static double out = -1;
    // Launcher
    public static double shooter_output_power = 1; // 1 is full speed, -1 is full speed other direction
    public static double shooter_input_power = 0.75; // 1 is full speed, -1 is full speed other direction
    public static boolean has_follower = false;
    public static boolean shooter_output_start_state = true; // true is running
    public static boolean shooter_input_start_state = false; // true is running

    // CAN BUS port
    // Intake
    public static int intake = 9;
    // Launcher
    public static int shooter_output_leader = 13;
    public static int shooter_output_follower = 12;
    public static int shooter_input = 9;
}
 
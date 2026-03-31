package frc.robot;

public class Config {
    // Controller settings
    public static int controller_port = 0;
    public static double bumper_sensitivity = 0.5; // 0 is not pressed and 1 is fully pressed
    // Swerves
    public static double rotation_speed = 0.1;

    // Motors configuration
    // PID
    public static double P = 1.17;
    public static double I = 1.03;
    public static double D = 0.1;
    // Climber
    public static double climb_up = 1;
    public static double climb_down = -1;
    // Intake
    public static double in = 0.375;
    public static double out = -0.375;
    public static double in_position = 0;
    public static double out_position = 0;
    public static double custom_divider = 10; // J'ai pas de nom pour ce truc, c'est pour remplacer les PIDs tout en restant smooth, utilisé pour l'intake pour le remote
    // Shooter
    public static double shooter_output_power = 1; // 1 is full speed, -1 is full speed other direction
    public static double shooter_input_power = 0.1; // 1 is full speed, -1 is full speed other direction
    public static boolean has_follower = true;
    public static boolean shooter_output_start_state = true; // true is running
    public static boolean shooter_input_start_state = false; // true is running

    // CAN BUS port
    // Climber
    public static int climber = 15;
    // Intake
    public static int intake = 13;
    public static int remote = 18;
    // Shooter
    public static int shooter_output_leader = 9;
    public static int shooter_output_follower = 14;
    public static int shooter_input = 4;
}
 
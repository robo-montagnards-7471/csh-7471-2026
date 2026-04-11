package frc.robot;


public class Config {
    // Controller settings
    public static int controller_port = 0;
    public static double bumper_sensitivity = 0.5; // 0 is not pressed and 1 is fully pressed
    // Pilot modifiers
    public static double william_modifier = 1; // 100 of input is considered
    public static double vincent_modifier = 0.6; // 60% of input is considered
    public static double other_modifier = 0.70; // 70% of input is considered

    // Motors configuration
    private static double custom_divider = 15; // J'ai pas de nom pour ce truc, c'est pour remplacer les PIDs tout en restant smooth, utilisé pour l'intake pour le remote
    // Climber
    public static double climb_up = 1;
    public static double climb_down = -1;
    // Intake
    public static double in = 0.60;
    public static double out = -0.60;
    public static double in_position = 0;
    public static double out_position = 14;
    public static double remote_speed = 0.2;
    public static boolean intake_deployed_at_start = false;
    public static double remote_stop_threshold = 0.1; // if the remote position changes less than this value, we consider it stopped
    // Shooter
    public static double shooter_output_power = 1;
    public static double shooter_input_power = 0.75;
    public static double start_shooter_input_when_output_is_at = 0.9; // 1 is 100%

    public static boolean has_follower = true;
    public static boolean shooter_output_start_state = true; // true is running
    public static boolean shooter_input_start_state = true; // true is running

    // Connexion port
    // Climber
    public static int climber = 15;
    // Intake
    public static int intake = 13;
    public static int remote = 12;
    public static int limit_switch_in = 1;
    public static int limit_switch_out = 0;
    // Shooter
    public static int shooter_output_leader = 14;
    public static int shooter_output_follower = 9;
    public static int shooter_input = 0;

    // General function
    // public static double smoothAtEnd( double current_speed, double target_speed ) {
    //     return (target_speed-current_speed)/custom_divider;
    // }
}
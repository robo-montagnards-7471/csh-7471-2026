package frc.robot;

// FIXME: Est ce que c'est bon comme ça ou on ferait une instance de la classe ?
public class Config {
    // Controller settings
    public static int controller_port = 0;
  
    // Motors configuration
    // PID
    public static double P = 1.17;
    public static double I = 1.03;
    public static double D = 0.1;
    // Intake
    public static double in = 1;
    public static double out = -1;

    // CAN BUS port
    // Intake
    public static int intake = 9;
}

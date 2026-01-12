package frc.robot.data;

/**
 * Classe de données simple pour transporter des paires de positions x/y
 * 
 * Peut être utilisé pour représenter le x/y d’un manche de commande
 * OU la position y du joystick avec le x étant résolue à partir d’une paire de déclencheurs analogiques
 */
public record StickPosition(double x, double y) {}
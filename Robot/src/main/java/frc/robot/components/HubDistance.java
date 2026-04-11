package frc.robot.components;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class HubDistance {
    private PhotonCamera camera;
    private final AprilTagFieldLayout field_layout;
    private final Transform3d robot_to_cam;
    private final PhotonPoseEstimator pose_estimator;

    private final Translation3d red_hub_position = new Translation3d( 11.91, 4.03, 1.51 );
    private final Translation3d blue_hub_position = new Translation3d( 4.63, 4.03, 1.51 );

    public HubDistance() {
        field_layout = AprilTagFieldLayout.loadField( AprilTagFields.kDefaultField );
        camera = new PhotonCamera("Front Camera");
        robot_to_cam = new Transform3d(
            new Translation3d( 0.38, 0.16, 0.28 ),
            new Rotation3d(0.0, 0.0, 0.0)
        );
        pose_estimator = new PhotonPoseEstimator(field_layout, robot_to_cam);
        SmartDashboard.putNumber( "Distance to Hub", -1);
    }

    public void displaydistance() {
        PhotonPipelineResult result = camera.getLatestResult();
        var estimated_pose = pose_estimator.estimateCoprocMultiTagPose(result);
        double distance = -1;
        boolean is_estimation_present = estimated_pose.isPresent();
        SmartDashboard.putBoolean("Can detect position", is_estimation_present);
        if( is_estimation_present ) {
            EstimatedRobotPose current_pos = estimated_pose.get();
            Translation2d current_translation = current_pos.estimatedPose.toPose2d().getTranslation();
            if( DriverStation.getAlliance().get() == Alliance.Blue ) {
                distance = blue_hub_position.toTranslation2d().getDistance( current_translation );
            }
            else if( DriverStation.getAlliance().get() == Alliance.Red ) {
                distance = red_hub_position.toTranslation2d().getDistance( current_translation );
            }
            SmartDashboard.putNumber( "Distance to Hub", distance);
        }
    }
}

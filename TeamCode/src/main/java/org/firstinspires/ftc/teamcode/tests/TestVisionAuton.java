package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Vision.AprilTagDetector;

//@Disabled // TODO: Remove this in order to show op-mode on phone
@Autonomous(name = "TestVisionAuton")
public class TestVisionAuton extends CommandOpMode {
    // Camera Configs
    int width = 0, height = 0;
    double tagSize = 0;
    double cX = 0, cY = 0;
    double fX = 0, fY = 0;


    // TODO: Declare detector
    private AprilTagDetector aprilTagDetector;
    private AprilTagDetector.Placement placement;

    @Override
    public void initialize() {

        // TODO: Initialize detector
        
        aprilTagDetector = new AprilTagDetector(hardwareMap, "blackEyes", width, height, tagSize, cX, cY, fX, fY);
        aprilTagDetector.init();
        aprilTagDetector.getPlacement();
         

        // TODO: Let FTC Dashboard get camera stream from detector
        // FtcDashboard.getInstance().startCameraStream(aprilTagDetector.getCamera(), 30);

        schedule(
                new WaitUntilCommand(this::isStarted).andThen(new RunCommand(() -> {
                    // Add telemetry
                telemetry.addData("April Tag Placement", aprilTagDetector.getPlacement().toString());
//                telemetry.addData("Lift Pos", aprilTagDetector.placementId());

                    telemetry.update();
                }))
        );
    }
}
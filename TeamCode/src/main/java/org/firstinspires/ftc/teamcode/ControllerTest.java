package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ControllerTest extends LinearOpMode {
    //declaring motors

    //motor1 = left, motor2 = right
    private DcMotor motor1;
    private DcMotor motor2;

    public void runOpMode() throws InterruptedException {
        //initializing motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        waitForStart();

        while (opModeIsActive()) {

            //moving the robot forward
            ForwardBack();
            Turn();

            telemetry.addData("Forward Speed", gamepad1.right_stick_y * 3);
            telemetry.addData("Turn Speed", gamepad1.left_stick_x * 3);
            telemetry.update();
        }
    }

    //functions that move motors
    //you can guess what they do by their names

    public void ForwardBack() {
        motor1.setPower(gamepad1.right_stick_y * 3);
        motor2.setPower(gamepad1.right_stick_y * 3);
    }

    public void Turn() {
        motor1.setPower(-gamepad1.left_stick_x * 3);
        motor2.setPower(gamepad1.left_stick_x * 3);
    }

    public void stopRobot() {
        motor1.setPower(0);
        motor2.setPower(0);
    }
}

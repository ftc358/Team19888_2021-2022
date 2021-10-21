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
    private DcMotor motor3;

    public void runOpMode() throws InterruptedException {
        //initializing motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");

        waitForStart();

        while (opModeIsActive()) {

            //moving the robot forward
            ForwardBack();
            Turn();
            Spin();

            telemetry.addData("forward power set to", gamepad1.right_stick_y);
            telemetry.addData("turn power set to", gamepad1.right_stick_y);
            telemetry.addData("spin power set to", gamepad1.right_trigger);
            telemetry.update();
        }
    }

    //functions that move motors
    //you can guess what they do by their names

    public void ForwardBack() {
        motor1.setPower(gamepad1.right_stick_y);
        motor2.setPower(gamepad1.right_stick_y);
    }

    public void Turn() {
        motor1.setPower(-gamepad1.right_stick_x);
        motor2.setPower(gamepad1.right_stick_x);
    }

    public void Spin() {
        motor3.setPower(gamepad1.right_trigger);
    }

    public void stopRobot() {
        motor1.setPower(0);
        motor2.setPower(0);
    }
}

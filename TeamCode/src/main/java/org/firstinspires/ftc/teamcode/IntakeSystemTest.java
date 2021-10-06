package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class IntakeSystemTest extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2;

    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        motor2.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

        }
    }

    public void takeIn() {
        motor1.setPower(1);
    }

    public void takeIn2() {
        motor1.setPower(1);
        motor2.setPower(1);
    }

    public void spitOut() {
        motor1.setPower(-1);
    }

    public void spitOut2() {
        motor1.setPower(-1);
        motor2.setPower(-1);
    }
}

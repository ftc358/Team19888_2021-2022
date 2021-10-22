package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.BasicsMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class RobotMain extends OpMode {
    BasicsMode board = new BasicsMode();
    private DcMotor RunMotor1;
    private DcMotor RunMotor2;
    private DcMotor DuckMotor;
    private DcMotor IntakeMotor;
    private Servo servo;
    private ColorSensor colorSensor;
    @Override

    public void init() {
        board.init(hardwareMap);
    }

    public void runOpMode() throws InterruptedException {

        RunMotor1 = hardwareMap.dcMotor.get("Runmotor1");
        RunMotor2 = hardwareMap.dcMotor.get("Runmotor2");
        RunMotor1.setDirection(DcMotor.Direction.REVERSE);
        loop();
    }

    @Override
    public void loop() {

        double colorvalue = colorSensor.argb();
        double colordefalutvalue = ;
        while ()
    }
}
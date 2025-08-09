package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp()
public class colorSensor extends LinearOpMode {

    private ColorSensor color_sensor;

    @Override()
    public void runOpMode() {
        if (opModeInInit()) {
            color_sensor = hardwareMap.get(ColorSensor.class, "sensor_color");
        }
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("red", color_sensor.red());
            telemetry.addData("green", color_sensor.green());
            telemetry.addData("blue", color_sensor.blue());
            telemetry.addData("brightness", color_sensor.alpha());
            telemetry.addData("hue", color_sensor.argb());
            telemetry.update();
            sleep(2000);
        }
    }
}
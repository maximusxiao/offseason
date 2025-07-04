package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

public class system {
    public void drive(Gamepad gamepad1, DcMotor motor_f_l, DcMotor motor_b_l, DcMotor motor_f_r, DcMotor motor_b_r) {

        // y_val reversed
        double y = -gamepad1.left_stick_y;


        // Multiplier of 1.17 to counteract strafing range
        double x = gamepad1.left_stick_x * 1.17;
        double rx = gamepad1.right_stick_x;

        // Range_limiter containes power within range [-1,1]
        double range_limiter = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double power_fl = -1 * ((y + x + rx) / range_limiter);
        double power_bl = -1 * ((y - x + rx) / range_limiter);
        double power_fr = (y - x - rx) / range_limiter;
        double power_br = (y + x - rx) / range_limiter;

        // Set Power
        motor_f_l.setPower(power_fl);
        motor_b_l.setPower(power_bl);
        motor_f_r.setPower(power_fr);
        motor_b_r.setPower(power_br);
    }
    public void extendo(Gamepad gamepad2, DcMotor extendo) {
        double extension = -gamepad2.right_stick_y;
        double slide_power = extension * 0.5;

        extendo.setPower(slide_power);
    }

    public void intake(Gamepad gamepad2, CRServo intake_srvo) {
        double fwd_spin = gamepad2.left_trigger;
        double rvrse_spin = gamepad2.right_trigger;
        double spin_power = fwd_spin - rvrse_spin;
        
        intake_srvo.setPower(spin_power);
    }
    
    // BETA!!!
    // funny start up sequence cos why not
    /*public void funny_start_sequence(DcMotor h_slide_motor) {
        h_slide_motor.setPower(0.5);
        Thread.sleep(750000000);
        h_slide_motor.setPower(0.5;
    }*/
}

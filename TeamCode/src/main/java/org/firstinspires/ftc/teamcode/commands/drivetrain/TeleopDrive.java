package org.firstinspires.ftc.teamcode.commands.drivetrain;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotConfig;
import org.firstinspires.ftc.teamcode.utilities.di.DiContainer;
import org.firstinspires.ftc.teamcode.utilities.di.DiInterfaces;

public class TeleopDrive implements DiInterfaces.ITickable {
    @DiContainer.Inject()
    public Drivetrain drivetrain;

    @DiContainer.Inject(id="gamepad1")
    public Gamepad gamepad1;

    @DiContainer.Inject(id="gamepad2")
    public Gamepad gamepad2;

    @DiContainer.Inject()
    public Telemetry telemetry;

    public boolean isFast = false;
    public boolean isHoldingFast = false;

    @Override
    public void Tick() {
        double vertical = -powerCurve(gamepad1.left_stick_y);
        double horizontal = -powerCurve(gamepad1.left_stick_x);
        double rotational = powerCurve(gamepad1.right_stick_x);

        telemetry.addData("Drive Vertical", vertical);
        telemetry.addData("Drive Horizontal", horizontal);
        telemetry.addData("Drive Rotational", rotational);

        if (gamepad1.square) {
            if (!isHoldingFast) {
                isFast = !isFast;
                isHoldingFast = true;
            }
        } else {
            isHoldingFast = false;
        }

        drivetrain.MecanumDrive(vertical, horizontal, rotational, (isFast ? RobotConfig.fastDriveSpeed : RobotConfig.slowDriveSpeed));
        //drivetrain.TankDrive(horizontal, rotational);
    }

    public double powerCurve(double input) {
        if (input < -1) return -1;
        if (input > 1) return 1;

        return Math.pow(input, 3);
    }
}

package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import util.FrameUtil;

public class WaitFrame extends JFrame {

	public WaitFrame() {

		this.setTitle("Eco-Adventure");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(380, 370);

		this.setResizable(false);

		FrameUtil.setFrameCenter(this);
		add(new JLabel("º”‘ÿ÷–£¨«Î…‘∫Û...",JLabel.CENTER));
		setVisible(true);
	}

}

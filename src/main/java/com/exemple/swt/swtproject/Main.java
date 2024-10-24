package com.exemple.swt.swtproject;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class Main {

	public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Exemplo de MyView");
        shell.setSize(750, 500);
        shell.setLayout(new GridLayout(1, false));

        NewPipeline myView = new NewPipeline();
        myView.createPartControl(shell);
        
        Monitor primary = display.getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = shell.getBounds();
        
        int x = (bounds.width - rect.width) / 2;
        int y = (bounds.height - rect.height) / 2;
        
        shell.setLocation(x, y);
        
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
